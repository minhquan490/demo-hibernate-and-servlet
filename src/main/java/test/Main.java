package test;

import java.sql.SQLException;
import java.util.Scanner;

import service.UserService;
import service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Scanner input = new Scanner(System.in);

        System.out.println("Input email:");
        String email = input.nextLine();

        System.out.println("username:");
        String username = input.nextLine();

        System.out.println("password");
        String password = input.nextLine();

        int roleId = 2;
        /*System.out.println("Nhap id");
        int id = Integer.parseInt(input.nextLine());*/

        try {
            Boolean success = userService.register(username, email, password, roleId);
            if (success) {
                System.out.println("Register success");
            } else {
                System.out.println("Failure");
            }

            /*Boolean success = userService.delete(id);
            if (success) {
                System.out.println("Delete success");
            } else {
                System.out.println("Failure");
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        input.close();
    }
}
