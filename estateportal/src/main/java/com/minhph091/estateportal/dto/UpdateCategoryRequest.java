package com.minhph091.estateportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCategoryRequest {
    @NotNull(message = "ID không được trống khi update")
    private int id;
    @NotBlank(message = "Tên không được null")
    private String name;

    public UpdateCategoryRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
