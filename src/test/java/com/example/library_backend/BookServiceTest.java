package com.example.library_backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    void shouldReturnBookById() {

        Book testBook = new Book();
        testBook.setId(4);
        testBook.setTitle("Hood Feminism");
        testBook.setAuthor("Mikkie Kendall");
        testBook.setAvailable(true);

        // tells Mokito to find book by id and return the testbook if thet id is called to the repo
        when(repository.findById(4)).thenReturn(Optional.of(testBook));

        Book result = service.getBookById(testBook.getId()).orElseThrow();

        assertEquals(4, result.getId());
        assertEquals("Hood Feminism", result.getTitle());
    }



}
