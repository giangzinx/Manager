package ui;

import model.Notification;
import service.NotificationService;

import java.util.List;
import java.util.Scanner;

public class NotificationUI {
    private static Scanner sc = new Scanner(System.in);
    private static NotificationService service = new NotificationService();

    public static void run(int currentUserId, String currentRole) {
        while (true) {
            System.out.println("\n--- Quản lý Thông báo ---");
            System.out.println("1. Thêm thông báo");
            System.out.println("2. Sửa thông báo");
            System.out.println("3. Xóa thông báo");
            System.out.println("4. Xem danh sách thông báo");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập nội dung thông báo: ");
                    String msg = sc.nextLine();
                    service.sendNotification(currentUserId, currentRole, msg);
                    System.out.println("✅ Đã gửi thông báo.");
                    break;

                case 2:
                    System.out.print("Nhập ID thông báo cần sửa: ");
                    int updateId = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập nội dung mới: ");
                    String newMsg = sc.nextLine();
                    service.updateNotification(updateId, newMsg);
                    System.out.println("✅ Đã cập nhật thông báo.");
                    break;

                case 3:
                    System.out.print("Nhập ID thông báo cần xóa: ");
                    int deleteId = Integer.parseInt(sc.nextLine());
                    service.deleteNotification(deleteId);
                    System.out.println("✅ Đã xóa thông báo.");
                    break;

                case 4:
                    List<Notification> list = service.getAllNotifications();
                    for (Notification n : list) {
                        System.out.println("[" + n.getCreatedAt() + "] " + n.getSenderRole().toUpperCase() +
                                " (ID: " + n.getSenderId() + "): " + n.getMessage());
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
}
