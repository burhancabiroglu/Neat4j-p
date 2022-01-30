package com.cabir.neat4j.math;

public class $Sigmoid implements MathFunction {
    @Override
    public Matrix calc(Matrix matrix) {
        return matrix.sigmoid();
    }

    @Override
    public Matrix derivativeCalc(Matrix matrix) {
        return null;
    }

    @Override
    public String toString() {
        return "Sigmoid Function";
    }
}
