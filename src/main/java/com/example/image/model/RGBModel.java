package com.example.image.model;

public class RGBModel {

    private String name;

    private Integer r;

    private Integer g;

    private Integer b;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public RGBModel(String name, Integer r, Integer g, Integer b) {
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
