package com.svalero.restaurantapi.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.restaurantapi.domain.Cocktail;

import java.util.List;

@Dao
public interface CocktailDao {
    @Query( "select * from  cocktail")
    List<Cocktail> getAll();

    @Query("SELECT * FROM cocktail WHERE name = :name")
    List<Cocktail> findByName(String name);

    @Update
    void update(Cocktail cocktail);

    @Insert
    void insert(Cocktail cocktail);

    @Delete()
    void  delete(Cocktail cocktail);
}
