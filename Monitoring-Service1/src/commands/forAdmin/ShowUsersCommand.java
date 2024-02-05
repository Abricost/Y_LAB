package commands.forAdmin;

import commands.Command;
import user.UserService;

import java.time.LocalDateTime;

public class ShowUsersCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Список пользователей:");
        UserService.showAllUsers();
        UserService.userLog.put(LocalDateTime.now(), "Администратор " + UserService.activeUser.getUserLogin() + " запрашивает список пользователей");
    }
}
