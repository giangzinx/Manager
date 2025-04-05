package ui;

import dao.CourseDAO;
import dao.GradeDAO;
import dao.StudentDAO;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentUI {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentDAO studentDAO = new StudentDAO();
    private static CourseDAO courseDAO = new CourseDAO();
    private static GradeDAO gradeDAO = new GradeDAO();

    public static void indexStudents() {
        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Xem tất cả sinh viên");
            System.out.println("5. Tìm sinh viên theo ID");
            System.out.println("6. Tìm sinh viên theo email");
            System.out.println("7. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    showStudents();
                    break;
                case 5:
                    searchStudentById();
                    break;
                case 6:
                    searchStudentByEmail();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    // Thêm sinh viên
    private static void addStudent() {
        System.out.println("Nhập tên sinh viên:");
        String name = scanner.nextLine();
        System.out.println("Nhập mã sinh viên:");
        String studentCode = scanner.nextLine();
        System.out.println("Nhập email:");
        String email = scanner.nextLine();
        System.out.println("Nhập mật khẩu:");
        String password = scanner.nextLine();

        Student student = new Student(0, name, studentCode, email, password); // ID sẽ tự động tăng
        studentDAO.add(student);  // Gọi phương thức add từ StudentDAO
        System.out.println("Sinh viên đã được thêm thành công!");
    }

    // Sửa thông tin sinh viên
    private static void updateStudent() {
        System.out.println("Nhập ID sinh viên cần sửa:");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn sót lại

        Student student = studentDAO.selectById(new Student(studentId));
        if (student == null) {
            System.out.println("Sinh viên không tồn tại!");
            return;
        }

        System.out.println("Nhập tên mới (hiện tại: " + student.getName() + "):");
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);

        System.out.println("Nhập email mới (hiện tại: " + student.getEmail() + "):");
        String email = scanner.nextLine();
        if (!email.isEmpty()) student.setEmail(email);

        System.out.println("Nhập mã sinh viên mới (hiện tại: " + student.getStudentCode() + "):");
        String code = scanner.nextLine();
        if (!code.isEmpty()) student.setStudentCode(code);

        System.out.println("Nhập mật khẩu mới (hiện tại: " + student.getPassword() + "):");
        String password = scanner.nextLine();
        if (!password.isEmpty()) student.setPassword(password);

        studentDAO.update(student);
        System.out.println("Sinh viên đã được cập nhật!");
    }

    // Xóa sinh viên
    private static void deleteStudent() {
        System.out.println("Nhập ID sinh viên cần xóa:");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = studentDAO.selectById(new Student(studentId));  // Lấy sinh viên theo ID
        if (student == null) {
            System.out.println("Sinh viên không tồn tại!");
            return;
        }

        studentDAO.delete(student);  // Xóa sinh viên
        System.out.println("Sinh viên đã được xóa!");
    }

    // Hiển thị tất cả sinh viên
    private static void showStudents() {
        List<Student> students = studentDAO.selectAll();
        if (students.isEmpty()) {
            System.out.println("Không có sinh viên nào!");
            return;
        }

        System.out.println("Danh sách sinh viên:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Tìm sinh viên theo tên
    private static void searchStudentById() {
        System.out.print("Nhập ID sinh viên cần tìm: ");
        int studentId = scanner.nextInt();

        Student student = studentDAO.selectById(new Student(studentId, null, null, null, null));

        if (student != null) {
            System.out.println("Thông tin sinh viên:");
            System.out.println(student);
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + studentId);
        }
    }


    // Tìm sinh viên theo email
    private static void searchStudentByEmail() {
        System.out.println("Nhập email của sinh viên:");
        String email = scanner.nextLine();
        Student student = new Student();
        student.setEmail(email);

        ArrayList<Student> students = studentDAO.selectByCondition(student);
        if (students.isEmpty()) {
            System.out.println("Không tìm thấy sinh viên với email này!");
            return;
        }

        System.out.println("Danh sách sinh viên tìm thấy:");
        for (Student st : students) {
            System.out.println(st);
        }
    }

    public static void main(String[] args) {
        indexStudents();
    }
}
