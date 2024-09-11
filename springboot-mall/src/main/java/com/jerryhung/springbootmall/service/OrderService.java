package com.jerryhung.springbootmall.service;

import com.jerryhung.springbootmall.dto.CreateOrderRequest;
import com.jerryhung.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

}
