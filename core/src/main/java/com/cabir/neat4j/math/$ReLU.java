package com.cabir.neat4j.math;

public class $ReLU implements MathFunction {
    @Override
    public Matrix calc(Matrix matrix) {
        return matrix.relu();
    }

    @Override
    public Matrix derivativeCalc(Matrix matrix) {
        return null;
    }

    @Override
    public String toString() {
        return "Rectified Linear Unit Function";
    }
}
