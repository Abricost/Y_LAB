package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void showAllUserIndicators() {

    }

    public static boolean isUserCanInputIndicator() {
        return false;
    }

    public void showUserIndicatorByLogin(String login) {

    }

}
