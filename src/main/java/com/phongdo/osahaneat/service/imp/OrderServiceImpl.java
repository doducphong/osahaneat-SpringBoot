package com.phongdo.osahaneat.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.phongdo.osahaneat.domain.entity.*;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phongdo.osahaneat.dto.request.OrderRequest;
import com.phongdo.osahaneat.domain.entity.keys.KeyOrderItem;
import com.phongdo.osahaneat.repository.OrderItemRepository;
import com.phongdo.osahaneat.repository.OrderRepository;

@Service
public class OrderServiceImpl implements com.phongdo.osahaneat.service.OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public boolean insertOrder(OrderRequest orderRequest) {

        try {

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getResId());

            User user = new User();
            user.setId(orderRequest.getUserId());

            Orders orders = new Orders();
            orders.setUser(user);
            orders.setRestaurant(restaurant);
            orders.setCreateDate(new Date());
            orderRepository.save(orders);

            List<OrderItem> orderItems = new ArrayList<>();
            for (int foodId : orderRequest.getFoodIds()) {

                Food food = new Food();
                food.setId(foodId);

                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(), foodId);
                orderItem.setKeys(keyOrderItem);
                orderItem.setCareateDate(new Date());
                orderItems.add(orderItem);
            }
            orderItemRepository.saveAll(orderItems);

            return true;
        } catch (Exception e) {
            System.out.println("Error insert order" + e);
            return false;
        }
    }
}
