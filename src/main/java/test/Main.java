/*package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

public class Main {

    static CategoryService categoryService = new CategoryServiceImpl();
    static ProductService productService = new ProductServiceImpl();
    public static void main(String[] args) throws SQLException {

        Scanner input = new Scanner(System.in);

        System.out.println("product");
        String nameCategory = input.nextLine();

        List<Product> listProducts = productService.searchByName(nameCategory);
        for (Product product : listProducts) {
            System.out.println(product.getName());
        }
        input.close();
    }
}*/