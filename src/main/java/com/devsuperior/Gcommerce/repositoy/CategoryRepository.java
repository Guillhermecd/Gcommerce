package com.devsuperior.Gcommerce.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.Gcommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
