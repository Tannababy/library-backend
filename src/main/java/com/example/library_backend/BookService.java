package com.example.library_backend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {

        List<Book> books = repository.findAll();

        System.out.println(books);

        return books;
    }

    public Optional<Book> getbookById(int id) {
        return repository.findById(id);
    }
}
