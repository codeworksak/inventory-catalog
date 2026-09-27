package com.codeworks.inventorycatalog.controllers;

import com.codeworks.inventorycatalog.memory_repositories.CategoryRepository;
import com.codeworks.inventorycatalog.models.Category;
import com.codeworks.inventorycatalog.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll()
    {
        List<Category> cats = categoryRepository.getAll();
        return ResponseEntity.ok(cats);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
        Category cat = categoryRepository.getById(id);
        if(cat !=null)
            return ResponseEntity.ok(cat);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
    }
    @GetMapping("/byname/{name}")
    public ResponseEntity<?> byName(@PathVariable String name)
    {
       List< Category> cats = categoryRepository.getByName(name);
        if(cats !=null)
            return ResponseEntity.ok(cats);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category name not found");
    }
    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category cat) {
       Category c= categoryRepository.add(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(c.getId())
                .toUri();

        return  ResponseEntity.created(uri).body(c);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id)
    {
       Boolean b= categoryRepository.delete(id);
      return b ? ResponseEntity.ok(b) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

    }
}
