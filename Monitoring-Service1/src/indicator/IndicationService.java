package indicator;

import user.User;
import user.UserService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class IndicationService {

    public static Set<String> indications = new HashSet<>(List.of("Горячая вода", "Холодная вода"));
    public static Map<User, List<Indication>> userIndicationMap = new HashMap<>();

    public boolean addIndicator(String indicatorName) {
        if (indications.contains(indicatorName)) {
            System.out.println("Показатель " + indicatorName + " уже существует");
            return false;
        }
        indications.add(indicatorName);
        System.out.println("Добавлен показатель " + indicatorName);
        return true;
    }

    public void showAllUserIndicators() {
        for (Map.Entry<User, List<Indication>> entry : userIndicationMap.entrySet()) {
            if (entry.getKey().equals(UserService.activeUser)) {
                for (Indication indication : entry.getValue()){
                    System.out.println(indication.getIndicationName() + ": "
                            + indication.getIndicationValue()
                            + "; дата: " + indication.getIndicationDate().getYear()
                            + " " + indication.getIndicationDate().getMonth());
                }
            }
        }
    }

    public void showSelectedIndicators(int year, Month month) {
        for (Map.Entry<User, List<Indication>> entry : userIndicationMap.entrySet()) {
            if (entry.getKey().equals(UserService.activeUser)) {
                for (Indication indication : entry.getValue()){
                    if (year == indication.getIndicationDate().getYear() && month == indication.getIndicationDate().getMonth()) {
                        System.out.println(indication.getIndicationName() + ": "
                                + indication.getIndicationValue()
                                + "; дата: " + indication.getIndicationDate().getYear()
                                + " " + indication.getIndicationDate().getMonth());
                    }
                }
            }
        }
    }

    public static boolean isUserInputIndicator() {
        for (Map.Entry<User, List<Indication>> entry : userIndicationMap.entrySet()) {
            if (entry.getKey().equals(UserService.activeUser)) {
                for (Indication indication : entry.getValue()){
                    return !(indication.getIndicationDate().getYear() == LocalDateTime.now().getYear()
                            && indication.getIndicationDate().getMonth() == LocalDateTime.now().getMonth());
                }
            }
        }
        return true;
    }

    public void showUserIndicatorByLogin(String login) {
        for (Map.Entry<User, List<Indication>> entry : userIndicationMap.entrySet()) {
            if (entry.getKey().getUserLogin().equals(login)) {
                for (Indication indication : entry.getValue()) {
                    System.out.println(indication);
                }
            }
        }
    }

}
