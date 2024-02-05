package commands.forAdmin;

import commands.Command;
import indicator.IndicationService;
import user.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ShowUserIndicatorsCommand implements Command {
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        IndicationService indicationService = new IndicationService();

        System.out.print("Пользователь: ");
        String login = scanner.nextLine();
        indicationService.showUserIndicatorByLogin(login);

        UserService.userLog.put(LocalDateTime.now(), "Администратор " + UserService.activeUser.getUserLogin() + " запросил показания пользователя " + login);
    }
}
