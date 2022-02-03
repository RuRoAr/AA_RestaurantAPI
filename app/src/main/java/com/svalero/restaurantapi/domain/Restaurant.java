package com.svalero.restaurantapi.domain;

public class Restaurant {

    private String name;
    private String address;
    private String typeFood;
    private float qualification;
    private String recommendation;
    private float mediumPrice;
    private String goBack;

    public Restaurant(String name, String address, String typeFood, float qualification, String recommendation, float mediumPrice, String goBack) {
        this.name = name;
        this.address = address;
        this.typeFood = typeFood;
        this.qualification = qualification;
        this.recommendation = recommendation;
        this.mediumPrice = mediumPrice;
        this.goBack = goBack;
    }

    public String getGoBack() {
        return goBack;
    }

    public void setGoBack(String goBack) {
        this.goBack = goBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }

    public float getQualification() {
        return qualification;
    }

    public void setQualification(float qualification) {
        this.qualification = qualification;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public float getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(float mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", typeFood='" + typeFood + '\'' +
                ", qualification=" + qualification +
                ", recommendation='" + recommendation + '\'' +
                ", mediumPrice=" + mediumPrice +
                ", goBack='" + goBack + '\'' +
                '}';
    }
}
