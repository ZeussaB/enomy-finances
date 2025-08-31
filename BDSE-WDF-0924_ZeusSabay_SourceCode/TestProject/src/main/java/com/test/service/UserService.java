package com.test.service;

import com.test.dao.UserDAO;
import com.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public UserService(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }
    
    public boolean login(String email, String rawPassword) {
        User user = userDAO.findByEmail(email);

        if (user == null) {
            System.out.println("User not found!");
            return false;
        }
        System.out.println("User found: ");
        System.out.println("User found: " + user.getEmail());
        System.out.println("Stored Password: " + user.getPassword());
        System.out.println("Raw Password Entered: " + rawPassword);

        // ❌ Remove password hashing check, compare directly
        if (rawPassword.equals(user.getPassword())) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Incorrect password!");
            return false;
        }
    }


    public boolean registerUser(User user) {
        if (userDAO.emailExists(user.getEmail())) {
            return false; // Prevent duplicate registration
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password before saving
        return userDAO.registerUser(user);
    }

    public boolean emailExists(String email) {
        return userDAO.emailExists(email);
    }

    public User authenticate(String email, String rawPassword) {
        User user = userDAO.findByEmail(email); // Get user by email only

        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user; // Valid login
        }
        return null; // Invalid credentials
    }
    
    // Verify if the provided password matches the stored hashed password
    public boolean verifyPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    // Update user's password
    public boolean updatePassword(String email, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        return userDAO.updatePassword(email, encodedPassword);
    }
}