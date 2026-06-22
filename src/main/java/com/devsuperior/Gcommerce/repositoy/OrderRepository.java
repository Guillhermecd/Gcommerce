package com.devsuperior.Gcommerce.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.Gcommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
