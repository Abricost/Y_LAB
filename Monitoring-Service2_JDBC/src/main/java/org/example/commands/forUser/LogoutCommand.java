package org.example.commands.forUser;

import org.example.commands.Command;
import org.example.repository.DataSourceConfig;
import org.example.repository.UserRepository;
import org.example.service.UserService;

public class LogoutCommand implements Command {
    @Override
    public void execute() {
        UserService userService = new UserService(new UserRepository(new DataSourceConfig()));
        userService.setActiveLogin(null);
    }
}
