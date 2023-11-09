package com.jnu.student.myfirstapplication.bean;

import java.io.Serializable;

public class Book implements Serializable {

    private final String title;
    private final int coverResourceId;

    public Book(String title, int coverResourceId) {
        this.title = title;
        this.coverResourceId = coverResourceId;
    }

    public String getTitle() {
        return title;
    }

    public int getCoverResourceId() {
        return coverResourceId;
    }
}
