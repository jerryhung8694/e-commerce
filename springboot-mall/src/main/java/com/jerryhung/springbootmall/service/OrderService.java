package com.jerryhung.springbootmall.service;

import com.jerryhung.springbootmall.dto.CreateOrderRequest;
import com.jerryhung.springbootmall.dto.OrderQueryParams;
import com.jerryhung.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

}
