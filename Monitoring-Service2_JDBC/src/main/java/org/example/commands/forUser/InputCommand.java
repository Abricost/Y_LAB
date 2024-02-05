package org.example.commands.forUser;

import org.example.commands.Command;
import org.example.service.IndicatorsService;
import org.example.service.UserService;

import java.util.List;
import java.util.Scanner;

public class InputCommand implements Command {

    private final IndicatorsService indicatorsService;

    public InputCommand(IndicatorsService indicatorsService) {
        this.indicatorsService = indicatorsService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        for (String name : indicatorsService.getAllIndicatorsList()) {
            if (indicatorsService.isUserCanInputIndicator(name)) {
                System.out.print("IND: " + name + ": ");
                String valueString = scanner.nextLine();
                while (!isNumber(valueString)) {
                    System.out.println("Необходимо ввести числовое значение");
                    System.out.print("IND: " + name + ": ");
                    valueString = scanner.nextLine();
                }
                double value = Double.parseDouble(valueString);
                indicatorsService.saveIndicator(name, value);
                System.out.println("Показатель " + name + " со значением " + value + " сохранён.");
            }

        }
        System.out.println("Больше нет показателей доступных для заполнения.");
    }

    boolean isNumber(String s ){
        return s.matches("\\d+\\.\\d+") || s.matches("\\d+");
    }
}
