package com.minhph091.estateportal.exception;
//Custome exception khi tìm thấy lỗi tài nguyên không tồn tại
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
