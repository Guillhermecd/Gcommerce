package com.devsuperior.Gcommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.Gcommerce.entity.Order;
import com.devsuperior.Gcommerce.entity.OrderItem;
import com.devsuperior.Gcommerce.entity.OrderStatus;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {
    private long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Pelo menos um item deve ser associado ao pedido")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient());
        payment = entity.getPayment() == null ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem item : entity.getItems()) {
            items.add(new OrderItemDTO(item));
        }
    }

    public long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public double getTotal() {
        double sum = 0.0;
        for (OrderItemDTO item : items) {
            sum = sum + item.getSubTotal();
        }
        return sum;
    }

}
