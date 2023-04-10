package com.example.stressaway;

public class BookCard {
    private String bookName, authorName;
    private int id;

    public BookCard (String bookName, String authorName, int id) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getId(){
        return id;
    }

}
