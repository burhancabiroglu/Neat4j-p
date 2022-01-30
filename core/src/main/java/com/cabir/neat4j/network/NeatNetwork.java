package com.cabir.neat4j.network;

import com.cabir.neat4j.layer.Layer;
import com.cabir.neat4j.layer.LayerSkeleton;
import com.cabir.neat4j.loss.MSELoss;
import com.cabir.neat4j.math.Matrix;

import java.util.ArrayList;

public class NeatNetwork {
    private ArrayList<Layer> layers;
    public double fitness;
    private Skeleton skeleton;




    public NeatNetwork(ArrayList<Layer> layers,double fitness,Skeleton skeleton){
        this.fitness = fitness;
        this.skeleton = skeleton;
        this.layers = new ArrayList<>(layers);
    }


    public NeatNetwork(Skeleton skeleton){
        layers = new ArrayList<>();
        fitness = 0.0;
        for (LayerSkeleton ls: skeleton.get()) layers.add(ls.generate());
        this.skeleton = skeleton.clone();
    }

    public Matrix forward(Matrix inputData){
        Matrix output = inputData.clone();
        for (Layer layer: layers) output = layer.forward(output);
        return output;
    }

    public Matrix forward(Matrix inputData,Matrix yTrue){
        Matrix output = inputData.clone();
        for (Layer layer: layers) output = layer.forward(output);
        loss(yTrue,output);
        return output;
    }


    public double loss(Matrix y_true,Matrix y_pred){
        fitness = 1/(1+ MSELoss.calc(y_true,y_pred));
        return fitness;
    }


    public int size(){
        return layers.size();
    }

    public Layer getLayer(int index){
        return layers.get(index);
    }

    @Override
    public String toString() {
        return "NeatNetwork{" +
                "layers=" + layers +
                ", fitness=" + fitness +
                ", skeleton=" + skeleton +
                '}';
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public NeatNetwork clone(){
        return new NeatNetwork(this.layers,this.fitness,this.skeleton.clone());
    }
}
