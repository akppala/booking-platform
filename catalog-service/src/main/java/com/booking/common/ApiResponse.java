package com.booking.common;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private ResponseStatus status;

    private String code;

    private String message;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private T data;
}
