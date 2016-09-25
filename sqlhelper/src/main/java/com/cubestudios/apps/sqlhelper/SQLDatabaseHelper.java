package com.cubestudios.apps.sqlhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Rajesh-Rk on 25-Sep-16.
 */
public class SQLDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes";
    private static final int DATABASE_VERSION = 1;
    private static final String NOTES_TABLE = "notes";
    private static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getKeyTitle() {
        return KEY_TITLE;
    }

    public static String getKeyContent() {
        return KEY_CONTENT;
    }

    public SQLDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + NOTES_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_CONTENT + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + NOTES_TABLE;
        db.execSQL(query);
        onCreate(db);
    }

    // CRUD Operations

    public void insertIntoDB(String title, String content) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, title);
        contentValues.put(KEY_CONTENT, content);

        database.insert(NOTES_TABLE, null, contentValues);
        database.close();
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + NOTES_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        // rawQuery(query,new String[] = {"test","content"}) returns note with title as test and content as content.

        if (cursor.moveToFirst()) {

            do {
                Note note = new Note();
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                note.setId(id);
                note.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return notes;
    }

    public void update(int id, String text, String KEY_TYPE) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        if (KEY_TYPE.equals(KEY_TITLE))
            contentValues.put(KEY_TITLE, text);
        else if (KEY_TYPE.equals(KEY_CONTENT))
            contentValues.put(KEY_CONTENT, text);

        database.update(NOTES_TABLE, contentValues, KEY_ID + "=?", new String[]{String.valueOf(id)});
        database.close();
    }



    public void delete(int id){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(NOTES_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});
        database.close();
    }

}
