package com.cabir.neat4j.layer;

import com.cabir.neat4j.math.$ReLU;
import com.cabir.neat4j.math.$Sigmoid;
import com.cabir.neat4j.math.MathFunction;
import com.cabir.neat4j.math.Matrix;

import java.util.Locale;

public class Activation extends Layer{
    private final MathFunction mathFunction;
    private final String activationName;
    public Activation(String type){
        if(type.toLowerCase(Locale.ROOT).equals("relu")) mathFunction = new $ReLU();
        else if(type.toLowerCase(Locale.ROOT).equals("sigmoid")) mathFunction = new $Sigmoid();
        else mathFunction = new $ReLU();
        this.activationName = type;
    }

    @Override
    public Matrix forward(Matrix m) {
        input = m.clone();
        output = mathFunction.calc(input);
        return output.clone();
    }

    @Override
    public LayerType type() {
        return LayerType.ACTIVATION;
    }

    @Override
    public String toString() {
        return "\nActivation{" +
                "mathFunction=" + mathFunction +
                '}';
    }
}
