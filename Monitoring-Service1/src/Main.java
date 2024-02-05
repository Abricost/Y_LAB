import commands.Command;
import commands.CommandFactory;
import user.Role;
import user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Сервис для подачи показаний");
        System.out.println("Необходимо войти или зарегистрироваться");
        System.out.println("Команды: login - войти в систему; reg - регистрация; exit - выход");
        UserService userService = new UserService();
        userService.createUser("Admin", "admin", Role.ROLE_ADMIN);

        ArrayList<String> userCommands = new ArrayList<>(List.of("input", "show", "logout"));
        ArrayList<String> adminCommands = new ArrayList<>(List.of("show_users", "role_admin", "log", "new_ind", "show_user_ind"));

        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            if (userCommands.contains(command) && !UserService.isUserLogged) {
                System.out.println("Требуется авторизация");
                continue;
            }
            if (adminCommands.contains(command) && !UserService.isUserAdmin) {
                System.out.println("Требуются права администратора");
                continue;
            }

            Command command1 = CommandFactory.create(command);
            if (command1 != null) command1.execute();



//            if (command.equals("login")) {
//
//                System.out.println("LOGIN");
//                System.out.print("Логин: ");
//                String login = scanner.nextLine();
//                System.out.print("Пароль: ");
//                String password = scanner.nextLine();
//                if (userService.userLogin(login, password)) {
//                    System.out.println("успешный вход");
//
//                } else {
//                    System.out.println("неверные данные");
//                }
//            } else if (command.equals("reg")) {
//                System.out.println("REGISTRATION");
//                System.out.print("Логин: ");
//                String login = scanner.nextLine();
//                System.out.print("Пароль: ");
//                String password = scanner.nextLine();
//                userService.createUser(login, password, Role.ROLE_USER);
//            } else if (command.equals("exit")) {
//                break;
//            } else {
//                System.out.println("Команда " + command + " не существует.");
//            }

        }
    }

}