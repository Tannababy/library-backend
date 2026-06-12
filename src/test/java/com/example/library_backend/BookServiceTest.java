package com.example.library_backend;

import com.example.library_backend.model.Book;
import com.example.library_backend.repository.BookRepository;
import com.example.library_backend.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {

        book1 = new Book();
        book1.setId(1);
        book1.setTitle("Hood Feminism");
        book1.setAuthor("Mikkie Kendall");
        book1.setAvailable(true);

        book2 = new Book();
        book2.setId(2);
        book2.setTitle("Pet");
        book2.setAuthor("Akwaeke Emezi");
        book2.setAvailable(true);


    }

    @Test
    void shouldReturnBookById() {


        // tells Mokito to find book by id and return the testbook if thet id is called to the repo
        when(repository.findById(1)).thenReturn(Optional.of(book1));

        Book result = service.getBookById(book1.getId()).orElseThrow();

        assertEquals(book1.getId(), result.getId());
        assertEquals(book1.getTitle(), result.getTitle());
    }

    @Test
    void shouldReturnAllBooks() {

        List<Book> bookList = List.of(book1,book2);

        when(repository.findAll()).thenReturn(bookList);

        List<Book> result = service.getAllBooks();

        assertEquals(2, result.size());
        assertEquals(book1.getTitle(), result.getFirst().getTitle());
    }


}
