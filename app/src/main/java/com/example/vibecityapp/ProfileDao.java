package com.example.vibecityapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(Profile profile);

    @Update
    void updateProfile(Profile profile);

    @Query("SELECT * FROM profiles WHERE id = 1")
    Profile getProfile();
}