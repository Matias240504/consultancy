package com.matias.consultancy.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.matias.consultancy.controller.LoginController;
import com.matias.consultancy.controller.UserController;

public class LoginView extends JFrame {
    private JTextField emailJTF;
    private JPasswordField passwordField;
    private JButton btnLogin;
    private LoginController loginController;
    private UserController userController;

    public LoginView(UserController userController, LoginController loginController) {
    this.userController = userController;
    this.loginController = loginController;
    initComponents();
}

    private void initComponents() {
        setTitle("Login Consultancy");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // titulo
        JLabel tituloLabel = new JLabel("Consultancy", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(tituloLabel, gbc);

        // label correo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ingrese Correo:"), gbc);

        // campo de entrada de correo
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        emailJTF = new JTextField();
        add(emailJTF, gbc);

        // label contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0; 
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ingrese Contraseña:"), gbc);

        // campo de entrada de contraseña
        gbc.gridx = 1;
        gbc.gridy = 2;
        passwordField = new JPasswordField();
        add(passwordField, gbc);

        // boton de login
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnLogin = new JButton("Iniciar Sesión");

        // estilos del boton
        btnLogin.setBackground(new Color(30, 144, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnLogin, gbc);

        btnLogin.addActionListener(e -> {
            System.out.println("Se presiono el boton de login");
                String email = emailJTF.getText();
                String password = new String(passwordField.getPassword());

                if (loginController == null) {
                    System.out.println("loginController es NULL en LoginView.");
                } else {
                    System.out.println("loginController existe.");
                }
        
                if(userController != null){
                    userController.loginUser(email, password);
                } 
        });
        

        setVisible(true);

    }
}
