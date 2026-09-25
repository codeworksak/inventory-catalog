package com.codeworks.inventorycatalog.memory_repositories;

import com.codeworks.inventorycatalog.models.Category;
import com.codeworks.inventorycatalog.models.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository implements  Repository<Product,UUID> {
    private List<Product> products;
    public ProductRepository() {
        products=new ArrayList<>();
        products.add(Product.builder()
                        .id(UUID.randomUUID())
                        .name("Moto Razar 60")
                        .brand("Motorola")
                        .price(70000.0)
                        .category_id(1)
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Oneplus 14")
                .brand("Oneplus")
                .price(50000.0)
                        .category_id(1)
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("TCL 4K Tv")
                .brand("TCL")
                .price(100000.0)
                        .category_id(2)
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Sony Bravia")
                .brand("Sony")
                .price(170000.0)
                        .category_id(2)
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Asus Vivobook")
                .brand("Asus")
                .price(65000.0)
                        .category_id(3)
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("HP Elite Book")
                .brand("Motorola")
                .price(170000.0)
                        .category_id(3)
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("PRO Microservice programming")
                .brand("TPublisher")
                .price(27000.0)
                        .category_id(4)
                .build());
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("World Economics Demistified")
                .brand("WPublisher")
                .price(3200.0)
                        .category_id(4)
                .build());
    }

    @Override
    public Product add(Product item) {
        Optional<Product> p = products.stream().filter(pr-> pr.getId().equals(item.getId())).findAny();
        if(p.isEmpty())
        {
            products.add(item);
            return  item;
        }else
            throw  new RuntimeException("Product already present");
    }

    @Override
    public Product getById(UUID id) {
        Optional<Product> p = products.stream().filter(pr-> pr.getId().equals(id)).findAny();
        return p.orElseThrow();
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> prs = products.stream().filter(pr-> pr.getName()
                .toLowerCase().startsWith(name.toLowerCase())).toList();
        return prs;
    }

    @Override
    public Boolean delete(UUID id) {
        Optional<Product> cat= products.stream().filter(c-> c.getId().equals(id)).findAny();
        if(cat.isPresent())
        {
            products.remove(cat.get());
            return true;
        }else
            throw  new RuntimeException("Could not find product to delete");
    }
}
