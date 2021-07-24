/*package test;

import java.sql.SQLException;
import java.util.Scanner;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Log;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        Scanner input = new Scanner(System.in);

        System.out.println("Username");
        String username = input.nextLine();

        User user = userService.getU(username);

        if (user != null) {
            TestEdit.Test(user);
        } else {
            System.out.println("Null");
        }

        input.close();
    }
}
*/