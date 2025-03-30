package ui;

import dao.StudentDAO;

import java.util.Scanner;

public class AdminUI {
    private static Scanner scanner = new Scanner(System.in);
//    private static StudentDAO studentDAO = new StudentDAO();

    public static void indexAdmins() {
        while (true) {
            System.out.println("1. Quản lý thông tin sinh viên (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("2. Quản lý giảng viên (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("3. Quản lý lớp học (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("4. Quản lý lịch học (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("5. Quản lý khóa học (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("6. Gửi thông báo cho sinh viên (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("7. Đăng xuất");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    return;
                case 4:
                    return;
                case 5:
                    return;
                case 6:
                    return;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
