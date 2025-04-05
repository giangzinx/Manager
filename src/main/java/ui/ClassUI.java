package ui;

import model.ClassRoom;
import service.ClassService;

import java.util.List;
import java.util.Scanner;

public class ClassUI {
    private static ClassService classService = new ClassService();
    private static Scanner sc = new Scanner(System.in);

    public static void quanLyLopHoc() {
        while (true) {
            System.out.println("\n--- Quản lý Lớp học ---");
            System.out.println("1. Thêm lớp học");
            System.out.println("2. Xóa lớp học");
            System.out.println("3. Sửa thông tin lớp học");  // ✅ Mục sửa đưa lên trước
            System.out.println("4. Xem danh sách lớp học");
            System.out.println("0. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");

            int luaChon;
            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lựa chọn không hợp lệ. Vui lòng nhập số.");
                continue;
            }

            switch (luaChon) {
                case 1:
                    themLop();
                    break;
                case 2:
                    xoaLop();
                    break;
                case 3:
                    suaLop();  // ✅ Xử lý mục sửa
                    break;
                case 4:
                    hienThiDanhSach();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void themLop() {
        System.out.print("Nhập mã lớp: ");
        String classId = sc.nextLine();
        System.out.print("Nhập mã môn học: ");
        String courseId = sc.nextLine();
        System.out.print("Nhập mã giảng viên: ");
        String teacherId = sc.nextLine();
        System.out.print("Nhập số sinh viên tối đa: ");
        int maxStudents = Integer.parseInt(sc.nextLine());

        classService.createClass(classId, courseId, teacherId, maxStudents);
        System.out.println("✅ Đã thêm lớp học thành công!");
    }

    private static void xoaLop() {
        System.out.print("Nhập mã lớp cần xóa: ");
        String maLop = sc.nextLine();
        classService.removeClass(maLop);
        System.out.println("✅ Đã xóa lớp học nếu tồn tại.");
    }

    private static void suaLop() {
        System.out.print("Nhập mã lớp cần sửa: ");
        String classId = sc.nextLine();
        System.out.print("Nhập mã môn học mới: ");
        String courseId = sc.nextLine();
        System.out.print("Nhập mã giảng viên mới: ");
        String teacherId = sc.nextLine();
        System.out.print("Nhập số sinh viên tối đa mới: ");
        int maxStudents = Integer.parseInt(sc.nextLine());

        classService.updateClass(classId, courseId, teacherId, maxStudents);
        System.out.println("✅ Đã cập nhật thông tin lớp học.");
    }

    private static void hienThiDanhSach() {
        List<ClassRoom> danhSach = classService.getAllClasses();
        System.out.println("\n📚 Danh sách lớp học:");
        if (danhSach.isEmpty()) {
            System.out.println("(Trống)");
        } else {
            for (ClassRoom lop : danhSach) {
                System.out.println("Mã lớp: " + lop.getClassId() +
                        " | Mã môn: " + lop.getCourseId() +
                        " | Giảng viên: " + lop.getTeacherId() +
                        " | SV tối đa: " + lop.getMaxStudents());
            }
        }
    }
}
