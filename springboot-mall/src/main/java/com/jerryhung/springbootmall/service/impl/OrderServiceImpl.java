package com.jerryhung.springbootmall.service.impl;

import com.jerryhung.springbootmall.dao.OrderDao;
import com.jerryhung.springbootmall.dao.ProductDao;
import com.jerryhung.springbootmall.dto.BuyItem;
import com.jerryhung.springbootmall.dto.CreateOrderRequest;
import com.jerryhung.springbootmall.model.Order;
import com.jerryhung.springbootmall.model.OrderItem;
import com.jerryhung.springbootmall.model.Product;
import com.jerryhung.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);

        order.setOrderItemsList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem item : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(item.getProductId());

            // Count total amount of price
            int amount = product.getPrice() * item.getQuantity();
            totalAmount += amount;

            // BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }
}
