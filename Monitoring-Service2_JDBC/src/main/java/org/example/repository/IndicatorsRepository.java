package org.example.repository;

import org.example.service.UserService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IndicatorsRepository {

    private final DataSourceConfig dataSourceConfig;

    public IndicatorsRepository(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public void addNewIndicator(String newIndicatorName) throws SQLException {
        String insertDateSql = "INSERT INTO testschema.indicators_list (name) VALUES (?)";
        Connection connection = dataSourceConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insertDateSql);
        preparedStatement.setString(1, newIndicatorName);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ОШИБКА " + e.getMessage());
        }

    }

    public List<String> getAllIndicatorsList() {
        String sql = "SELECT name FROM testschema.indicators_list";
        List<String> indicatorsList = new ArrayList<>();
        try (Connection connection = dataSourceConfig.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String indicatorName = resultSet.getString("name");
                indicatorsList.add(indicatorName);
            }
            return indicatorsList;
        } catch (SQLException e) {
            System.out.println("ОШИБКА " + e.getMessage());
            return null;
        }
    }

    public void showAllUserIndicators() {

    }

    public static boolean isUserCanInputIndicator() {
        return false;
    }

    public void showUserIndicatorByLogin(String login) {

    }

    public boolean isUserCanInputIndicator(String name) {
        String sql = "SELECT name_id, date, user_id FROM testschema.indicators WHERE name_id = ? AND user_id = ?";
        try (Connection connection = dataSourceConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            UserService userService = new UserService(new UserRepository(dataSourceConfig));
            int name_id = getIdByName(name);
            int user_id = userService.getIdByLogin(UserService.activeLogin);
            preparedStatement.setInt(1, name_id);
            preparedStatement.setInt(2, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            System.out.println("ОШИБКА " + e.getMessage());
            return false;
        }
    }

    public Integer getIdByName(String name) {
        String sql = "SELECT id FROM testschema.indicators_list WHERE name = ?";
        try (Connection connection = dataSourceConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("SQL check user (isCorrectUser) Exception: " + e.getMessage());
            return null;
        }
    }

    public void saveIndicator(String name, double value) {
        String sql = "INSERT INTO testschema.indicators (name_id, value, date, user_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSourceConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            UserService userService = new UserService(new UserRepository(dataSourceConfig));
            int name_id = getIdByName(name);
            int user_id = userService.getIdByLogin(UserService.activeLogin);
            preparedStatement.setInt(1, name_id);
            preparedStatement.setDouble(2, value);
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(4, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL check user (saveIndicator) Exception: " + e.getMessage());
        }
    }

    public ResultSet getIndicatorsByLogin(String login) {
        String sql = "SELECT l.name, i.value FROM testschema.indicators i " +
                "LEFT JOIN testschema.indicators_list l ON i.name_id = l.id " +
                "LEFT JOIN testschema.users u ON i.user_id = u.id " +
                "WHERE u.login = ?";
        try (Connection connection = dataSourceConfig.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQL check user (saveIndicator) Exception: " + e.getMessage());
            return null;
        }
    }
}
