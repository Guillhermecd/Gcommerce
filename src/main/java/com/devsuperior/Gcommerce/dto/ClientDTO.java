package com.devsuperior.Gcommerce.dto;

import com.devsuperior.Gcommerce.entity.User;

public class ClientDTO {
    private long Id;
    private String name;

    public ClientDTO() {
    }

    public ClientDTO(long id, String name) {
        this.Id = id;
        this.name = name;
    }

    public ClientDTO(User entity) {
        this.Id = entity.getId();
        this.name = entity.getName();
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

}
