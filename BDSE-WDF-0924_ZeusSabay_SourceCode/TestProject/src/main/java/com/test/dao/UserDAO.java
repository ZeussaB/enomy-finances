package com.test.dao;

import com.test.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UserDAO {

    private final DataSource dataSource;
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/enomyfinances";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "ZeusSabay@12";

    private static final String INSERT_USER_SQL = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String CHECK_EMAIL_SQL = "SELECT id FROM users WHERE email = ?";
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT id, username, email, password FROM users WHERE email = ?";
    private static final String UPDATE_PASSWORD_SQL = "UPDATE users SET password = ? WHERE email = ?";
    
    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findByEmail(String email) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(FIND_USER_BY_EMAIL_SQL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // ✅ Raw password

                System.out.println("User found: " + user.getEmail());
                System.out.println("Stored Password: " + user.getPassword()); // Debugging
                return user;
            } else {
                System.out.println("User not found with email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean emailExists(String email) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CHECK_EMAIL_SQL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL)) {

            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword()); // Save as plain text

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 
    public boolean updatePassword(String email, String newPassword) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(UPDATE_PASSWORD_SQL)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}