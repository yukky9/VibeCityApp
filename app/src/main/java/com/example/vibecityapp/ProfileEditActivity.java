package com.example.vibecityapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEditActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private ChipGroup interestsChipGroup;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        usernameEditText = findViewById(R.id.edit_username);
        interestsChipGroup = findViewById(R.id.interests_chip_group);
        saveButton = findViewById(R.id.save_profile_button);

        // Sample data - in a real app, this would come from your database/API
        usernameEditText.setText("User123");
        List<String> allInterests = Arrays.asList("Music", "Art", "Technology", "Sports", "Food", "Travel");
        List<String> userInterests = Arrays.asList("Music", "Art", "Technology");

        // Add chips for all interests
        for (String interest : allInterests) {
            Chip chip = new Chip(this);
            chip.setText(interest);
            chip.setCheckable(true);
            chip.setChecked(userInterests.contains(interest));
            interestsChipGroup.addView(chip);
        }

        saveButton.setOnClickListener(v -> {
            String newUsername = usernameEditText.getText().toString();

            // Get selected interests from ChipGroup
            List<String> selectedInterests = new ArrayList<>();
            for (int i = 0; i < interestsChipGroup.getChildCount(); i++) {
                Chip chip = (Chip) interestsChipGroup.getChildAt(i);
                if (chip.isChecked()) {
                    selectedInterests.add(chip.getText().toString());
                }
            }

            // Create the request object
            ProfileUpdateRequest request = new ProfileUpdateRequest(newUsername, selectedInterests);

            // Make API call (using Retrofit)
            String authToken = ((VibeCityApp) getApplication()).getAuthToken();
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<Void> call = apiService.updateProfile(request, "Bearer " + authToken);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ProfileEditActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ProfileEditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ProfileEditActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}