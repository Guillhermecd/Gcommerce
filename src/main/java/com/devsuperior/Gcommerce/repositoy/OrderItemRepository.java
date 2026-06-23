package com.devsuperior.Gcommerce.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.Gcommerce.entity.OrderItem;
import com.devsuperior.Gcommerce.entity.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
