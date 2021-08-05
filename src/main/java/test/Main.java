/*package test;

import java.sql.SQLException;
import java.util.Scanner;

import service.UserService;
import service.impl.UserServiceImpl;
import model.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        User user = new User();
        UserService userService = new UserServiceImpl();

        Scanner input = new Scanner(System.in);

        System.out.println("username");
        String username = input.nextLine();
        user.setUsername(username);

        System.out.println("password");
        String password = input.nextLine();
        user.setPassword(password);

        System.out.println("email");
        String email = input.nextLine();
        user.setEmail(email);

        user.setRoleId(2);

        boolean success = userService.register(user);
        if (success) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
        input.close();
    }
}*/