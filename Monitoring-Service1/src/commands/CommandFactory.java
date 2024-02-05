package commands;

import commands.forAdmin.LogCommand;
import commands.forAdmin.NewIndicatorCommand;
import commands.forAdmin.RoleAdminCommand;
import commands.forAdmin.ShowUsersCommand;
import commands.forUser.*;

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
            return new ShowCommand(command);
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
