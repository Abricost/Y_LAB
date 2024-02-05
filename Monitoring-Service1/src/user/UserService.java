package user;

import java.time.LocalDateTime;
import java.util.*;

public class UserService {
    final static Map<User, Role> userList = new HashMap<>();

    public static Map<User, Boolean> userCanInputIndicatorsMap = new HashMap<>();
    public static User activeUser;
    public static boolean isUserLogged = false;
    public static boolean isUserAdmin = false;

    public static Map<LocalDateTime, String> userLog = new TreeMap<>();

    public boolean createUser(String login, String password, Role role) {
        login = login.toLowerCase();
        User user = new User(login, password);
        if (isUserExists(login)) {
            System.out.println("Пользователь уже существует");
        } else {
            userList.put(user, role);
            System.out.println("Создан пользователь " + login);
        }
        return true;
    }

    public boolean isCorrectUser(String login, String password) {
        for (Map.Entry<User, Role> entry : userList.entrySet()) {
            if (entry.getKey().getUserLogin().equals(login.toLowerCase())) {
                String passwordReal = entry.getKey().getUserPassword();
                return password.equals(passwordReal);
            }
        }
        return false;
    }

    public static void showAllUsers() {
        for (Map.Entry<User, Role> entry : userList.entrySet()) {
            System.out.println(entry);
        }
    }

    public User getUserByLogin(String login) {
        for (Map.Entry<User, Role> entry : userList.entrySet()) {
            if (entry.getKey().getUserLogin().equals(login)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean isUserExists(String login) {
        for (Map.Entry<User, Role> entry : userList.entrySet()) {
            if (entry.getKey().getUserLogin().equals(login.toLowerCase()))
                return true;
        }
        return false;
    }

    public boolean isUserAdmin(String login) {
        for (Map.Entry<User, Role> entry : userList.entrySet()) {
            if (entry.getKey().getUserLogin().equals(login.toLowerCase())) {
                if (entry.getValue().equals(Role.ROLE_ADMIN)) return true;
            }
        }
        return false;
    }

    public void changeUserRole(User user, Role role) {
        if (user != null) userList.put(user, role);
    }

}
