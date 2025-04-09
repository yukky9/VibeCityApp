package com.example.vibecityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView usernameTextView;
    private TextView interestsTextView;
    private Button editProfileButton;
    private Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        usernameTextView = view.findViewById(R.id.profile_username);
        interestsTextView = view.findViewById(R.id.profile_interests);
        editProfileButton = view.findViewById(R.id.edit_profile_button);
        logoutButton = view.findViewById(R.id.logout_button);

        // Sample data - in a real app, this would come from your database/API
        usernameTextView.setText("User123");
        List<String> interests = Arrays.asList("Music", "Art", "Technology");
        interestsTextView.setText(String.join(", ", interests));

        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            ((VibeCityApp) requireActivity().getApplication()).setAuthToken(null);
            startActivity(new Intent(getActivity(), CodeInputActivity.class));
            requireActivity().finish();
        });

        return view;
    }
}