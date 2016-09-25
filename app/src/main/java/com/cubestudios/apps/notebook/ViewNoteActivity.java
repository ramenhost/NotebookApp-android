package com.cubestudios.apps.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.cubestudios.apps.sqlhelper.Note;
import com.cubestudios.apps.sqlhelper.SQLDatabaseHelper;

public class ViewNoteActivity extends AppCompatActivity {

    private EditText titleText, contentText;
    private Note note;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        type = intent.getStringExtra(Constants.TYPE);
        note = (Note) intent.getSerializableExtra(Constants.NOTE);

        titleText = (EditText) findViewById(R.id.note_title_text);
        contentText = (EditText) findViewById(R.id.note_content_text);

        if (type.equals(Constants.EDIT_NOTE)) {
            titleText.setText(note.getTitle());
            contentText.setText(note.getContent());
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            save();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        save();
        super.onBackPressed();
    }

    public void save() {

        SQLDatabaseHelper sqlDatabaseHelper = new SQLDatabaseHelper(getApplicationContext());
        String title = titleText.getText().toString();
        String content = contentText.getText().toString();
        if (type.equals(Constants.EDIT_NOTE)) {
            if (!note.getTitle().equals(title))
                sqlDatabaseHelper.update(note.getId(), title, SQLDatabaseHelper.KEY_TITLE);
            else if (!note.getContent().equals(content))
                sqlDatabaseHelper.update(note.getId(), content, SQLDatabaseHelper.KEY_CONTENT);
        } else if (type.equals(Constants.NEW_NOTE)) {
            if (!title.equals("") || !content.equals(""))
                sqlDatabaseHelper.insertIntoDB(title, content);
        }
        sqlDatabaseHelper.close();
    }
}
