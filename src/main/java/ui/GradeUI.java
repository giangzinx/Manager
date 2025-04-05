package ui;

import dao.GradeDAO;
import model.Grade;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeUI {
    private static final Scanner scanner = new Scanner(System.in);
    private final GradeDAO gradeDAO = new GradeDAO();

    public static void showMenu() {
        while (true) {
            System.out.println("Chọn chức năng:");
            System.out.println("1. Thêm điểm");
            System.out.println("2. Cập nhật điểm");
            System.out.println("3. Xóa điểm");
            System.out.println("4. Xem tất cả điểm");
            System.out.println("5. Tìm điểm theo tên sinh viên");
            System.out.println("6. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            GradeUI gradeUI = new GradeUI();

            switch (choice) {
                case 1:
                    gradeUI.addGrade();
                    break;
                case 2:
                    gradeUI.updateGrade();
                    break;
                case 3:
                    gradeUI.deleteGrade();
                    break;
                case 4:
                    gradeUI.showGrades();
                    break;
                case 5:
                    gradeUI.searchGradeByName();
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
            }
        }
    }

    // Thêm điểm cho sinh viên
    private void addGrade() {
        System.out.println("Nhập mã sinh viên:");
        int studentId = scanner.nextInt();
        System.out.println("Nhập mã lớp:");
        int classId = scanner.nextInt();
        System.out.println("Nhập điểm (0-10):");
        float gradeValue = scanner.nextFloat();

        Grade grade = new Grade(0, studentId, classId, gradeValue);
        gradeDAO.add(grade);
    }

    // Cập nhật điểm
    private void updateGrade() {
        System.out.println("Nhập mã điểm cần cập nhật:");
        int gradeId = scanner.nextInt();
        System.out.println("Nhập điểm mới (0-10):");
        float newGrade = scanner.nextFloat();

        Grade grade = new Grade(gradeId, 0, 0, newGrade);
        gradeDAO.update(grade);
    }

    // Xóa điểm
    private void deleteGrade() {
        System.out.println("Nhập mã điểm cần xóa:");
        int gradeId = scanner.nextInt();

        Grade grade = new Grade(gradeId, 0, 0, 0);
        gradeDAO.delete(grade);
    }

    // Xem tất cả điểm
    private void showGrades() {
        ArrayList<Grade> grades = gradeDAO.selectAll();
        if (grades.isEmpty()) {
            System.out.println("Không có điểm nào trong hệ thống.");
        } else {
            for (Grade grade : grades) {
                System.out.println(grade);
            }
        }
    }

    // Tìm điểm theo tên sinh viên
    private void searchGradeByName() {
        System.out.println("Nhập tên sinh viên:");
        String studentName = scanner.nextLine();

        Grade grade = gradeDAO.selectByName(studentName);
        if (grade != null) {
            System.out.println("Điểm của sinh viên " + studentName + ": " + grade.getGrade());
        } else {
            System.out.println("Không tìm thấy điểm cho sinh viên " + studentName);
        }
    }

    public static void main(String[] args) {
        showMenu();
    }
}
