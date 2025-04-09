package com.example.vibecityapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.List;

@Entity(tableName = "profiles")
public class Profile {
    @PrimaryKey
    private int id = 1; // У нас будет только один профиль

    private String username;
    private String interests; // Будем хранить как строку с разделителем

    // Конструктор, геттеры и сеттеры
    public Profile() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getInterests() { return interests; }
    public void setInterests(String interests) { this.interests = interests; }

    // Преобразуем List<String> в строку для хранения
    public void setInterestsList(List<String> interests) {
        this.interests = String.join(",", interests);
    }

    // Получаем List<String> из строки
    public List<String> getInterestsList() {
        return Arrays.asList(interests.split(","));
    }
}