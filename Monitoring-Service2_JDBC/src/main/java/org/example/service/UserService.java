package org.example.service;

import org.example.model.Role;
import org.example.model.User;
import org.example.repository.UserRepository;

import java.sql.SQLException;

public class UserService {

    private String activeLogin;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getActiveLogin() {
        return activeLogin;
    }

    public void setActiveLogin(String activeLogin) {
        this.activeLogin = activeLogin;
    }

    public void createUser(String login, String password, Role role) throws SQLException {
        login = login.toLowerCase();
        User user = new User(login, password, role);
        userRepository.createUser(user);
    }

    public boolean isLoginExists(String login) {
        return userRepository.isLoginExists(login);
    }

    public boolean isCorrectUser(String login, String password) {
        return userRepository.isCorrectUser(login,password);
    }

    public boolean isUserAdmin(String login) {
        return userRepository.isUserAdmin(login);
    }

}
