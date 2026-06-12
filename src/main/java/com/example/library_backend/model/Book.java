package com.example.library_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String title;
    private String author;
    private String borrower;
    private boolean available;
    private int borrowCount;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBorrower() {
        return borrower;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    public String toString() {

        return "Id: " + this.getId() + ", Title: " + this.getTitle() + ", Author: " + this.getAuthor() + ", Borrower: " + this.getBorrower() + ", Available: " + this.isAvailable();
    }
}
