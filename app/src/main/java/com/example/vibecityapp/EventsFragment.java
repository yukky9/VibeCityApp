package com.example.vibecityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private EventAdapter eventAdapter;
    private List<Event> eventList = new ArrayList<>();
    private List<Event> filteredList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.events_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data - in a real app, this would come from an API
        eventList.add(new Event("1", "Jazz Night", "Live jazz performance", "2023-12-15", "20:00", "Jazz Club", "https://example.com/jazz.jpg"));
        eventList.add(new Event("2", "Art Exhibition", "Modern art showcase", "2023-12-16", "10:00", "Art Gallery", "https://example.com/art.jpg"));
        eventList.add(new Event("3", "Tech Conference", "Latest in tech innovations", "2023-12-17", "09:00", "Convention Center", "https://example.com/tech.jpg"));
        filteredList.addAll(eventList);

        eventAdapter = new EventAdapter(filteredList, event -> {
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });
        recyclerView.setAdapter(eventAdapter);

        // Search functionality
        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterEvents(newText);
                return true;
            }
        });

        return view;
    }

    private void filterEvents(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(eventList);
        } else {
            for (Event event : eventList) {
                if (event.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(event);
                }
            }
        }
        eventAdapter.notifyDataSetChanged();
    }
}