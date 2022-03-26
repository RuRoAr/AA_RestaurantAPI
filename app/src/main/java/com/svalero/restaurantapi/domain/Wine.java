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

    public Wine(int id, String name, String type, int age, String wineCellar) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.wineCellar = wineCellar;
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

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", wineCellar='" + wineCellar + '\'' +
                '}';
    }
}
