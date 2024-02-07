package org.example.commands.forUser;

import org.example.commands.Command;
import org.example.repository.DataSourceConfig;
import org.example.repository.UserRepository;
import org.example.service.UserService;

public class LogoutCommand implements Command {

    private final UserService userService;

    public LogoutCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("Пользователь " + UserService.activeLogin + " вышел из системы");
        userService.setActiveLogin(null);
    }
}
