package ui;

import dao.AdminDAO;
import model.Admin;

import java.sql.SQLException;
import java.util.Scanner;

public class ManageAdminUI {
    private static Scanner scanner = new Scanner(System.in);
    private static AdminDAO adminDAO = new AdminDAO();

    public static void manageAdmin() throws Exception {
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
    public static void validateName(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Tên tài khoản không được để trống.");
        }

        if (name.contains(" ")) {
            throw new Exception("Tên tài khoản không được chứa khoảng trắng.");
        }

        if (name.length() < 3) {
            throw new Exception("Tên tài khoản phải có ít nhất 3 ký tự.");
        }


    }
    public static void validateIsSuper(String isSuper) throws Exception {
        if (!isSuper.equals("true") && !isSuper.equals("false")) {
            throw new Exception("Hãy nhập đúng true hoặc false.");
        }
    }

    public static void validatePassword(String password) throws Exception {
        if (password.length() < 6) {
            throw new Exception("Mật khẩu cần ít nhất 6 ký tự.");
        }
        if (password.contains(" ")) {
            throw new Exception("Mật khẩu không được chứa khoảng trắng.");
        }
    }
    private static void addAdmin(){
        while(true) {
            try {
                System.out.print("Nhập tên Admin: ");
                String name = scanner.nextLine().trim();
                validateName(name);
                Admin existingAdmin = adminDAO.selectByName(name);
                if (existingAdmin != null) {
                    throw new Exception("Tài khoản đã tồn tại.");
                }
                System.out.print("Nhập mật khẩu: ");
                String password = scanner.nextLine().trim();
                validatePassword(password);
                System.out.print("Là Super Admin? (true/false): ");
                String checkSuper = scanner.nextLine().trim();
                validateIsSuper(checkSuper);
                boolean isSuper = checkSuper.equals("true");
                Admin admin = new Admin(name, isSuper, password);
                adminDAO.add(admin);
                System.out.println("Admin đã được thêm thành công!");
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void updateAdmin() {
        while (true) {
            try{
                System.out.print("Nhập tên Admin cần sửa: ");
                String name = scanner.nextLine().trim();
                validateName(name);
                Admin existingAdmin = adminDAO.selectByName(name);
                if(existingAdmin == null) {
                    throw new Exception("Tài khoản không tồn tại");
                }
                System.out.print("Nhập mật khẩu mới: ");
                String password = scanner.nextLine();
                validatePassword(password);
                System.out.print("Là Super Admin? (true/false): ");
                String checkSuper = scanner.nextLine().trim();
                validateIsSuper(checkSuper);
                boolean isSuper = checkSuper.equals("true");
                Admin updatedAdmin = new Admin(name, isSuper, password);
                adminDAO.update(updatedAdmin);
                System.out.println("Tài khoản " + updatedAdmin.getName() +" đã được cập nhật thành công!");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    private static void deleteAdmin() {
        System.out.print("Nhập tên Admin cần xóa: ");
        String name = scanner.nextLine();
        Admin existingAdmin = adminDAO.selectByName(name);
        if (existingAdmin != null) {
            if(existingAdmin.getName().equals("admin")){
                System.out.println("Không thể xóa tài khoản admin");
            }
            else{
                adminDAO.delete(existingAdmin);
                System.out.println("Admin đã được xóa thành công!");
            }
        } else {
            System.out.println("Tài khoản không tồn tại!");
        }
    }

    private static void listAdmins() {
        System.out.println("Danh sách Admins:");
        if(adminDAO.selectAll().size() > 0){
            for (Admin admin : adminDAO.selectAll()) {
                System.out.println("Tên: " + admin.getName() + " | Super Admin: " + admin.isSuperAdmin() + " | password: " + admin.getPassword());
            }
        }
        else{
            System.out.println("Danh sách trống");
        }
    }
}

