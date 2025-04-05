package dao;

import model.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDAO implements InterfaceDAO<Class> {

    @Override
    public void add(Class aClass) {
        String sql = "INSERT INTO classes (course_id, teacher_id, max_students) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aClass.getCourseId());
            stmt.setInt(2, aClass.getTeacherId());
            stmt.setInt(3, aClass.getMaxStudents());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Class added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Class aClass) {
        String sql = "UPDATE classes SET course_id = ?, teacher_id = ?, max_students = ? WHERE class_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aClass.getCourseId());
            stmt.setInt(2, aClass.getTeacherId());
            stmt.setInt(3, aClass.getMaxStudents());
            stmt.setInt(4, aClass.getClassId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Class updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Class aClass) {
        String sql = "DELETE FROM classes WHERE class_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aClass.getClassId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Class deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class selectById(Class aClass) {
        String sql = "SELECT * FROM classes WHERE class_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aClass.getClassId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Class(rs.getInt("class_id"),
                        rs.getInt("course_id"),
                        rs.getInt("teacher_id"),
                        rs.getInt("max_students"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class selectByName(String name) {
        // If you're looking for a class by name, you'd need to join with a courses table to search by course name
        // assuming 'name' is related to the course name.
        String sql = "SELECT * FROM classes c JOIN courses co ON c.course_id = co.course_id WHERE co.course_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Class(rs.getInt("class_id"),
                        rs.getInt("course_id"),
                        rs.getInt("teacher_id"),
                        rs.getInt("max_students"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Class> selectAll() {
        ArrayList<Class> classes = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                classes.add(new Class(rs.getInt("class_id"),
                        rs.getInt("course_id"),
                        rs.getInt("teacher_id"),
                        rs.getInt("max_students")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public ArrayList<Class> selectByCondition(Class aClass) {
        ArrayList<Class> classes = new ArrayList<>();
        String sql = "SELECT * FROM classes WHERE course_id = ? AND teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aClass.getCourseId());
            stmt.setInt(2, aClass.getTeacherId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                classes.add(new Class(rs.getInt("class_id"),
                        rs.getInt("course_id"),
                        rs.getInt("teacher_id"),
                        rs.getInt("max_students")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
