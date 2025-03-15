package com.matias.consultancy.controller;

import com.matias.consultancy.model.User;
import com.matias.consultancy.model.UserDAO;    

public class LoginController {
    private UserDAO userDAO;
    private UserController userController; // Agregamos UserController

    public LoginController() {
        this.userDAO = new UserDAO();
        this.userController = new UserController(this); // Pasamos this para conectar con LoginController
    }

    public User authenticate(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public UserController getUserController() {  // Nuevo método para obtener UserController
        return userController;
    }
}
