package org.example.repository;

import org.example.model.Role;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private final DataSourceConfig dataSourceConfig;

    public UserRepository(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public void createUser(User user) throws SQLException {
        String insertDateSql = "INSERT INTO testschema.users (login, password, role) VALUES (?, ?, ?)";
        Connection connection = dataSourceConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertDateSql);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole().toString());
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL create user Exception: " + e.getMessage());
        }

    }

    public Integer getIdByLogin(String login) {
        String sql = "SELECT id FROM testschema.users WHERE login = ?";
        try (Connection connection = dataSourceConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("SQL check user (getIdByLogin) Exception: " + e.getMessage());
            return null;
        }
    }

    public boolean isCorrectUser(String login, String password) {
        String getUser = "SELECT login, password FROM testschema.users WHERE login = ?";
        try (Connection connection = dataSourceConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getUser);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String passwordFromDB = resultSet.getString("password");
            return password.equals(passwordFromDB);
        } catch (SQLException e) {
            System.out.println("SQL check user (isCorrectUser) Exception: " + e.getMessage());
            return false;
        }
    }

    public boolean isLoginExists(String login) {
        String sql = "SELECT login FROM testschema.users WHERE login = ?";
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("SQL check user (isLoginExists) Exception: " + e.getMessage());
            return false;
        }
    }

    public boolean isUserAdmin(String login) {
        return false;
    }

    public void changeUserRole(String login, Role role) {

    }
}