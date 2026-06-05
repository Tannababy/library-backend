package com.example.library_backend;

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

    public boolean isAvailable() {
        return available;
    }

    public String toString() {

        return "Id: " + this.getId() + ", Title: " + this.getTitle() + ", Author: " + this.getAuthor() + ", Borrower: " + this.getBorrower() + ", Available: " + this.isAvailable();
    }
}
