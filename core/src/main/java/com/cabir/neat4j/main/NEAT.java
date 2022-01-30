package com.cabir.neat4j.main;

import com.cabir.neat4j.layer.Layer;
import com.cabir.neat4j.layer.LayerType;
import com.cabir.neat4j.math.Matrix;
import com.cabir.neat4j.network.NeatNetwork;
import com.cabir.neat4j.network.Skeleton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class NEAT {
    private final Skeleton skeleton;
    private final int populationCount;
    private final double mutationRate;
    private ArrayList<NeatNetwork> population;
    private final int generationLimit;

    public NEAT(Skeleton skeleton, int populationCount, double mutationRate,int generationLimit){
        this.skeleton = skeleton;
        this.mutationRate = mutationRate;
        this.populationCount = populationCount;
        population = new ArrayList<>();
        this.generationLimit = generationLimit;
        for (int i = 0; i < populationCount; i++) population.add(new NeatNetwork(skeleton));
    }

    public NeatNetwork crossingOver(NeatNetwork nn1,NeatNetwork nn2){
        NeatNetwork child = new NeatNetwork(skeleton);
        for (int i = 0; i < child.size(); i++) {
            Layer layer = child.getLayer(i);
            if(layer.type() != LayerType.LINEAR) continue;
            for (int j = 0; j < layer.weight().shape(0); j++) {
                for (int k = 0; k < layer.weight().shape(1); k++) {
                    if(Math.random()>mutationRate){
                        if(Math.random()<(nn1.fitness/(nn1.fitness+ nn2.fitness))){
                            child.getLayer(i).weight().data[j][k] = nn1.getLayer(i).weight().data[j][k];
                        }
                        else{
                            child.getLayer(i).weight().data[j][k] = nn2.getLayer(i).weight().data[j][k];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < child.size(); i++) {
            Layer layer = child.getLayer(i);
            if(layer.type() != LayerType.LINEAR) continue;
            for (int j = 0; j < layer.bias().shape(0); j++) {
                for (int k = 0; k < layer.bias().shape(1); k++) {
                    if(Math.random()>mutationRate){
                        if(Math.random()<(nn1.fitness/(nn1.fitness+ nn2.fitness))){
                            child.getLayer(i).bias().data[j][k] = nn1.getLayer(i).bias().data[j][k];
                        }
                        else{
                            child.getLayer(i).bias().data[j][k] = nn2.getLayer(i).bias().data[j][k];
                        }
                    }
                }
            }
        }

        return child;
    }

    public void sortPopulation(final boolean reversed){
        population.sort(new Comparator<NeatNetwork>() {
            @Override
            public int compare(NeatNetwork o1, NeatNetwork o2) {
                if(reversed){
                    return Double.compare(o2.fitness,o1.fitness);
                }
                else {
                    return Double.compare(o1.fitness,o2.fitness);
                }
            }
        });
    }


    public void createNewGenerations(){
        ArrayList<NeatNetwork> nextGen = new ArrayList<>();
        sortPopulation(true);
        for (int i = 0; i < populationCount; i++) {
            if(Math.random()<((double) (populationCount-i)/(double) populationCount)) nextGen.add(population.get(i).clone());
        }
        ArrayList<Double> fitnessSum = new ArrayList<>();
        fitnessSum.add(0.0);
        Optional<NeatNetwork> minFitNeuron = nextGen.stream().min(new Comparator<NeatNetwork>() {
            @Override
            public int compare(NeatNetwork o1, NeatNetwork o2) {
                return Double.compare(o1.fitness,o2.fitness);
            }

        });
        double minFit = minFitNeuron.get().fitness;
        for (int i = 0; i < nextGen.size(); i++) {
            fitnessSum.add((fitnessSum.get(i)+Math.pow((nextGen.get(i).fitness-minFit),4)));
        }

        while (nextGen.size()<populationCount){
            double r1 = Matrix.uniformRandom(0,fitnessSum.size()-1);
            double r2 = Matrix.uniformRandom(0,fitnessSum.size()-1);
            fitnessSum.sort(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return Double.compare(o1,o2);
                }
            });
            int i1 = fitnessSum.indexOf(r1);
            int i2 = fitnessSum.indexOf(r2);

            if((0<= i1 && i1<fitnessSum.size())&&(0<= i2 && i2<fitnessSum.size())){
                nextGen.add(crossingOver(nextGen.get((int)i1),nextGen.get((int)i2)));
            }
        }
        population.clear();
        population.addAll(nextGen);

    }


    public void fit(Matrix xVals,Matrix yTrue){

        for (int i = 0; i < generationLimit; i++) {
            for (int j = 0; j < population.size(); j++) {
                population.get(j).forward(xVals,yTrue);
            }
            createNewGenerations();
        }
    }

    public NeatNetwork best(){
        sortPopulation(true);
        return population.get(0);
    }

    public ArrayList<NeatNetwork> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<NeatNetwork> population) {
        this.population = population;
    }
}
