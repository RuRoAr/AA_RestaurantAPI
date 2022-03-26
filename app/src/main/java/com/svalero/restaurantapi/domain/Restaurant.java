package com.svalero.restaurantapi.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity//todo esto es a raiz del room
public class Restaurant implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String address;
    @ColumnInfo
    private String typeFood;
    @ColumnInfo
    private float qualification;
    @ColumnInfo
    private String recommendation;
    @ColumnInfo
    private float mediumPrice;
    @ColumnInfo
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return " Nombre : " + name + "\n" +
                " Direccion : " + address + "\n" +
                " Tipo de comida : " + typeFood + "\n" +
                " Calificacion : " + qualification + "\n" +
                " Plato a recomedar : " + recommendation + "\n" +
                " mediumPrice : " + mediumPrice + "\n" +
                " Si vuelvo pediré... : " + goBack + "\n";
    }
}
