package com.svalero.restaurantapi.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.restaurantapi.domain.User;

import java.util.List;

@Dao
public interface UserDao {

        @Query( "select * from user")
        List<User> getAll();

        @Query("SELECT * FROM user WHERE name = :name")
        List<User> findByName(String name);

        @Update
        void update(User user);

        @Insert
        void insert(User user);

        @Delete()
        void  delete(User user);
    }

