package com.minhph091.estateportal.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {

    @NotBlank(message = "Name không được để trống")
    private String name;

    public CategoryRequest() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
