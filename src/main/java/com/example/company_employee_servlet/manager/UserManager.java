package com.example.company_employee_servlet.manager;

import com.example.company_employee_servlet.db.DBConnectionProvider;
import com.example.company_employee_servlet.model.User;
import com.example.company_employee_servlet.model.UserType;

import java.sql.*;

public class UserManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void save(User user) {
        String sql = "INSERT INTO company_employee.user(name, surname, email, password, type) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getType().name());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            System.out.println("User Registered");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM company_employee.user WHERE email = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int employeeId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String userEmail = resultSet.getString("email");
                    String userPassword = resultSet.getString("password");
                    UserType userType = UserType.valueOf(resultSet.getString("type"));
                    return new User(employeeId, name, surname, userEmail, userPassword, userType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getByEmail(String email) {
        String query = "SELECT * FROM company_employee.user WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return User.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .surname(resultSet.getString("surname"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .type(UserType.valueOf(resultSet.getString("type")))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
