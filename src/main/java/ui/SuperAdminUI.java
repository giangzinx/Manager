package ui;

import java.util.Scanner;

import static ui.ManageAdminUI.manageAdmin;

public class SuperAdminUI {
    private static Scanner scanner = new Scanner(System.in);
//    private static StudentDAO studentDAO = new StudentDAO();

    public static void indexSuperAdmins() throws Exception {
        while (true) {
            System.out.println("1. Quản lý thông tin Admin (Thêm, sửa, xóa, xem danh sách");
            System.out.println("2. Quản lý thông tin sinh viên (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("3. Quản lý giảng viên (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("4. Quản lý lớp học (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("5. Quản lý lịch học (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("6. Quản lý khóa học (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("7. Gửi thông báo cho sinh viên (Thêm, sửa, xóa, xem danh sách)");
            System.out.println("8. Đăng xuất");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageAdmin();
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
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
