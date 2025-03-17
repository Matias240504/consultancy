package com.matias.consultancy;

import com.matias.consultancy.controller.LoginController;
import com.matias.consultancy.controller.UserController;

public class Main {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        UserController userController = new UserController(loginController); 
    }
}