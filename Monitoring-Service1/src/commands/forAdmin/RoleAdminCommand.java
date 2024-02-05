package commands.forAdmin;

import commands.Command;
import user.Role;
import user.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class RoleAdminCommand implements Command {
    @Override
    public void execute() {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Какого пользователя назначить администратором?");
        System.out.print("Login пользователя: ");
        String userlogin = scanner.nextLine();
        userService.changeUserRole(userService.getUserByLogin(userlogin), Role.ROLE_ADMIN);
        UserService.userLog.put(LocalDateTime.now(), "Администратор " + UserService.activeUser.getUserLogin() + " назначил пользователя " + userlogin + " администратор");
    }
}
