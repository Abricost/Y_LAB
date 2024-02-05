package commands.forAdmin;

import commands.Command;
import user.Role;
import user.User;
import user.UserService;

import java.time.LocalDateTime;
import java.util.Map;

public class LogCommand implements Command {
    @Override
    public void execute() {
        UserService.userLog.put(LocalDateTime.now(), "Администратор " + UserService.activeUser.getUserLogin() + " запрашивает лог");
        for (Map.Entry<LocalDateTime, String> entry : UserService.userLog.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
