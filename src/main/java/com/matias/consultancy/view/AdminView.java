package com.matias.consultancy.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.matias.consultancy.controller.LoginController;
import com.matias.consultancy.controller.UserController;
import com.matias.consultancy.model.User;
import com.matias.consultancy.model.UserDAO;

public class AdminView  extends JFrame {
    private UserDAO userDAO;
    private JTable userTable;
    private JButton logoutBtn;
    private UserController userController;
    private LoginController loginController;

    
    public AdminView(UserController userController){
        this.userController = userController;
        this.userDAO = new UserDAO();
        System.out.println("Admin view a sido creado correctamente");
        initComponents();
    }

    private void initComponents(){
        userDAO = new UserDAO();

        setTitle("Panel Administrador");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        List<User> users = userDAO.getAllUsers();
        String[] columnNames = {"ID", "Nombre", "Apellido", "Email", "Telefono", "Direccion", "Role"};
        Object[][] data = new Object[users.size()][7];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] =  user.getId();
            data[i][1] = user.getNombre();
            data[i][2] = user.getApellido();
            data[i][3] = user.getEmail();
            data[i][4] = user.getPhone();
            data[i][5] = user.getDireccion();
            data[i][6] = getRoleName(user.getRoleId());
        }

        userTable = new JTable(data, columnNames);
        add(new JScrollPane(userTable), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel();
        JButton btnEdit = new JButton("Editar Usuario");
        logoutBtn = new JButton("Cerrar Sesion");

        bottomPanel.add(btnEdit);
        bottomPanel.add(logoutBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        btnEdit.addActionListener(e -> {
            int selectRow = userTable.getSelectedRow();
            if (selectRow >= 0) {
                int userId = (int) userTable.getValueAt(selectRow, 0);
                User user = userDAO.getUserById(userId);
                if (user != null) {
                    new EditUserView(user, userDAO, userController).setVisible(true); // Pasar userController
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el usuario.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un Usuario para Editar");
            }
        });
        
        logoutBtn.addActionListener(e -> {
            if (userController != null) {
                userController.logout(this);
            }
        });
        

        setVisible(true);
    }

    public void cargarUsuarios() {
        System.out.println("Ejecutando metodo de cargarUsuarios");
        List<User> users = userDAO.getAllUsers();
        String[] columnNames = {"ID", "Nombre", "Apellido", "Email", "Telefono", "Direccion", "Role"};
        Object[][] data = new Object[users.size()][7];
    
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getNombre();
            data[i][2] = user.getApellido();
            data[i][3] = user.getEmail();
            data[i][4] = user.getPhone();
            data[i][5] = user.getDireccion();
            data[i][6] = getRoleName(user.getRoleId());
        }
    
        userTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames)); 
        userTable.repaint();
    }

    private String getRoleName(int roledId){
        return switch (roledId) {
            case 1 -> "Administrador";
            case 2 -> "Usuario";
            default -> "Desconocido";
        };
    }
}
