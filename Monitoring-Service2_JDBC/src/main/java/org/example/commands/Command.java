package org.example.commands;

import org.example.service.UserService;

import java.sql.SQLException;

public interface Command {

    public void execute() throws SQLException;
}
