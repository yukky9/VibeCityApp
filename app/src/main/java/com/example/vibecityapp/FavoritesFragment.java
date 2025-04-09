package com.example.vibecityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private EventAdapter eventAdapter;
    private AppDatabase db;
    private EventDao eventDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDatabase(requireContext());
        eventDao = db.eventDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        eventAdapter = new EventAdapter(new ArrayList<>(), event -> {
            Intent intent = new Intent(getActivity(), EventDetailActivity.class);
            intent.putExtra("event", event);
            startActivity(intent);
        });

        recyclerView.setAdapter(eventAdapter);
        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        new Thread(() -> {
            List<Event> favorites = eventDao.getFavoriteEvents();
            requireActivity().runOnUiThread(() -> {
                eventAdapter.updateList(favorites);

                // Показываем пустое состояние, если нет избранных
                View emptyState = getView().findViewById(R.id.empty_state);
                RecyclerView recyclerView = getView().findViewById(R.id.favorites_recycler_view);

                if (favorites.isEmpty()) {
                    emptyState.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);

                    // Обработчик кнопки "Explore Events"
                    Button exploreButton = getView().findViewById(R.id.explore_events_button);
                    exploreButton.setOnClickListener(v -> {
                        BottomNavigationView navView = requireActivity().findViewById(R.id.bottom_navigation);
                        navView.setSelectedItemId(R.id.nav_events);
                    });
                } else {
                    emptyState.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        }).start();
    }
}
