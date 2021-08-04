/*package test;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import model.Cart;
import model.CartItem;
import model.User;
import service.CartItemService;
import service.CartService;
import service.CategoryService;
import service.ProductService;
import service.UserService;
import service.impl.CartItemServiceImpl;
import service.impl.CartServiceImpl;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        CartService cartService = new CartServiceImpl();
        Scanner input = new Scanner(System.in);

        System.out.println("username");
        String username = input.nextLine();

        User user = userService.get(username);

        Cart cart = cartService.get(user);

        Set<CartItem> cartItems = cart.getCartItems();

        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem.getId());
            System.out.println(cartItem.getCart());
            System.out.println(cartItem.getProduct());
            System.out.println(cartItem.getQuantity());
            System.out.println(cartItem.getTotal());
        }

        input.close();
    }
}*/