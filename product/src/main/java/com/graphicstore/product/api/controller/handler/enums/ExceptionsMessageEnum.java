package com.graphicstore.product.api.controller.handler.enums;

public enum ExceptionsMessageEnum {

    INTERNAL_ERROR("Something going wrong!");

    private String message;

    ExceptionsMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
