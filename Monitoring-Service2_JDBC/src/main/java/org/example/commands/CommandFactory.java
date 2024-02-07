package org.example.commands;

import org.example.commands.forAdmin.LogCommand;
import org.example.commands.forAdmin.NewIndicatorCommand;
import org.example.commands.forAdmin.RoleAdminCommand;
import org.example.commands.forAdmin.ShowUsersCommand;
import org.example.commands.forUser.*;
import org.example.repository.DataSourceConfig;
import org.example.repository.IndicatorsRepository;
import org.example.repository.UserRepository;
import org.example.service.IndicatorsService;
import org.example.service.UserService;

public class CommandFactory {
    public static Command create(String command) {

        if (command.equals("login")) {
            UserService userService = new UserService(new UserRepository(new DataSourceConfig()));
            return new LoginCommand(userService);
        } else if (command.equals("logout")) {
            UserService userService = new UserService(new UserRepository(new DataSourceConfig()));
            return new LogoutCommand(userService);
        } else if (command.equals("reg")) {
            UserService userService = new UserService(new UserRepository(new DataSourceConfig()));
            return new RegCommand(userService);
        } else if (command.equals("input")) {
            IndicatorsService indicatorsService = new IndicatorsService(new IndicatorsRepository(new DataSourceConfig()));
            return new InputCommand(indicatorsService);
        } else if (command.equals("show_users")) {
            return new ShowUsersCommand();
        } else if (command.startsWith("show")) {
            IndicatorsService indicatorsService = new IndicatorsService(new IndicatorsRepository(new DataSourceConfig()));
            return new ShowCommand(indicatorsService);
        } else if (command.equals("role_admin")) {
            return new RoleAdminCommand();
        } else if (command.equals("new_ind")) {
            return new NewIndicatorCommand();
        } else if (command.equals("log")) {
            return new LogCommand();
        } else if (command.equals("show_user_ind")) {
            return new LogCommand();
        } else {
            System.out.println("Unsupported command");
            return null;
        }
    }
}
