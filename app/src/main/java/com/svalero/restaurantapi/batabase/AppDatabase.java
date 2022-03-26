package com.svalero.restaurantapi.batabase;

//hila el dao con la clase

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.restaurantapi.dao.RestaurantDao;
import com.svalero.restaurantapi.domain.Restaurant;

@Database(entities = {Restaurant.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RestaurantDao restaurantDao();
}
