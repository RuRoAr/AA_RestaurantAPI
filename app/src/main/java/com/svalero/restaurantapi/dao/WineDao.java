package com.svalero.restaurantapi.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.restaurantapi.domain.Wine;

import java.util.List;

@Dao
public interface WineDao {

    @Query( "select * from  wine")
    List<Wine> getAll();

    @Query("SELECT * FROM wine WHERE name = :name")
    List<Wine> findByName(String name);

    @Update
    void update(Wine wine);

    @Insert
    void insert(Wine wine);

    @Delete()
    void  delete(Wine wine);
}
