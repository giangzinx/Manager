package ui;

import dao.CourseDAO;
import model.Course;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseUI {
    private static Scanner scanner = new Scanner(System.in);
    private static CourseDAO courseDAO = new CourseDAO();

    // Hiển thị menu và xử lý các lựa chọn
    public static void showMenu() {
        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Thêm môn học");
            System.out.println("2. Sửa môn học");
            System.out.println("3. Xóa môn học");
            System.out.println("4. Xem tất cả môn học");
            System.out.println("5. Tìm môn học theo ID");
            System.out.println("6. Tìm môn học theo tên hoặc code");
            System.out.println("7. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Đọc bỏ dòng enter còn sót lại

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    updateCourse();
                    break;
                case 3:
                    deleteCourse();
                    break;
                case 4:
                    showCourses();
                    break;
                case 5:
                    searchCourseById();
                    break;
                case 6:
                    selectByCondition();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    // Thêm môn học mới
    private static void addCourse() {
        System.out.print("Nhập mã môn học: ");
        String courseCode = scanner.nextLine();
        System.out.print("Nhập tên môn học: ");
        String courseName = scanner.nextLine();
        System.out.print("Nhập số tín chỉ: ");
        int credits = scanner.nextInt();

        Course course = new Course(courseCode, courseName, credits);
        courseDAO.add(course);
    }

    // Sửa thông tin môn học
    private static void updateCourse() {
        System.out.print("Nhập id môn học cần sửa: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng thừa

        Course existingCourse = courseDAO.findById(courseId);
        if (existingCourse == null) {
            System.out.println("Chưa có môn học với id " + courseId);
            return;
        }

        System.out.print("Nhập mã môn học mới: ");
        String courseCode = scanner.nextLine();
        System.out.print("Nhập tên môn học mới: ");
        String courseName = scanner.nextLine();
        System.out.print("Nhập số tín chỉ mới: ");
        int credits = scanner.nextInt();

        Course course = new Course(courseId , courseCode, courseName, credits);
        courseDAO.update(course);
        System.out.println("Cập nhật môn học thành công.");
    }


    // Xóa môn học
    private static void deleteCourse() {
        System.out.print("Nhập mã môn học cần xóa: ");
        String courseCode = scanner.nextLine();

        Course course = new Course(courseCode);
        courseDAO.delete(course);
    }

    // Hiển thị tất cả các môn học
    private static void showCourses() {
        ArrayList<Course> courses = courseDAO.selectAll();
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // Tìm môn học theo mã
    private static void searchCourseById() {
        System.out.print("Nhập code môn học: ");
        int courseId = scanner.nextInt();

        Course course = new Course();
        course.setCourseId(courseId);
        Course result = courseDAO.selectById(course);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("Không tìm thấy môn học với id: " + courseId);
        }
    }

    // Tìm môn học theo tên
    private static void selectByCondition() {
        System.out.print("Nhập tên môn học hoặc code môn học: ");
        String courseNameOrCode = scanner.nextLine();
        Course course = new Course();
        course.setCourseCode(courseNameOrCode);
        course.setCourseName(courseNameOrCode);

        ArrayList<Course> courses = courseDAO.selectByCondition(course);
        if (!courses.isEmpty()) {
            for (Course course1 : courses) {
                System.out.println(course1);
            }
        } else {
            System.out.println("Không tìm thấy môn học với tên: " + courseNameOrCode);
        }
    }

    public static void main(String[] args) {
        showMenu();
    }
}
