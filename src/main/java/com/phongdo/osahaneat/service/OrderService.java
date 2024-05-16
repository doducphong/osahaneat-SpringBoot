package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.entity.*;
import com.phongdo.osahaneat.entity.keys.KeyOrderItem;
import com.phongdo.osahaneat.dto.request.OrderRequest;
import com.phongdo.osahaneat.repository.OrderItemRepository;
import com.phongdo.osahaneat.repository.OrderRepository;
import com.phongdo.osahaneat.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @Transactional
    @Override
    public boolean insertOrder(OrderRequest orderRequest) {

        try{

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
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(),foodId);
                orderItem.setKeys(keyOrderItem);
                orderItem.setCareateDate(new Date());
                orderItems.add(orderItem);
            }
            orderItemRepository.saveAll(orderItems);

            return true;
        }catch (Exception e){
            System.out.println("Error insert order" + e);
            return false;
        }
    }
}
