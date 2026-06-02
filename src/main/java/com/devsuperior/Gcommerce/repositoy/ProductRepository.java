package com.devsuperior.Gcommerce.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.Gcommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
