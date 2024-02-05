package commands.forUser;

import commands.Command;
import user.Role;
import user.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class RegCommand implements Command {
    @Override
    public void execute() {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Логин: ");
        String login = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        if (login.isEmpty()) {
            System.out.println("Логин не может быть пустым!");
        } else {
            userService.createUser(login, password, Role.ROLE_USER);
            System.out.println("Зарегистрирован пользователь " + login);
            UserService.userLog.put(LocalDateTime.now(), "Регистрация пользователя " + login);
        }
    }
}
