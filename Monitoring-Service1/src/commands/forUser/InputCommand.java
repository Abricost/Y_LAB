package commands.forUser;

import commands.Command;
import indicator.Indication;
import indicator.IndicationService;
import user.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputCommand implements Command {
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        List<Indication> indications = new ArrayList<>();
        int count = 0;
        if (!IndicationService.isUserInputIndicator()) {
            System.out.println("Показания уже введены");
        } else {
            for (String indicatorName : IndicationService.indications) {
                count++;
                System.out.println(count + ") " + indicatorName);
                System.out.print("Значение: ");
                String indicatorValueString = scanner.nextLine();
                while (!isNumber(indicatorValueString)) {
                    System.out.println("Необходимо ввести числовое значение");
                    System.out.print("Значение: ");
                    indicatorValueString = scanner.nextLine();
                }
                double indicatorValue = Double.parseDouble(indicatorValueString);
                LocalDateTime dateTime = LocalDateTime.now();
                Indication indication = new Indication(indicatorName, indicatorValue, dateTime);
                indications.add(indication);
            }
            IndicationService.userIndicationMap.put(UserService.activeUser, indications);
            UserService.userLog.put(LocalDateTime.now(), "Ввод показаний пользователем " + UserService.activeUser.getUserLogin());
            System.out.println("Все показания введены");
        }
    }

    static boolean isNumber(String s ){
        return s.matches("\\d+\\.\\d+") || s.matches("\\d+");
    }
}
