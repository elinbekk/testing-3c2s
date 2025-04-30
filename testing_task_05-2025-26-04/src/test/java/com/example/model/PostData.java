package com.example.model;

public class PostData {
    private String title;
    private String content;

    public PostData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostData() {}

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }
}
