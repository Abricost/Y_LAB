package org.example.commands.forUser;

import org.example.commands.Command;
import org.example.model.Role;
import org.example.repository.DataSourceConfig;
import org.example.repository.UserRepository;
import org.example.service.UserService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RegCommand implements Command {
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Логин: ");
        String login = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        UserService userService = new UserService(new UserRepository(new DataSourceConfig()));

        if (login.isEmpty()) {
            System.out.println("Логин не может быть пустым.");
        } else if (userService.isLoginExists(login)) {
            System.out.println("Пользователь с логином " + login + " уже существует.");
        } else {
            try {
                userService.createUser(login, password, Role.ROLE_USER);
                System.out.println("Зарегистрирован пользователь " + login);
            } catch (SQLException e) {
                System.out.println("Ошибка " + e.getMessage() + ".\nНе удалось создать пользователя " + login + ".");
            }
        }

    }
}
