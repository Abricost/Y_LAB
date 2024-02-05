package commands.forUser;

import commands.Command;
import indicator.IndicationService;
import user.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ShowCommand implements Command {
    private String command;

    public ShowCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute() {
        IndicationService indicationService = new IndicationService();
        if (command.equals("show")) {
            indicationService.showAllUserIndicators();
            UserService.userLog.put(LocalDateTime.now(), "Запрос всех показаний пользователем " + UserService.activeUser.getUserLogin());
        } else {
            //show 2024 JANUARY
            String year = command.substring(5, 9);
            String month = command.substring(10).toUpperCase();
            if (!(isYear(year) && isMonth(month))) {
                System.out.println("Некорректная команда\nПример команды: show 2024 JANUARY");
            } else {
                indicationService.showSelectedIndicators(Integer.parseInt(year), Month.valueOf(month));
                UserService.userLog.put(LocalDateTime.now(), "Запрос показаний пользователя " + UserService.activeUser.getUserLogin() + " за "+ year + " год и " + month + " месяц");
            }

        }
    }

    boolean isYear(String year) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        try {
            simpleDateFormat.parse(year);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    boolean isMonth(String month) {
        List<String> monthList = new ArrayList<>(List.of(
                "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
                "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"));
        return monthList.contains(month);
    }
}
