package ui;

import model.Registration;
import service.RegistrationService;

import java.util.List;
import java.util.Scanner;

public class RegistrationUI {
    private static Scanner scanner = new Scanner(System.in);
    private static RegistrationService registrationService = new RegistrationService();
    private static int currentStudentId;

    public static void showRegistrationMenu(int studentIdFromLogin) {
        currentStudentId = studentIdFromLogin; // Lưu studentId sau khi đăng nhập

        while (true) {
            System.out.println("\n--- Đăng ký môn học ---");
            System.out.println("1. Xem kết quả đăng ký môn học");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lựa chọn không hợp lệ.");
                continue;
            }

            switch (choice) {
                case 1:
                    xemKetQuaDangKy();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void xemKetQuaDangKy() {
        List<Registration> list = registrationService.getByStudentId(currentStudentId);
        if (list.isEmpty()) {
            System.out.println("📭 Bạn chưa đăng ký môn học nào.");
        } else {
            System.out.println("📘 Danh sách lớp bạn đã đăng ký:");
            for (Registration r : list) {
                System.out.println("- Mã lớp: " + r.getClassId());
            }
        }
    }
}