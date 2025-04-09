package com.example.vibecityapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class EventDetailActivity extends AppCompatActivity {

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
}