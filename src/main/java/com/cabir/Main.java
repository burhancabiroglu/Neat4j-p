package com.cabir;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cabir.game.core.GrumpRun;
import com.cabir.neat4j.layer.LayerSkeleton;
import com.cabir.neat4j.main.NEAT;
import com.cabir.neat4j.math.Matrix;
import com.cabir.neat4j.network.NeatNetwork;
import com.cabir.neat4j.network.Skeleton;

public class Main {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new GrumpRun(), config);
    }
}
