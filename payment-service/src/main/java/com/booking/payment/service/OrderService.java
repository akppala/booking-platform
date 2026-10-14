package com.booking.payment.service;

import java.util.Map;

import com.booking.payment.dto.OrderDto;
import com.booking.payment.dto.OrderRequestDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Map> createOrder(OrderRequestDto request);
}
