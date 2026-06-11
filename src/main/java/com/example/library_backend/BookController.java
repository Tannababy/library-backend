package com.example.library_backend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library_backend")
public class BookController {

    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {

        return service.getAllBooks();
    }
    
    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        
        return service.getBookById(id);
    }

    @GetMapping("/loadBooks")
    public void loadBooks() {

        service.loadBooksFromJson();
    }

    @PostMapping("/books")
    public String saveBook(@RequestBody Book book) {

        return service.saveBook(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {

        return service.updateBook(id, book);
    }

}
