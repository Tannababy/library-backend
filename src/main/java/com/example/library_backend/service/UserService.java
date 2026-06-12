package com.example.library_backend.service;

import com.example.library_backend.model.User;
import com.example.library_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {

        List<User> users = repository.findAll();

        System.out.println(users);

        return users;
    }

    public User getUserById(int id) {
        return repository.findById(id).orElseThrow();
    }

    public void saveUser(User user) {

        repository.save(user);

    }

    public void loadUsersFromCSV() {


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/users.csv"))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) { // continuously reads a line of text from opened file

                if (line.contains("Username") && line.contains("Password")) { // skip header
                    continue;
                }

                User newUser = getUser(line);

                saveUser(newUser);

            }

        } catch (IOException e) {

            System.err.println("CSV file not found: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private User getUser(String line) {
        String[] values = line.split(",");
        List<String> row = Arrays.asList(values);

        String userName, userEmail, userPassword;
        boolean userAdminStatus;

        userName = row.get(0);
        userEmail = row.get(1);
        userPassword = row.get(2);
        userAdminStatus = Boolean.parseBoolean(row.getLast());

        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setEmail(userEmail);
        newUser.setPassword(userPassword);
        newUser.setAdmin(userAdminStatus);
        return newUser;
    }
}
