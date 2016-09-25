package com.cubestudios.apps.sqlhelper;

import java.io.Serializable;

/**
 * Created by Rajeshkannah on 18-Sep-16.
 */
public class Note implements Serializable {
    private int id;
    private String title,content;
    private static final long serialVersionUID=1L;

    public Note() {
    }

    public Note(String title, int id, String content) {
        this.title = title;
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
