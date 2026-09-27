package com.codeworks.inventorycatalog.memory_repositories;

import com.codeworks.inventorycatalog.models.Category;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Scope("singleton")
public class CategoryRepository implements Repository<Category,Integer> {
    private List<Category> categories;

    public CategoryRepository() {
        this.categories = new ArrayList<>();
        categories.add(Category.builder()
                        .id(1)
                        .name("Mobiles")
                .build());
        categories.add(Category.builder()
                .id(2)
                .name("Tvs")
                .build());
        categories.add(Category.builder()
                .id(3)
                .name("Laptops")
                .build());
        categories.add(Category.builder()
                .id(4)
                .name("Books")
                .build());
    }


    @Override
    public Category add(Category item) {
        Optional<Category> cat= categories.stream().filter(c-> c.getId().equals(item.getId())).findAny();
       if( cat.isEmpty()) {
           categories.add(item);
           return item;
       }
       else
        throw  new RuntimeException("Cannot add category as it is present");
    }

    @Override
    public Category getById(UUID id) {
        Optional<Category> cat= categories.stream().filter(c-> c.getId().equals(id)).findAny();
        return  cat.orElseThrow();
    }

    @Override
    public List<Category> getByName(String name) {
        return  categories;
    }

    @Override
    public Boolean delete(Integer id) {
        Optional<Category> cat= categories.stream().filter(c-> c.getId().equals(id)).findAny();
        if(cat.isPresent())
        {
            categories.remove(cat.get());
            return true;
        }else
            throw  new RuntimeException("Could not find category");
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }
}
