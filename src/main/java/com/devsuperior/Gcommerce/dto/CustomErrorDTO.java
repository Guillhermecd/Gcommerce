package com.devsuperior.Gcommerce.dto;

import java.time.Instant;

public class CustomErrorDTO {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    public CustomErrorDTO(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public CustomErrorDTO(String string, int value, String message, String requestURI) {
        // TODO Auto-generated constructor stub
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
