package com.booking.common;



public final class ResponseHandler {

    private ResponseHandler() {
    }

    public static <T> ApiResponse<T> success(
            T data,
            ResponseCode responseCode) {

        return ApiResponse.<T>builder()
                .status(ResponseStatus.SUCCESS)
                .code(responseCode.getCode())
                .message(responseCode.getMessage())
                .data(data)
                .build();
    }

}
