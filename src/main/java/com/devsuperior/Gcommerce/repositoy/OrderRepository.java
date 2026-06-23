package com.devsuperior.Gcommerce.repositoy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.Gcommerce.entity.Order;
import com.devsuperior.Gcommerce.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByClient(User client, Pageable pageable);

}
