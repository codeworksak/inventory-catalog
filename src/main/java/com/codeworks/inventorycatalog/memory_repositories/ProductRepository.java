package com.codeworks.inventorycatalog.memory_repositories;

import com.codeworks.inventorycatalog.models.Category;
import com.codeworks.inventorycatalog.models.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class ProductRepository implements  Repository<Product,UUID> {
    private List<Product> products;
    public ProductRepository() {
        products=new ArrayList<>();
        products.add(Product.builder()
                        //.id(UUID.randomUUID())
                        .id(UUID.fromString("b7e2c0d5-23c7-4a05-8441-5e0ae65b9019"))
                        .name("Moto Razar 60")
                        .brand("Motorola")
                        .price(70000.0)
                        .category_id(1)
                .build());
        products.add(Product.builder()
                //.id(UUID.randomUUID())
                .id(UUID.fromString("86712b93-a787-4b1e-87df-630748a85ad2"))
                .name("Oneplus 14")
                .brand("Oneplus")
                .price(50000.0)
                        .category_id(1)
                .build());
        products.add(Product.builder()
                //.id(UUID.randomUUID())
                .id(UUID.fromString("ed7ae80d-f5ba-43ce-b860-1ee228c4d7ab"))
                .name("TCL 4K Tv")
                .brand("TCL")
                .price(100000.0)
                        .category_id(2)
                .build());
        products.add(Product.builder()
                //.id(UUID.randomUUID())
                .id(UUID.fromString("7ddd0cb9-a456-4b82-a71f-d21a4ec698fa"))
                .name("Sony Bravia")
                .brand("Sony")
                .price(170000.0)
                        .category_id(2)
                .build());
        products.add(Product.builder()
//                .id(UUID.randomUUID())
                .id(UUID.fromString("12d5c8e0-d92e-4855-9434-c27e8cebc960"))
                .name("Asus Vivobook")
                .brand("Asus")
                .price(65000.0)
                        .category_id(3)
                .build());
        products.add(Product.builder()
//                .id(UUID.randomUUID())
                .id(UUID.fromString("4aacffc1-86fa-4ecd-9359-e65b2ea44ff3"))
                .name("HP Elite Book")
                .brand("Motorola")
                .price(170000.0)
                        .category_id(3)
                .build());
        products.add(Product.builder()
//                .id(UUID.randomUUID())
                .id(UUID.fromString("d5e1df4f-9a6e-4d0f-9def-53f0e452f205"))
                .name("PRO Microservice programming")
                .brand("TPublisher")
                .price(27000.0)
                        .category_id(4)
                .build());
        products.add(Product.builder()
//                .id(UUID.randomUUID())
                .id(UUID.fromString("cf39a3a9-e332-4d21-8feb-e122633bb707"))
                .name("World Economics Demistified")
                .brand("WPublisher")
                .price(3200.0)
                        .category_id(4)
                .build());
    }

    @Override
    public Product add(Product item) {
        //Optional<Product> p = products.stream().filter(pr-> pr.getId().equals(item.getId())).findAny();

            item.setId(UUID.randomUUID());
            products.add(item);
            return  item;
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

    @Override
    public List<Product> getAll() {
        return products;
    }
}
