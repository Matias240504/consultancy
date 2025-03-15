package com.matias.consultancy.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.matias.consultancy.model.User;
import com.matias.consultancy.model.UserDAO;
import com.matias.consultancy.view.AdminView;
import com.matias.consultancy.view.LoginView;
import com.matias.consultancy.view.UserView;

public class UserController {
    private UserDAO userDAO;
    private LoginView loginView;
    private LoginController loginController;  

    public UserController(LoginController loginController) {
        this.userDAO = new UserDAO();
        this.loginController = loginController;  
        this.loginView = new LoginView(this, loginController);  
        loginView.setVisible(true);
    }

    public void loginUser(String email, String password) {
        User user = userDAO.authenticateUser(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(null, "Bienvenido, " + user.getNombre() + "!", "Inicio Exitoso", JOptionPane.INFORMATION_MESSAGE);
            loginView.dispose();

            if (user.getRoleId() == 1) { // Admin
                new AdminView(this).setVisible(true);
            } else {
                new UserView(user, this, loginController).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales Incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void logout(JFrame currentView) {
        int option = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            currentView.dispose();
            new LoginView(this, loginController).setVisible(true);
        }
    }

    public void updateUsers(User user) {
        if (userDAO.updateUsers(user)) {  
            JOptionPane.showMessageDialog(null, "Datos Actualizados Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR al Actualizar Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
