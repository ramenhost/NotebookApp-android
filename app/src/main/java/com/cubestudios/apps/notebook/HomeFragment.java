package com.cubestudios.apps.notebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Rajeshkannah on 18-Sep-16.
 */
public class HomeFragment extends Fragment {
    ArrayList<Note> notes = new ArrayList<>();

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);


        Note note = new Note("First Note", 1, "This is the Sample note created by the application");
        notes.add(note);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewNotesAdapter notesAdapter = new RecyclerViewNotesAdapter(notes, getContext());
        recyclerView.setAdapter(notesAdapter);
        return view;
    }
}
