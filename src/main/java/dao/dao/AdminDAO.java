package dao.dao;

import dao.DatabaseConnection;
import dao.InterfaceDAO;
import model.Admin;
import ui.AdminUI;
import ui.SuperAdminUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO implements InterfaceDAO<Admin> {
    public void getCheckAdmin(String name, String password) {
        String sql = "SELECT * FROM admins WHERE name= ?";
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Phải có rs.next() trước khi lấy dữ liệu từ ResultSet
                if (rs.getString("name").equals(name) && rs.getString("password").equals(password)) {
                    if(rs.getBoolean("super")) {
                        SuperAdminUI.indexSuperAdmins();
                    }
                    else {
                        AdminUI.indexAdmins();
                    }
                } else {
                    System.out.println("Wrong email or password");
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Admin admin) {
        String sql = "INSERT INTO admins (name, super, password) VALUES (?, ?, ?)";
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getName());
            stmt.setBoolean(2,admin.isSuperAdmin());
            stmt.setString(3, admin.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Admin admin) {
        String sql = "UPDATE admins SET password = ?, super = ? WHERE name = ?";
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getPassword());
            stmt.setBoolean(2, admin.isSuperAdmin());
            stmt.setString(3, admin.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Admin admin) {
        String sql = "DELETE FROM admins WHERE name = ?";
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin selectById(Admin admin) {
        String sql = "SELECT * FROM admins WHERE admin_id = ?";
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, admin.getAdmin_id());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getBoolean("super"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin selectByName(String name) {
        String sql = "SELECT * FROM admins WHERE name = ?";
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getBoolean("super"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Admin> selectAll() {
        ArrayList<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins";
        try (Connection conn = dao.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getBoolean("super"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public ArrayList<Admin> selectByCondition(Admin admin) {
        ArrayList<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins WHERE super = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, admin.isSuperAdmin());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getBoolean("super"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
}
