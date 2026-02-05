package com.felipestanzani.migrationdemo.service;

import com.felipestanzani.migrationdemo.dto.ProductRequest;
import com.felipestanzani.migrationdemo.dto.ProductResponse;
import com.felipestanzani.migrationdemo.model.Product;
import com.felipestanzani.migrationdemo.repository.ProductRepository;
import com.felipestanzani.migrationdemo.service.contract.ProductService;
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

    @Override
    public List<String> findAllNames() {
        return repository.findAll()
                .stream()
                .map(Product::getName)
                .toList();
    }

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(p -> new ProductResponse(p.getName(), p.getPrice()))
                .toList();
    }
}
