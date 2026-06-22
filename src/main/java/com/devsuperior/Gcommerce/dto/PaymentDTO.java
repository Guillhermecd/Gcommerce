package com.devsuperior.Gcommerce.dto;

import java.time.Instant;

import com.devsuperior.Gcommerce.entity.Payment;

public class PaymentDTO {
    private long id;
    private Instant moment;

    public PaymentDTO() {
    }

    public PaymentDTO(long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
    }

    public long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

}
