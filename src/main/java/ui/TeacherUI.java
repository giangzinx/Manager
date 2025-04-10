package ui;

import dao.TeacherDAO;
import model.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class TeacherUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TeacherDAO teacherDAO = new TeacherDAO();

    public static void showMenu() {
        while (true) {
            System.out.println("\nChọn chức năng:");
            System.out.println("1. Thêm giáo viên");
            System.out.println("2. Sửa thông tin giáo viên");
            System.out.println("3. Xóa giáo viên");
            System.out.println("4. Xem tất cả giáo viên");
            System.out.println("5. Tìm giáo viên theo tên");
            System.out.println("6. Tìm giáo viên theo email");
            System.out.println("7. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Đọc ký tự enter sau khi nhập số

            switch (choice) {
                case 1:
                    addTeacher();
                    break;
                case 2:
                    updateTeacher();
                    break;
                case 3:
                    deleteTeacher();
                    break;
                case 4:
                    showAllTeachers();
                    break;
                case 5:
                    searchTeacherByName();
                    break;
                case 6:
                    searchTeacherByEmail();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    private static void addTeacher() {
        System.out.println("Nhập ID giáo viên:");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự enter sau khi nhập số

        System.out.println("Nhập tên giáo viên:");
        String name = scanner.nextLine();

        System.out.println("Nhập email giáo viên:");
        String email = scanner.nextLine();

        System.out.println("Nhập mật khẩu giáo viên:");
        String password = scanner.nextLine();

        Teacher teacher = new Teacher(teacherId, name, email, password);
        teacherDAO.add(teacher);
    }


    private static void updateTeacher() {
        System.out.println("Nhập ID giáo viên cần sửa:");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự enter sau khi nhập số

        System.out.println("Nhập tên mới:");
        String name = scanner.nextLine();
        System.out.println("Nhập email mới:");
        String email = scanner.nextLine();
        System.out.println("Nhập mật khẩu mới:");
        String password = scanner.nextLine();

        Teacher teacher = new Teacher(teacherId, name, email, password);
        teacherDAO.update(teacher);
    }

    private static void deleteTeacher() {
        System.out.println("Nhập ID giáo viên cần xóa:");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự enter sau khi nhập số

        Teacher teacher = new Teacher(teacherId);
        teacherDAO.delete(teacher);
    }

    private static void showAllTeachers() {
        ArrayList<Teacher> teachers = teacherDAO.selectAll();
        if (teachers.isEmpty()) {
            System.out.println("Không có giáo viên nào trong cơ sở dữ liệu.");
        } else {
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }
    }

    private static void searchTeacherByName() {
        System.out.println("Nhập tên giáo viên để tìm kiếm:");
        String name = scanner.nextLine();
        Teacher teacher = teacherDAO.selectByName(name);
        if (teacher == null) {
            System.out.println("Không tìm thấy giáo viên với tên " + name);
        } else {
            System.out.println(teacher);
        }
    }

    private static void searchTeacherByEmail() {
        System.out.println("Nhập email giáo viên để tìm kiếm:");
        String email = scanner.nextLine();
        Teacher teacher = new Teacher();
        teacher.setEmail(email);
        ArrayList<Teacher> teachers = teacherDAO.selectByCondition(teacher);
        if (teachers.isEmpty()) {
            System.out.println("Không tìm thấy giáo viên với email " + email);
        } else {
            for (Teacher t : teachers) {
                System.out.println(t);
            }
        }
    }

    public static void main(String[] args) {
        showMenu();
    }
}
