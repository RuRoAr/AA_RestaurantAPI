package com.svalero.restaurantapi.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cocktail implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name ;
    @ColumnInfo
    private  float grade;
    @ColumnInfo
    private String ingredients;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float qualification;

    @Override
    public String toString() {
        return "Cocktail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", qualification=" + qualification +
                '}';
    }

    public Cocktail(int id, String name, float grade, String ingredients, float price, float qualification) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.ingredients = ingredients;
        this.price = price;
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQualification() {
        return qualification;
    }

    public void setQualification(float qualification) {
        this.qualification = qualification;
    }
}
