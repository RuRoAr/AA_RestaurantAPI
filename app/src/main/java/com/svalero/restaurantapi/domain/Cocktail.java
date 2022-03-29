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
    private String ingredient;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float qualification;
    @ColumnInfo
    private boolean alcoholicDrink;

    @Override
    public String toString() {
        return
                "Nombre del cocktail ='" + name + "\n"  +
                " Grados de alcohol =" + grade +"\n"+
                 " Ingredientes ='" + ingredient + "\n"  +
                " Precio =" + price +"\n"+
                        " Calificacion =" + qualification +"\n"+
                " Lleva alcohol?? =" + alcoholicDrink ;
    }

    public Cocktail(String name, float grade, String ingredient, float price, float qualification, boolean alcoholicDrink) {
        this.name = name;
        this.grade = grade;
        this.ingredient = ingredient;
        this.price = price;
        this.qualification = qualification;
        this.alcoholicDrink = alcoholicDrink;
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
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

    public boolean isAlcoholicDrink() {
        return alcoholicDrink;
    }

    public void setAlcoholicDrink(boolean alcoholicDrink) {
        this.alcoholicDrink = alcoholicDrink;
    }
}
