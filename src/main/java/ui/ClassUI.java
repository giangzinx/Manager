package ui;

import dao.ClassDAO;
import model.Class;

import java.util.ArrayList;
import java.util.Scanner;

public class ClassUI {
    private static Scanner scanner = new Scanner(System.in);
    private static ClassDAO classDAO = new ClassDAO();

    public static void showMenu() {
        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Thêm lớp học");
            System.out.println("2. Cập nhật lớp học");
            System.out.println("3. Xóa lớp học");
            System.out.println("4. Xem tất cả lớp học");
            System.out.println("5. Tìm lớp học theo điều kiện");
            System.out.println("6. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự thừa sau khi nhập số nguyên

            switch (choice) {
                case 1:
                    addClass();
                    break;
                case 2:
                    updateClass();
                    break;
                case 3:
                    deleteClass();
                    break;
                case 4:
                    showAllClasses();
                    break;
                case 5:
                    searchClassByCondition();
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    // Thêm lớp học
    private static void addClass() {
        System.out.println("Nhập thông tin lớp học:");

        System.out.print("Mã khóa học (course_id): ");
        int courseId = scanner.nextInt();

        System.out.print("Mã giảng viên (teacher_id): ");
        int teacherId = scanner.nextInt();

        System.out.print("Số lượng sinh viên tối đa (max_students): ");
        int maxStudents = scanner.nextInt();

        Class newClass = new Class(0, courseId, teacherId, maxStudents); // classId = 0 vì nó tự động tăng
        classDAO.add(newClass);
    }

    // Cập nhật lớp học
    private static void updateClass() {
        System.out.print("Nhập mã lớp học cần cập nhật: ");
        int classId = scanner.nextInt();

        System.out.print("Mã khóa học (course_id): ");
        int courseId = scanner.nextInt();

        System.out.print("Mã giảng viên (teacher_id): ");
        int teacherId = scanner.nextInt();

        System.out.print("Số lượng sinh viên tối đa (max_students): ");
        int maxStudents = scanner.nextInt();

        Class updatedClass = new Class(classId, courseId, teacherId, maxStudents);
        classDAO.update(updatedClass);
    }

    // Xóa lớp học
    private static void deleteClass() {
        System.out.print("Nhập mã lớp học cần xóa: ");
        int classId = scanner.nextInt();

        Class classToDelete = new Class(classId, 0, 0, 0); // Chỉ cần classId để xóa
        classDAO.delete(classToDelete);
    }

    // Xem tất cả lớp học
    private static void showAllClasses() {
        ArrayList<Class> classes = classDAO.selectAll();
        if (classes.isEmpty()) {
            System.out.println("Không có lớp học nào trong hệ thống.");
        } else {
            System.out.println("Danh sách tất cả lớp học:");
            for (Class aClass : classes) {
                System.out.println(aClass);
            }
        }
    }

    // Tìm lớp học theo điều kiện (Ví dụ: theo khóa học và giảng viên)
    private static void searchClassByCondition() {
        System.out.print("Nhập mã khóa học (course_id): ");
        int courseId = scanner.nextInt();

        System.out.print("Nhập mã giảng viên (teacher_id): ");
        int teacherId = scanner.nextInt();

        Class searchClass = new Class(0, courseId, teacherId, 0); // Chỉ cần khóa học và giảng viên
        ArrayList<Class> classes = classDAO.selectByCondition(searchClass);

        if (classes.isEmpty()) {
            System.out.println("Không tìm thấy lớp học phù hợp!");
        } else {
            System.out.println("Danh sách lớp học tìm được:");
            for (Class aClass : classes) {
                System.out.println(aClass);
            }
        }
    }

    public static void main(String[] args) {
        showMenu();
    }
}
