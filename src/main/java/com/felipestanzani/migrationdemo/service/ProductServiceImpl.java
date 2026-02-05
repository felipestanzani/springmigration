package com.felipestanzani.migrationdemo.service;

import com.felipestanzani.migrationdemo.dto.ProductRequest;
import com.felipestanzani.migrationdemo.dto.ProductResponse;
import com.felipestanzani.migrationdemo.model.Product;
import com.felipestanzani.migrationdemo.repository.ProductRepository;
import com.felipestanzani.migrationdemo.service.contract.ProductService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Transactional
    public Product save(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        return repository.save(product);
    }

    @Retry(name = "findAllNames"
            , fallbackMethod = "fallbackFindAllNames")
    @Override
    public List<String> findAllNames() {
        if (Math.random() > 0.5) throw new RuntimeException("It is not frozen, it is in panic!!!");

        return repository.findAll()
                .stream()
                .map(Product::getName)
                .toList();
    }

    public List<String> fallbackFindAllNames() {
        return List.of("Charuteira", "Infundibuliar");
    }

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(p -> new ProductResponse(p.getName(), p.getPrice()))
                .toList();
    }
}
