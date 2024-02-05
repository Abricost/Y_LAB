package org.example.commands.forUser;

import org.example.commands.Command;
import org.example.model.Role;
import org.example.repository.DataSourceConfig;
import org.example.repository.UserRepository;
import org.example.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginCommand implements Command {
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
        } else if (!userService.isLoginExists(login)) {
            System.out.println("Пользователь с логином " + login + " не существует.");
        } else if (!userService.isCorrectUser(login, password)) {
            System.out.println("Неверный пароль");
        } else {
            userService.setActiveLogin(login);
            System.out.println("Пользователь " + login + " вошёл в систему.");
            System.out.println("Успешный вход.");
        }
    }
}
