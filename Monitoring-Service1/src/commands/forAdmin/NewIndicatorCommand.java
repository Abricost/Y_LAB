package commands.forAdmin;

import commands.Command;
import indicator.IndicationService;
import user.UserService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class NewIndicatorCommand implements Command {
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        IndicationService indicationService = new IndicationService();
        System.out.print("Показатель: ");
        String newIndicator = scanner.nextLine();
        if (!newIndicator.isEmpty()) {
            indicationService.addIndicator(newIndicator);
            UserService.userLog.put(LocalDateTime.now(), "Администратором " + UserService.activeUser.getUserLogin() + " добавлен показатель " + newIndicator);
        } else {
            System.out.println("Поле не может быть пустым");
        }
    }
}
