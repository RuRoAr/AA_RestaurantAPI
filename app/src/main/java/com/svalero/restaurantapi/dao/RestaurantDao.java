package com.svalero.restaurantapi.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.svalero.restaurantapi.domain.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDao {//aqui me creo los query methos

    @Query( "select * from  restaurant")
    List<Restaurant> getAll();

    @Query("SELECT * FROM restaurant WHERE name = :name")
    List<Restaurant> findByName(String name);

    @Insert
    void insert(Restaurant restaurant);

    @Delete()
    void  delete(Restaurant restaurant);
}
