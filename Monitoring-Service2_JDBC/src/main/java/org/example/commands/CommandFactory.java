package org.example.commands;

import org.example.commands.forAdmin.LogCommand;
import org.example.commands.forAdmin.NewIndicatorCommand;
import org.example.commands.forAdmin.RoleAdminCommand;
import org.example.commands.forAdmin.ShowUsersCommand;
import org.example.commands.forUser.*;

public class CommandFactory {
    public static Command create(String command) {
        if (command.equals("login")) {
            return new LoginCommand();
        } else if (command.equals("logout")) {
            return new LogoutCommand();
        } else if (command.equals("reg")) {
            return new RegCommand();
        } else if (command.equals("input")) {
            return new InputCommand();
        } else if (command.equals("show_users")) {
            return new ShowUsersCommand();
        } else if (command.startsWith("show")) {
            return new ShowCommand();
        } else if (command.equals("role_admin")) {
            return new RoleAdminCommand();
        } else if (command.equals("new_ind")) {
            return new NewIndicatorCommand();
        } else if (command.equals("log")) {
            return new LogCommand();
        } else if (command.equals("show_user_ind")) {
            return new LogCommand();
        } else {
            System.out.println("Unsupported command");
            return null;
        }
    }
}
