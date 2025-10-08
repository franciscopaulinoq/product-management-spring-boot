package com.example.productmanagement.controller;

import com.example.productmanagement.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    List<Product> products = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Optional<Product> productFound = products.stream().filter(productFind -> productFind.getId().equals(product.getId())).findFirst();
        if (productFound.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(productFound.get());
        }
        products.add(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = products.stream().filter(productFind -> productFind.getId().equals(id)).findFirst();
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productFound = products.stream().filter(productFind -> productFind.getId().equals(id)).findFirst();
        if (productFound.isPresent()) {
            productFound.get().setName(product.getName());
            productFound.get().setPrice(product.getPrice());
            productFound.get().setQuantity(product.getQuantity());
            return ResponseEntity.ok(productFound.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = products.stream().filter(productFind -> productFind.getId().equals(id)).findFirst();
        if (product.isPresent()) {
            products.remove(product.get());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name) {
        List<Product> productsFinds = new ArrayList<>(products.stream().filter(product -> product.getName().contains(name)).toList());
        return ResponseEntity.ok(productsFinds);
    }
}
