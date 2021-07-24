/*package test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class TestEdit {

    public static void Test(User user) throws SQLException{
        UserService userService = new UserServiceImpl();
        Scanner input = new Scanner(System.in);

        System.out.println("Ten:");
        String name = input.nextLine();

        System.out.println("Gioi tinh:");
        String gender = input.nextLine();

        System.out.println("sinh nhat:");
        Date  birthDate = Date.valueOf(input.nextLine());

        System.out.println("address");
        String address = input.nextLine();

        System.out.println("phone:");
        String phone = input.nextLine();

        user.setFullName(name);
        user.setGender(gender);
        user.setBirthDate(birthDate);
        user.setAddress(address);
        user.setPhone(phone);

        userService.edit(user);

        input.close();
    }
}*/