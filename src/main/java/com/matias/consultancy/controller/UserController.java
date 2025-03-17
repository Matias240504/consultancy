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
    private AdminView adminView;
    private LoginController loginController;  

    public UserController(LoginController loginController) {
        this.userDAO = new UserDAO();
        this.loginController = loginController;  
        this.loginView = new LoginView(this, loginController);  
        loginView.setVisible(true);
    }

    public void loginUser(String email, String password) {
        System.out.println("Entrando a loginUser()");
        User user = userDAO.authenticateUser(email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(null, "Bienvenido, " + user.getNombre() + "!", "Inicio Exitoso", JOptionPane.INFORMATION_MESSAGE);
            loginView.dispose();

            if (user.getRoleId() == 1) { // Admin
                if (this.adminView == null) {
                    this.adminView = new AdminView(this);
                    System.out.println("AdminView inicializado correctamente");
                }
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
            if (loginView == null || !loginView.isVisible()) {
                loginView = new LoginView(this, loginController);
                loginView.setVisible(true);
            }
        }
    }

    public boolean updateUsers(User user) {
        boolean actualizado = userDAO.updateUsers(user);

        int option = JOptionPane.showConfirmDialog(null,"¿Seguro que quieres actualizar los datos?", "Revisa Corectamente los Datos", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            if (actualizado) {  
                JOptionPane.showMessageDialog(null, "Datos Actualizados Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR al Actualizar Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } 
        return actualizado;
    }

    public AdminView getAdminView() {
        if (adminView == null) {
            System.out.println("ERROR: AdminView sigue siendo null cuando se necesita");
        } else {
            System.out.println("AdminView devuelto correctamente");
        }
        return adminView;
    }  
}
