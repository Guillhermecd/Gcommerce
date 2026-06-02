package com.devsuperior.Gcommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Gcommerce.dto.ProductDTO;
import com.devsuperior.Gcommerce.entity.Product;
import com.devsuperior.Gcommerce.repositoy.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)

    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;

    }

}
