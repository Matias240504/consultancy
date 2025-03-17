package com.matias.consultancy.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.matias.consultancy.controller.LoginController;
import com.matias.consultancy.controller.UserController;
import com.matias.consultancy.model.User;
import com.matias.consultancy.model.UserDAO;

public class UserView extends JFrame {
    private User user;
    private UserDAO userDAO;
    private JTextField nombreJTF, apellidoJTF, phoneJTF, direccionJTF;
    private JButton btnguardar, logoutbtn;
    private UserController userController;
    private LoginController loginController;

    public UserView(User user, UserController userController, LoginController loginController) { 
        this.user = user;
        this.userController = userController;
        this.loginController = loginController;
        this.userDAO = new UserDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Mi perfil");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel tituloLabel = new JLabel("Datos", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(tituloLabel, gbc);

        // Label y campo Nombre
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Ingrese Nombre:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        nombreJTF = new JTextField(10);
        nombreJTF.setText(user.getNombre());
        add(nombreJTF, gbc);

        // Label y campo Apellido
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ingrese Apellido:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        apellidoJTF = new JTextField(10);
        apellidoJTF.setText(user.getApellido());
        add(apellidoJTF, gbc);

        // Label y campo Teléfono
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ingrese Teléfono:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        phoneJTF = new JTextField(10);
        phoneJTF.setText(user.getPhone());
        add(phoneJTF, gbc);

        // Label y campo Dirección
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Ingrese Dirección:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        direccionJTF = new JTextField(10);
        direccionJTF.setText(user.getDireccion());
        add(direccionJTF, gbc);

        // Botón Guardar
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnguardar = new JButton("Guardar Datos");
        btnguardar.setBackground(new Color(30, 144, 255));
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFocusPainted(false);
        btnguardar.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnguardar, gbc);

        // Botón Logout
        gbc.gridy = 6;
        logoutbtn = new JButton("Cerrar Sesión");
        logoutbtn.setBackground(new Color(30, 144, 255));
        logoutbtn.setForeground(Color.WHITE);
        logoutbtn.setFocusPainted(false);
        logoutbtn.setFont(new Font("Arial", Font.BOLD, 14));
        add(logoutbtn, gbc);

        btnguardar.addActionListener(e -> {

            String nombre = nombreJTF.getText().trim();
            String apellido = apellidoJTF.getText().trim();
            String phone = phoneJTF.getText().trim();
            String direccion = direccionJTF.getText().trim();

            // Validaciones
            if (!esTextoValido(nombre)) {
                JOptionPane.showMessageDialog(this, "El nombre solo puede contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!esTextoValido(apellido)) {
                JOptionPane.showMessageDialog(this, "El apellido solo puede contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!esTelefonoValido(phone)) {
                JOptionPane.showMessageDialog(this, "El teléfono debe contener exactamente 9 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setPhone(phone);
            user.setDireccion(direccion);

            //boolean actualizado = userController.updateUsers(user);
            userController.updateUsers(user);

            
        });

        logoutbtn.addActionListener(e -> {
            if (userController != null) {
                userController.logout(this);
            }
        });

        setVisible(true);
    }

    // Validar que el campo solo contenga letras y espacios
    private boolean esTextoValido(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }

    // Validar que el telefono contenga exactamente 9 dígitos numericos
    private boolean esTelefonoValido(String phone) {
        return phone.matches("^\\d{9}$");
    }

}
