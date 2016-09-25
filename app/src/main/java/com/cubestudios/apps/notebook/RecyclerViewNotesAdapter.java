package com.cubestudios.apps.notebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cubestudios.apps.sqlhelper.Note;

import java.util.ArrayList;

/**
 * Created by Rajeshkannah on 18-Sep-16.
 */
public class RecyclerViewNotesAdapter extends RecyclerView.Adapter<RecyclerViewNotesAdapter.NotesViewHolder>{

    private ArrayList<Note> notes = new ArrayList<>();
    private Context context;

    public RecyclerViewNotesAdapter(ArrayList<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public RecyclerViewNotesAdapter.NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewNotesAdapter.NotesViewHolder holder, int position) {

        Note note = notes.get(position);
        holder.titleText.setText(note.getTitle());
        holder.contentText.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleText,contentText;

        public NotesViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.note_title);
            contentText = (TextView) itemView.findViewById(R.id.note_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,ViewNoteActivity.class);
            intent.putExtra(Constants.TYPE,Constants.EDIT_NOTE);
            intent.putExtra(Constants.NOTE,notes.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
