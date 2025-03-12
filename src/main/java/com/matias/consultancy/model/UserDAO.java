package com.matias.consultancy.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.matias.consultancy.config.DatabaseConfig;

public class UserDAO {
    //metodo para obtener usuario por email
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("direccion"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //metodo para obtener todos los usuarios
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConfig.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    users.add(new User(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("direccion"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return users;
    }

    //metodo para actualizar usuario
    public boolean udpateUsers(User user){
        String sql = "UPDATE users SET nombre = ?, apellido = ?, email = ?, phone = ?, direccion = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getNombre());
                stmt.setString(2, user.getApellido());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getPhone());
                stmt.setInt(5, user.getId());
                return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }
}
