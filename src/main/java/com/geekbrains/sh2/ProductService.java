package com.geekbrains.sh2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public void create(String userInput) {
        String[] prodFields;
        int id;
        String name;
        int cost;
        prodFields = userInput.split(",");
        if (prodFields.length == 3 && prodFields[1].matches("[a-zA-Z]+" )) {
            name = prodFields[1];
            try {
                id = Integer.parseInt(prodFields[0]);
                cost = Integer.parseInt(prodFields[2]);
            } catch (NumberFormatException e) {
                System.out.println("id or cost is NaN");
                return;
            }
            if(id<=0||cost<=0) {
                System.out.println("ID and cost can't be negative");
                return;
            }
            for (Product product: productRepo.getProducts()) {
                if(product.getId()==id){
                    System.out.println("Product with such ID is already exist");
                    return;
                }
            }
            productRepo.addProd(new Product(id, name, cost));
            System.out.println("Product has been created");
        }
        else
            System.out.println("INVALID CREATION");
    }

    public void readId(String userInput) {
        int id;
        try{
            id = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Not a number");
            return;
        }
        for (Product product: productRepo.getProducts()) {
            if (product.getId() == id) {
                System.out.println(product.toString());
                return;
            }
        }
        System.out.println("No product with such ID");
    }

    public void readAll() {
        System.out.println("=============Product Lis=============");
        for (Product product: productRepo.getProducts()) {
            System.out.println(product);
        }
        System.out.println("=====================================");

    }

    public void update(String userInput) {
        String[] prodFields;
        int id;
        String name;
        int cost;
        prodFields = userInput.split(",");
        if (prodFields.length == 3 && prodFields[1].matches("[a-zA-Z]+" )) {
            name = prodFields[1];
            try {
                id = Integer.parseInt(prodFields[0]);
                cost = Integer.parseInt(prodFields[2]);
            } catch (NumberFormatException e) {
                System.out.println("id or cost is NaN");
                return;
            }
            for (Product product: productRepo.getProducts()) {
                if(product.getId()==id){
                    if(cost<=0) {
                        System.out.println("Cost can't be negative");
                        return;
                    }
                    product.setName(name);
                    product.setCost(cost);
                    System.out.println("Product has been updated");
                    return;
                }
            }
            System.out.println("No product with such id");
        }
        else
            System.out.println("INVALID UPDATE");
    }

    public void delete(String userInput) {
        int id;
        try{
            id = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("Not a number");
            return;
        }
        for (Product product: productRepo.getProducts()) {
            if (product.getId() == id) {
                System.out.println(product.toString() + " has been removed");
                productRepo.getProducts().remove(product);
                return;
            }
        }
        System.out.println("No product with such ID");
    }

    public void countProd() {
        System.out.println(productRepo.getProducts().size() + " products in repository");
    }

    public void averageCost() {
        double avg = 0;
        for (Product product: productRepo.getProducts()) {
            avg+=product.getCost();
        }
        System.out.println("Average cost is: " + avg/productRepo.getProducts().size());
    }
}

