package com.example.vibecityapp;

import com.example.vibecityapp.Event;
import com.example.vibecityapp.ProfileUpdateRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;

public interface ApiService {
    @GET("events")
    Call<List<Event>> getEvents(@Header("Authorization") String token);

    @GET("events/{id}")
    Call<Event> getEventDetails(@Path("id") String eventId, @Header("Authorization") String token);

    @POST("profile")
    Call<Void> updateProfile(@Body ProfileUpdateRequest request, @Header("Authorization") String token);
}