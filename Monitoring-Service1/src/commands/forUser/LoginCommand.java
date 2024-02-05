package commands.forUser;

import commands.Command;
import user.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class LoginCommand implements Command {
    @Override
    public void execute() {

        //TODO в конструктор
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Логин: ");
        String login = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        if (userService.isCorrectUser(login, password)) {
            UserService.activeUser = userService.getUserByLogin(login);
            UserService.isUserLogged = true;
            if (userService.isUserAdmin(login)) {
                UserService.isUserAdmin = true;
                System.out.println("Пользователь " + login + " является администратором");
            }
            System.out.println("успешный вход");
            UserService.userLog.put(LocalDateTime.now(), "Вход пользователя " + login);
        } else {
            System.out.println("неверные данные");
        }
    }
}
