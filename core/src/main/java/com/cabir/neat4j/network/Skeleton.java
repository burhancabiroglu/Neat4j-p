package com.cabir.neat4j.network;

import com.cabir.neat4j.layer.LayerSkeleton;

import java.util.ArrayList;

public class Skeleton {
    private final ArrayList<LayerSkeleton> layerSkeletons;

    public Skeleton(){
        layerSkeletons  = new ArrayList<>();
    }

    public Skeleton(ArrayList<LayerSkeleton> list){
        layerSkeletons = new ArrayList<>(list);
    }

    public void add(LayerSkeleton layerSkelaton){
        layerSkeletons.add(layerSkelaton);
    }


    public ArrayList<LayerSkeleton> get(){
        return  layerSkeletons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skeleton skeleton = (Skeleton) o;

        return layerSkeletons != null ? layerSkeletons.equals(skeleton.layerSkeletons) : skeleton.layerSkeletons == null;
    }

    @Override
    public int hashCode() {
        return layerSkeletons != null ? layerSkeletons.hashCode() : 0;
    }

    protected Skeleton clone() {
        return new Skeleton(layerSkeletons);
    }


    public NeatNetwork build(){
        return new NeatNetwork(this);
    }

    @Override
    public String toString() {
        return "Skeleton{" +
                "layerSkeletons=" + layerSkeletons +
                '}';
    }
}
