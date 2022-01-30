package com.cabir.neat4j.loss;

import com.cabir.neat4j.math.Matrix;

public class MSELoss {
    public static double calc(Matrix y_true, Matrix y_pred){
        return Matrix.sub(y_true,y_pred).power().mean();
    }
}
