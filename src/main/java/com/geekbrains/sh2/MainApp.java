package com.geekbrains.sh2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:");
            String userInput = sc.nextLine();
            if(userInput.equals("/create")) {
                System.out.print("Enter id,name,cost: ");
                productService.create(sc.nextLine());
            }
            else if(userInput.equals("/readid")){
                System.out.print("Enter id: ");
                productService.readId(sc.nextLine());
            }
            else if(userInput.equals("/readall")){
                productService.readAll();
            }
            else if(userInput.equals("/update")){
                System.out.print("Enter id,name,cost: ");
                productService.update(sc.nextLine());
            }
            else if(userInput.equals("/delete"))  {
                System.out.print("Enter id: ");
                productService.delete(sc.nextLine());
            }
            else if(userInput.equals("/count"))  {
                productService.countProd();
            }
            else if(userInput.equals("/avg"))  {
                productService.averageCost();
            }
            else if(userInput.equals("/close"))
                break;
            else
                System.out.println("INVALID COMMAND");
        }
        context.close();
    }
}
