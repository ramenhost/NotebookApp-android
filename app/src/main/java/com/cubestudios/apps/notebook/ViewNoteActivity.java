package com.cubestudios.apps.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ViewNoteActivity extends AppCompatActivity {

    private EditText titleText,contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Constants.TYPE);
        Note note = (Note) intent.getSerializableExtra(Constants.NOTE);

        titleText = (EditText) findViewById(R.id.note_title_text);
        contentText = (EditText) findViewById(R.id.note_content_text);

        if(type.equals(Constants.EDIT_NOTE)){
            titleText.setText(note.getTitle());
            contentText.setText(note.getContent());
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
