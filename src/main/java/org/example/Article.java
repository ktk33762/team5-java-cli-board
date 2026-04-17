package org.example;

import java.time.LocalDate;

public class Article {
    private int id;
    private String title;
    private String content;
    private LocalDate regDate;

    public Article(int id, String title, String content, LocalDate regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public LocalDate getRegDate() {
        return regDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
