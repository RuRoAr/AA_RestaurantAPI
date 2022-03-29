package com.svalero.restaurantapi.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Wine implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name ;
    @ColumnInfo
    private String type;
    @ColumnInfo
    private  int age;
    @ColumnInfo
    private String wineCellar;
    @ColumnInfo
    private float price;
    @ColumnInfo
    private float qualification;


    public Wine(String name, String type, int age, String wineCellar, float price, float qualification) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.wineCellar = wineCellar;
        this.price = price;
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return
                " Nombre del vino ='" + name + "\n"  +
                " Tipo de vino ='" + type + "\n"  +
                " Año del vino =" + age +"\n"+
        " Bodega ='" + wineCellar + '\'' +"\n"+
        " Precio de una botella =" + price +"\n"+
        " Calificación =" + qualification ;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWineCellar() {
        return wineCellar;
    }

    public void setWineCellar(String wineCellar) {
        this.wineCellar = wineCellar;
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
