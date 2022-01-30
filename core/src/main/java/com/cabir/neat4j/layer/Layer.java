package com.cabir.neat4j.layer;

import com.cabir.neat4j.math.Matrix;
import com.cabir.neat4j.serialize.IGson;

public abstract class Layer extends IGson {

    Matrix input = null;
    Matrix output = null;

    public Matrix forward(Matrix m) {
        return null;
    }

    public void backward(Matrix m){}


    public LayerType type(){
        return null;
    }

    public void save(){}

    public Matrix weight(){
        return null;
    }

    public Matrix bias(){
        return null;
    }
}
