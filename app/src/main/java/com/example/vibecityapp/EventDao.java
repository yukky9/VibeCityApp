package com.example.vibecityapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM events")
    List<Event> getAllEvents();

    @Query("SELECT * FROM events WHERE isFavorite = 1")
    List<Event> getFavoriteEvents();

    @Insert
    void insertEvent(Event event);

    @Update
    void updateEvent(Event event);

    @Query("UPDATE events SET isFavorite = :isFavorite WHERE id = :eventId")
    void updateFavoriteStatus(String eventId, boolean isFavorite);

    @Query("SELECT * FROM events WHERE id = :eventId")
    Event getEventById(String eventId);
}