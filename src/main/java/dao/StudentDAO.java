package dao;

import model.Student;
import ui.StudentUI;
import java.sql.*;
import java.util.ArrayList;


public class StudentDAO implements InterfaceDAO<Student> {
    public void getCheckStudent(String studentEmail, String studentPassword) {
        String sql = "SELECT * FROM students WHERE email= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentEmail);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Phải có rs.next() trước khi lấy dữ liệu từ ResultSet
                if (rs.getString("email").equals(studentEmail) && rs.getString("password").equals(studentPassword)) {
                    int studentId = rs.getInt("student_id"); // hoặc rs.getInt("id") tùy theo tên cột
                    StudentUI.indexStudents(studentId); // ✅ Truyền đúng tham số
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
    public void add(Student student) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public Student selectById(String id) {
        return null;
    }

    @Override
    public Student selectByName(String name) {
        return null;
    }

    @Override
    public ArrayList<Student> selectAll() {
        return null;
    }

    @Override
    public ArrayList<Student> selectByCondition(Student student) {
        return null;
    }
}
