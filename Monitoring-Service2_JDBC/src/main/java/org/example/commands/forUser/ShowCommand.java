package org.example.commands.forUser;

import org.example.commands.Command;
import org.example.service.IndicatorsService;
import org.example.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowCommand implements Command {

    private final IndicatorsService indicatorsService;

    public ShowCommand(IndicatorsService indicatorsService) {
        this.indicatorsService = indicatorsService;
    }

    @Override
    public void execute() throws SQLException {
        ResultSet resultSet = indicatorsService.getIndicatorsByLogin(UserService.activeLogin);
        while (resultSet.next()) {
            String indicatorName = resultSet.getString(1);
            double value = resultSet.getDouble(2);
            System.out.println(indicatorName + ": " + value);
        }
    }
}
