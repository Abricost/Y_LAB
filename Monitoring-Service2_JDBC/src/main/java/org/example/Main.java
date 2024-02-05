package org.example;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.commands.Command;
import org.example.commands.CommandFactory;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.DataSourceConfig;
import org.example.repository.IndicatorsRepository;
import org.example.repository.UserRepository;
import org.example.service.IndicatorsService;
import org.example.service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Docker:
// docker pull postgres:latest
// docker run -d --name mypostgres -p 5432:5432 -e POSTGRES_PASSWORD=pass postgres:latest

public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            Connection connection = dataSourceConfig.getConnection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            String changeLogFile = "db.changelog/changelog.xml"; //!!!!&!&!&!&!@#!@@!$
            Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Migration is completed successfully");
        } catch (SQLException | LiquibaseException e) {
            System.out.println("SQL Exception in migration: " + e.getMessage());
        }

        List<String> publicCommands = new ArrayList<>(List.of("login", "reg", "exit"));
        List<String> userCommands = new ArrayList<>(List.of("input", "show", "logout"));
        List<String> adminCommands = new ArrayList<>(List.of("show_users", "role_admin", "log", "new_ind", "show_user_ind"));

        System.out.println("\nСервис для подачи показаний");
        System.out.println("Необходимо войти или зарегистрироваться");
        System.out.println("Команды: login - войти в систему; reg - регистрация; exit - выход");

        UserService userService = new UserService(new UserRepository(new DataSourceConfig()));

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("exit")) break;

            if (userCommands.contains(command) && UserService.activeLogin == null && !publicCommands.contains(command)) {
                System.out.println("Требуется авторизация");
                continue;
            }
            if (adminCommands.contains(command) && !userService.isUserAdmin(userService.getActiveLogin()) && !publicCommands.contains(command)) {
                System.out.println("Требуются права администратора");
                continue;
            }

            Command commandEx = CommandFactory.create(command);
            if (commandEx != null) commandEx.execute();

        }

    }
}