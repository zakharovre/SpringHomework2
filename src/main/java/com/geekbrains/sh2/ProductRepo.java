package com.geekbrains.sh2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepo {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
    public void addProd(Product product){
        products.add(product);
    }
   @PostConstruct
   public void init(){
        products = new ArrayList<>();
        products.add(new Product(1, "Apple", 50));
        products.add(new Product(2, "Orange", 100));
        products.add(new Product(3, "Peach", 75));
        products.add(new Product(4, "Banana", 25));
        products.add(new Product(5, "Grape", 650));
    }
}
