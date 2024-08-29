package org.twspring.capstone3.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.capstone3.Api.ApiException;
import org.twspring.capstone3.Model.Product;
import org.twspring.capstone3.Repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Integer id, Product updateProduct) {
        Product product = productRepository.findProductById(id)
                .orElseThrow(() -> new ApiException("PRODUCT NOT FOUND"));
        productRepository.save(product);
    }

    public void deleteProduct(Integer id){
        Product product = productRepository.findProductById(id)
                .orElseThrow(() -> new ApiException("PRODUCT NOT FOUND"));
        productRepository.delete(product);
    }
}
