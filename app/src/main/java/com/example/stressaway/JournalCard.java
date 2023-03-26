package com.example.stressaway;

public class JournalCard {
    private String content, date, id;

    public JournalCard(String content, String date, String id) {
        this.content = content;
        this.date = date;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public String getId(){
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
