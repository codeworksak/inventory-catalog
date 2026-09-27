package com.codeworks.inventorycatalog.controllers;

import com.codeworks.inventorycatalog.dtos.ProductReponse;
import com.codeworks.inventorycatalog.memory_repositories.CategoryRepository;
import com.codeworks.inventorycatalog.memory_repositories.ProductRepository;
import com.codeworks.inventorycatalog.models.Category;
import com.codeworks.inventorycatalog.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ProductReponse>> getProducts()
    {
        List<ProductReponse> prResp= getProdResponse();
        return ResponseEntity.ok(prResp);
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<ProductReponse> getById(@PathVariable UUID id)
    {
        List<ProductReponse> prResp= getProdResponse();
       Optional< ProductReponse> pr = prResp.stream().filter(p-> p.Id().equals(id)).findAny();
       if(pr.isEmpty())
           return  ResponseEntity.notFound().build();
       else
           return  ResponseEntity.ok(pr.get());
    }
    @GetMapping("/byname/{name}")
    public ResponseEntity< List<ProductReponse>> getById(@PathVariable String name)
    {
        List<ProductReponse> prResp= getProdResponse();
         List<ProductReponse> pr = prResp.stream().filter(p-> p.Name().toLowerCase().startsWith(name.toLowerCase())).toList();
        if(pr.isEmpty())
            return  ResponseEntity.notFound().build();
        else
            return  ResponseEntity.ok(pr);
    }

    @PostMapping("/add")
    public  ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        productRepository.add(product);
        return  new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public  ResponseEntity<Boolean> delete(@PathVariable UUID id)
    {
       if( productRepository.delete(id))
           return ResponseEntity.ok().body(true);
       else
           return  ResponseEntity.internalServerError().body(false);
    }
    private List<ProductReponse> getProdResponse()
    {
        List<Category> categories= categoryRepository.getAll();
        List<Product> products=productRepository.getAll();

        List<ProductReponse> productReponses=categories.stream()
                .flatMap(cat-> products.stream().filter(prod-> cat.getId() == prod.getCategory_id()).map(p->new
                        ProductReponse(p.getId(),p.getName(),p.getBrand(),cat.getName(),p.getPrice()))).collect(Collectors.toList());
        return  productReponses;
    }
}
