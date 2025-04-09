package com.example.vibecityapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList;
    private OnEventClickListener listener;

    public interface OnEventClickListener {
        void onEventClick(Event event);
    }

    public EventAdapter(List<Event> eventList, OnEventClickListener listener) {
        this.eventList = eventList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.bind(event, listener);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private ImageView eventImage;
        private TextView eventTitle;
        private TextView eventDate;
        private TextView eventLocation;
        private ImageButton favoriteButton;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.event_image);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventDate = itemView.findViewById(R.id.event_date);
            eventLocation = itemView.findViewById(R.id.event_location);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
        }

        public void bind(Event event, OnEventClickListener listener) {
            Glide.with(itemView.getContext())
                    .load(event.getImageUrl())
                    .placeholder(R.drawable.ic_event_placeholder)
                    .into(eventImage);

            eventTitle.setText(event.getTitle());
            eventDate.setText(String.format("%s at %s", event.getDate(), event.getTime()));
            eventLocation.setText(event.getLocation());

            // Update favorite button icon
            updateFavoriteIcon(event.isFavorite());

            favoriteButton.setOnClickListener(v -> {
                event.setFavorite(!event.isFavorite());
                updateFavoriteIcon(event.isFavorite());
                // In a real app, you would save this to your database/API
            });

            itemView.setOnClickListener(v -> listener.onEventClick(event));
        }

        private void updateFavoriteIcon(boolean isFavorite) {
            favoriteButton.setImageResource(
                    isFavorite ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_border);
        }
    }
}