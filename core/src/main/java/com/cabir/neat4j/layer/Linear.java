package com.cabir.neat4j.layer;

import com.cabir.neat4j.math.Matrix;
import com.cabir.neat4j.math.Shape;

public class Linear extends Layer{

    private final Shape shape;
    private Matrix weight;
    private Matrix bias;

    public Linear(int inputSize,int outputSize){
        this(new Shape(inputSize,outputSize));
    }

    public Linear(Shape shape){
        this.shape = shape;
        weight = Matrix.randomMatrix(shape.getInput(),shape.getOutput());
        bias = Matrix.randomMatrix(1,shape.getOutput());
    }

    @Override
    public Matrix forward(Matrix m) {
        input = m.clone();
        Matrix weightCalc = Matrix.dot(m, weight);
        assert weightCalc != null;
        output = Matrix.addBias(weightCalc,bias);
        assert output != null;
        return output;
    }

    @Override
    public LayerType type() {
        return LayerType.LINEAR;
    }

    @Override
    public String toString() {
        return "\nLinear{" +
                "shape=" + shape +
                ", weight=" + weight.shape() +
                ", bias=" + bias.shape() +
                '}';
    }

    @Override
    public Matrix weight() {
        return weight;
    }

    @Override
    public Matrix bias() {
        return bias;
    }
}
