package com.example.library_backend.controller;

import com.example.library_backend.model.Book;
import com.example.library_backend.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library_backend/books")
public class BookController {

    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Book> getAllBooks() {

        return service.getAllBooks();
    }
    
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        
        return service.getBookById(id);
    }

    @GetMapping("/loadBooks")
    public void loadBooks() {

        service.loadBooksFromJson();
    }

    @PostMapping("/")
    public String saveBook(@RequestBody Book book) {

        return service.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {

        return service.updateBook(id, book);
    }

}
