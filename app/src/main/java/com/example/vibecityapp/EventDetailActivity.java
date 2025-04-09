package com.example.vibecityapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class EventDetailActivity extends AppCompatActivity {
    private static final String TG_BOT_URL = "https://t.me/vibecity_expresspe_bot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Event event = (Event) getIntent().getSerializableExtra("event");
        if (event == null) {
            finish();
            return;
        }

        ImageView eventImage = findViewById(R.id.event_image);
        TextView eventTitle = findViewById(R.id.event_title);
        TextView eventDate = findViewById(R.id.event_date);
        TextView eventLocation = findViewById(R.id.event_location);
        TextView eventDescription = findViewById(R.id.event_description);
        ImageButton favoriteButton = findViewById(R.id.favorite_button);
        MaterialButton registerEventButton = findViewById(R.id.action_button);

        registerEventButton.setOnClickListener(v -> openTelegramBot());

        Glide.with(this)
                .load(event.getImageUrl())
                .placeholder(R.drawable.ic_event_placeholder)
                .into(eventImage);

        eventTitle.setText(event.getTitle());
        eventDate.setText(String.format("%s at %s", event.getDate(), event.getTime()));
        eventLocation.setText(event.getLocation());
        eventDescription.setText(event.getDescription());

        favoriteButton.setImageResource(
                event.isFavorite() ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);

        favoriteButton.setOnClickListener(v -> {
            event.setFavorite(!event.isFavorite());
            favoriteButton.setImageResource(
                    event.isFavorite() ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);
            // In a real app, you would save this to your database/API
        });
    }

    private void openTelegramBot() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(TG_BOT_URL));
            startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(TG_BOT_URL.replace("t.me", "web.telegram.org")));
            startActivity(intent);
        }
    }
}