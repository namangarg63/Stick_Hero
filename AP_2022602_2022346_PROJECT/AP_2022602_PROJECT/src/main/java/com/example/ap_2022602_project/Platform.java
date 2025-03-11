package com.example.ap_2022602_project;
import java.util.ArrayList;
class Platform {
    private Cherry cherry;
    private double pillar1Width;
    private double pillar2Width;
    private ArrayList<Double> pillars = new ArrayList<>();
    private ArrayList<Double> widthList = new ArrayList<>();
    private double x, y;

    public Platform() {
    }
    public Cherry getCherry() {
        return cherry;
    }
    public void setCherry(Cherry cherry) {
        this.cherry = cherry;
    }
    public double getP1() {
        return pillar1Width;
    }
    public void setP1(double p1) {
        this.pillar1Width = p1;
    }
    public double getP2() {
        return pillar2Width;
    }
    public void setP2(double p2) {
        this.pillar2Width = p2;
    }
    public ArrayList<Double> getPillars() {
        return pillars;
    }
    public void setPillars(ArrayList<Double> pillars) {
        this.pillars = pillars;
    }
    public ArrayList<Double> getWidthList() {
        return widthList;
    }

    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
}