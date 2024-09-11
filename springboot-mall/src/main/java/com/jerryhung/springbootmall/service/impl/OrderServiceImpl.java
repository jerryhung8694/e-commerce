package com.jerryhung.springbootmall.service.impl;

import com.jerryhung.springbootmall.dao.OrderDao;
import com.jerryhung.springbootmall.dao.ProductDao;
import com.jerryhung.springbootmall.dao.UserDao;
import com.jerryhung.springbootmall.dto.BuyItem;
import com.jerryhung.springbootmall.dto.CreateOrderRequest;
import com.jerryhung.springbootmall.dto.OrderQueryParams;
import com.jerryhung.springbootmall.model.Order;
import com.jerryhung.springbootmall.model.OrderItem;
import com.jerryhung.springbootmall.model.Product;
import com.jerryhung.springbootmall.model.User;
import com.jerryhung.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());

            order.setOrderItemsList(orderItemList);
        }

        return orderList;
    }

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

        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("This userId {} is not exist", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem item : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(item.getProductId());

            if (product == null) {
                log.warn("This productId {} is not exist", item.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < item.getQuantity()) {
                log.warn("This productId {} is not enough, only {} left, and you want to buy {}",
                        item.getProductId(), product.getStock(), item.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(product.getProductId(), product.getStock() - item.getQuantity());

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
