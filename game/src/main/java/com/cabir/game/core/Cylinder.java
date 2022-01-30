package com.cabir.game.core;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Cylinder extends InteractiveTileObject {
    public Cylinder(World world, TiledMap TiledMap, Rectangle rect,String name) {
        super(world, TiledMap, rect,name);
    }
    public void isCollision(){
        System.out.println(super.name+" hit");
    }
}
