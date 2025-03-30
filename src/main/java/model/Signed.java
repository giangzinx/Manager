package model;

import java.util.Scanner;

public class Signed {
    private static Scanner scanner = new Scanner(System.in);

    public static String[] getUserCredentials() {
        System.out.print("Tài khoản: ");
        String userInput = scanner.nextLine().toLowerCase().trim();
        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine().trim();
        return new String[]{userInput, password};
    }
}
