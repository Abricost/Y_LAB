package org.example.commands.forUser;

import org.example.commands.Command;
import org.example.model.Role;
import org.example.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class RegCommand implements Command {

    private final UserService userService;

    public RegCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Логин: ");
        String login = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

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
