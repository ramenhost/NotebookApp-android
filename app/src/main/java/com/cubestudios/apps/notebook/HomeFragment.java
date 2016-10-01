package com.cubestudios.apps.notebook;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cubestudios.apps.sqlhelper.Note;
import com.cubestudios.apps.sqlhelper.SQLDatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Rajeshkannah on 18-Sep-16.
 */
public class HomeFragment extends Fragment {
    ArrayList<Note> notes = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewNotesAdapter notesAdapter;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        SQLDatabaseHelper sqlDatabaseHelper = new SQLDatabaseHelper(getContext());
        notes = sqlDatabaseHelper.getAllNotes();
        sqlDatabaseHelper.close();

        recyclerView = (RecyclerView) view.findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notesAdapter = new RecyclerViewNotesAdapter(notes, getContext());
        recyclerView.setAdapter(notesAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    public void update(){
        SQLDatabaseHelper sqlDatabaseHelper = new SQLDatabaseHelper(getContext());
        notes = sqlDatabaseHelper.getAllNotes();
        notesAdapter = (RecyclerViewNotesAdapter) recyclerView.getAdapter();
        notesAdapter.setNotes(notes);
        notesAdapter.notifyDataSetChanged();
        sqlDatabaseHelper.close();
    }
}
