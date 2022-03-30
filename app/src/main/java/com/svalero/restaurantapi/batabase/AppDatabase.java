package com.svalero.restaurantapi.batabase;

//hila el dao con la clase

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.restaurantapi.dao.CocktailDao;
import com.svalero.restaurantapi.dao.RestaurantDao;
import com.svalero.restaurantapi.dao.WineDao;
import com.svalero.restaurantapi.domain.Cocktail;
import com.svalero.restaurantapi.domain.Restaurant;
import com.svalero.restaurantapi.domain.Wine;


@Database(entities = {Restaurant.class, Cocktail.class, Wine.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RestaurantDao restaurantDao();
    public abstract CocktailDao cocktailDao();
    public abstract WineDao wineDao();
}
