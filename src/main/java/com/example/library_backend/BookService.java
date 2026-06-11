package com.example.library_backend;

import org.springframework.stereotype.Service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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

    public Optional<Book> getBookById(int id) {
        return repository.findById(id);
    }

    public String saveBook(Book book) {

        repository.save(book);

        return "Book: " + book.getTitle() + ", by Author: " + book.getAuthor() + " was saved.";
    }

    public void loadBooksFromJson() {


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/com/example/library_backend/books.json"))) {

            StringBuilder fullBookJsonArr = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fullBookJsonArr.append(line);
            }

            JSONArray listOfBooksJson = new JSONArray(fullBookJsonArr.toString());

            for (int i = 0; i < listOfBooksJson.length(); i++) {

                JSONObject bookObj = listOfBooksJson.getJSONObject(i);

                String title = bookObj.getString("title");
                String author = bookObj.getString("author");
                boolean isBorrowed = bookObj.getBoolean("isBorrowed");
                String borrowedBy = bookObj.isNull("borrowedBy")
                        ? null
                        : bookObj.getString("borrowedBy");
                int borrowCount = bookObj.getInt("borrowCount");

                Book libraryBook = new Book();
                libraryBook.setTitle(title);

                if (author.contains(",")) {

                    String[] name = author.split(",");
                    String[] reversedName = {name[name.length - 1], name[0]};
                    String nameStr = Arrays.toString(reversedName).replace("[", "").replace("]", "");
                    libraryBook.setAuthor(nameStr);
                } else {
                    libraryBook.setAuthor(author);
                }

                libraryBook.setAvailable(!isBorrowed);
                libraryBook.setBorrower(borrowedBy);
                libraryBook.setBorrowCount(borrowCount);

                saveBook(libraryBook);


            }


        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public Book updateBook(int id, Book book) {

        Book foundbook = repository.findById(id).orElseThrow();

        if (foundbook.isAvailable()) {

            foundbook.setAvailable(false);
            foundbook.setBorrower(book.getBorrower());
            foundbook.setBorrowCount(foundbook.getBorrowCount() + 1);
        } else {

            foundbook.setAvailable(true);
            foundbook.setBorrower(null);
        }

        return repository.save(foundbook);
    }
}
