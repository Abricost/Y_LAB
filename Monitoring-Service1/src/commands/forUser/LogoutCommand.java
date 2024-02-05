package commands.forUser;

import commands.Command;
import user.UserService;

import java.time.LocalDateTime;

public class LogoutCommand implements Command {
    @Override
    public void execute() {
        UserService.userLog.put(LocalDateTime.now(), "Выход пользователя " + UserService.activeUser.getUserLogin());
        UserService.isUserLogged = false;
        UserService.activeUser = null;
        System.out.println("Разлогинивание");
    }
}
