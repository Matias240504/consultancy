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

import com.matias.consultancy.controller.UserController;
import com.matias.consultancy.model.User;
import com.matias.consultancy.model.UserDAO;

public class EditUserView extends JFrame {
    private User user;
    private UserDAO userDAO;
    private JTextField nombreJTF, apellidoJTF, phoneJTF, direccionJTF;
    private JButton btnguardar;
    private UserController userController;

    // Constructor que recibe un usuario
    public EditUserView(User user, UserDAO userDAO, UserController userController) {
        this.user = user; // Asignamos el usuario recibido
        this.userDAO = userDAO; // Inicializamos UserDAO
        this.userController = userController;

        setTitle("Editar Usuario");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel tituloLabel = new JLabel("Editar Datos", JLabel.CENTER);
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
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        nombreJTF = new JTextField(10);
        nombreJTF.setText(user.getNombre()); // Cargar el valor actual
        add(nombreJTF, gbc);

        // Label y campo Apellido
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Apellido:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        apellidoJTF = new JTextField(10);
        apellidoJTF.setText(user.getApellido()); // Cargar el valor actual
        add(apellidoJTF, gbc);

        // Label y campo Teléfono
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Teléfono:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        phoneJTF = new JTextField(10);
        phoneJTF.setText(user.getPhone()); // Cargar el valor actual
        add(phoneJTF, gbc);

        // Label y campo Dirección
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Dirección:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        direccionJTF = new JTextField(10);
        direccionJTF.setText(user.getDireccion()); // Cargar el valor actual
        add(direccionJTF, gbc);

        // Botón Guardar
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnguardar = new JButton("Guardar Cambios");
        btnguardar.setBackground(new Color(30, 144, 255));
        btnguardar.setForeground(Color.WHITE);
        btnguardar.setFocusPainted(false);
        btnguardar.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnguardar, gbc);

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

            boolean actualizado = userController.updateUsers(user);

            if (actualizado) {
                if (userController.getAdminView() != null) {
                    userController.getAdminView().cargarUsuarios();
                } 

                dispose(); // Cierra la ventana después de guardar
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar usuario.");
            }
        });

        setVisible(true);
    }

    // Validar que el campo solo contenga letras y espacios
    private boolean esTextoValido(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }

    // Validar que el teléfono contenga exactamente 9 dígitos numéricos
    private boolean esTelefonoValido(String phone) {
        return phone.matches("^\\d{9}$");
    }

}
