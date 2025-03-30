package ui;

import dao.AdminDAO;
import model.Admin;

import java.util.Scanner;

public class ManageAdminUI {
    private static Scanner scanner = new Scanner(System.in);
    private static AdminDAO adminDAO = new AdminDAO();

    public static void manageAdmin() {
        while (true) {
            System.out.println("1. Thêm Admin");
            System.out.println("2. Sửa Admin");
            System.out.println("3. Xóa Admin");
            System.out.println("4. Xem danh sách Admin");
            System.out.println("5. Thoát");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAdmin();
                    break;
                case 2:
                    updateAdmin();
                    break;
                case 3:
                    deleteAdmin();
                    break;
                case 4:
                    listAdmins();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addAdmin() {
        System.out.print("Nhập tên Admin: ");
        String name = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.print("Là Super Admin? (true/false): ");
        boolean isSuper = scanner.nextBoolean();
        scanner.nextLine();

        Admin admin = new Admin(name, isSuper, password );
        adminDAO.add(admin);
        System.out.println("Admin đã được thêm thành công!");
    }

    private static void updateAdmin() {
        System.out.print("Nhập tên Admin cần sửa: ");
        String name = scanner.nextLine().trim();
        Admin existingAdmin = adminDAO.selectByName(name);

        if (existingAdmin != null) {
            System.out.print("Nhập mật khẩu mới: ");
            String password = scanner.nextLine();
            System.out.print("Là Super Admin? (true/false): ");
            boolean isSuper = scanner.nextBoolean();
            scanner.nextLine();

            Admin updatedAdmin = new Admin(name, isSuper, password);
            adminDAO.update(updatedAdmin);
            System.out.println("Admin đã được cập nhật thành công!");
        } else {
            System.out.println("Admin không tồn tại!");
        }
    }

    private static void deleteAdmin() {
        System.out.print("Nhập tên Admin cần xóa: ");
        String name = scanner.nextLine();
        Admin existingAdmin = adminDAO.selectById(new Admin(name, false, ""));

        if (existingAdmin != null) {
            adminDAO.delete(existingAdmin);
            System.out.println("Admin đã được xóa thành công!");
        } else {
            System.out.println("Admin không tồn tại!");
        }
    }

    private static void listAdmins() {
        System.out.println("Danh sách Admins:");
        for (Admin admin : adminDAO.selectAll()) {
            System.out.println("Tên: " + admin.getName() + " | Super Admin: " + admin.isSuperAdmin() + " | password: " + admin.getPassword());
        }
    }
}

