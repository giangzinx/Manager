package dao;

import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDAO implements InterfaceDAO<Teacher> {

    @Override
    public void add(Teacher teacher) {
        String sql = "INSERT INTO teachers (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for PreparedStatement
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getPassword());

            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Teacher added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE teachers SET name = ?, email = ?, password = ? WHERE teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for PreparedStatement
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getPassword());
            stmt.setInt(4, teacher.getTeacherId());

            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Teacher updated successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Teacher teacher) {
        String sql = "DELETE FROM teachers WHERE teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for PreparedStatement
            stmt.setInt(1, teacher.getTeacherId());

            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Teacher deleted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher selectById(Teacher teacher) {
        String sql = "SELECT * FROM teachers WHERE teacher_id = ?";
        Teacher result = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacher.getTeacherId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Teacher selectByName(String name) {
        String sql = "SELECT * FROM teachers WHERE name = ?";
        Teacher result = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<Teacher> selectAll() {
        String sql = "SELECT * FROM teachers";
        ArrayList<Teacher> teachers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }

    @Override
    public ArrayList<Teacher> selectByCondition(Teacher teacher) {
        String sql = "SELECT * FROM teachers WHERE email LIKE ?";
        ArrayList<Teacher> teachers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + teacher.getEmail() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teachers;
    }
}
