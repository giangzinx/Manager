package dao;

import model.ClassRoom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {

    public List<ClassRoom> getAllClasses() {
        List<ClassRoom> list = new ArrayList<>();
        String sql = "SELECT * FROM classes";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                ClassRoom c = new ClassRoom(
                        rs.getString("class_id"),
                        rs.getString("course_id"),
                        rs.getString("teacher_id"),
                        rs.getInt("max_students")
                );
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addClass(ClassRoom c) {
        String sql = "INSERT INTO classes (class_id, course_id, teacher_id, max_students) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, c.getClassId());
            stmt.setString(2, c.getCourseId());
            stmt.setString(3, c.getTeacherId());
            stmt.setInt(4, c.getMaxStudents());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClass(String classId) {
        String sql = "DELETE FROM classes WHERE class_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, classId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateClass(ClassRoom c) {
        String sql = "UPDATE classes SET course_id = ?, teacher_id = ?, max_students = ? WHERE class_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, c.getCourseId());
            stmt.setString(2, c.getTeacherId());
            stmt.setInt(3, c.getMaxStudents());
            stmt.setString(4, c.getClassId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
