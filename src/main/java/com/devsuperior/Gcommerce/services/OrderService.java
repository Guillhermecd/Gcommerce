package com.devsuperior.Gcommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.Gcommerce.dto.OrderDTO;
import com.devsuperior.Gcommerce.dto.OrderItemDTO;
import com.devsuperior.Gcommerce.entity.Order;
import com.devsuperior.Gcommerce.entity.OrderItem;
import com.devsuperior.Gcommerce.entity.OrderStatus;
import com.devsuperior.Gcommerce.entity.Product;
import com.devsuperior.Gcommerce.entity.User;
import com.devsuperior.Gcommerce.repositoy.OrderItemRepository;
import com.devsuperior.Gcommerce.repositoy.OrderRepository;
import com.devsuperior.Gcommerce.repositoy.ProductRepository;
import com.devsuperior.Gcommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> findMyOrders(Pageable pageable) {
        User client = userService.authenticated();
        Page<Order> result = repository.findByClient(client, pageable);
        return result.map(OrderDTO::new);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        order = repository.save(order);
        for (OrderItem item : order.getItems()) {
            orderItemRepository.save(item);
        }
        return new OrderDTO(order);
    }
}
