package org.example.commands;

import org.example.service.UserService;

import java.sql.SQLException;

public abstract class CommandAbs {

    UserService userService;

    public CommandAbs(UserService userService) {
        this.userService = userService;
    }

    public abstract void execute();
}
