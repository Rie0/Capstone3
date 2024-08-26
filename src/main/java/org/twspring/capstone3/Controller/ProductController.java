package org.twspring.capstone3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.twspring.capstone3.Api.ApiResponse;
import org.twspring.capstone3.Model.Product;
import org.twspring.capstone3.Service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity addProduct(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok(new ApiResponse("Product added successfully"));
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestParam Integer id, @Valid @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok(new ApiResponse("Product updated successfully"));
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestParam Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse("Product deleted successfully"));
    }
}
