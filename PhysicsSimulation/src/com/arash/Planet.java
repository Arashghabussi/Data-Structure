package com.arash;

public class Planet {

    public String name;
    public double mass;
    public double initXF;
    public double initYF;
    public double initXV;
    public double initXY;
    public double size;

    public Planet(String name, double mass, double initXF, double initYF, double initXV, double initXY, double size) {

        this.name = name;
        this.mass = mass;
        this.initXF = initXF;
        this.initYF = initYF;
        this.initXV = initXV;
        this.initXY = initXY;
        this.size = size;
    }

    @Override
    public String toString() {
        return this.name + " " + this.mass + " " + this.initXF  + " " + this.initYF + " " + this.initXV + " " + this.initXY + " " + this.size;
    }
}
