package com.matias.consultancy.controller;

import com.matias.consultancy.model.User;
import com.matias.consultancy.model.UserDAO;

public class LoginController {
    private UserDAO userDAO;

    public LoginController(){
        userDAO = new UserDAO();    
    }

    public User authenticate(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
