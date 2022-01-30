package com.cabir.neat4j.layer;

import com.cabir.neat4j.math.Shape;

import java.util.Locale;

public class LayerSkeleton {
    private Shape shape = null;
    private LayerType layerType = null;
    private ActivationType activationType = null;



    public LayerSkeleton(Shape shape){
        this.shape = shape;
        layerType = LayerType.LINEAR;
    }


    public LayerSkeleton(int inputSize,int outputSize){
        this.shape = new Shape(inputSize,outputSize);
        layerType = LayerType.LINEAR;
    }

    public LayerSkeleton(ActivationType activationType){
        this.activationType = activationType;
        this.layerType = LayerType.ACTIVATION;
    }

    public LayerSkeleton(String activation){
        if(activation.toLowerCase(Locale.ROOT).equals("relu"))  this.activationType = ActivationType.ReLU;
        else if(activation.toLowerCase(Locale.ROOT).equals("sigmoid"))  this.activationType = ActivationType.Sigmoid;
        this.layerType = LayerType.ACTIVATION;
    }

    public Layer generate(){
        if(layerType == LayerType.ACTIVATION){
            if (activationType == ActivationType.ReLU) return new Activation("relu");
            else return new Activation("sigmoid");
        }
        else return new Linear(shape);
    }

    @Override
    public String toString() {
        return "LayerSkeleton{" +
                "shape=" + shape +
                ", layerType=" + layerType +
                ", activationType=" + activationType +
                '}';
    }
}
