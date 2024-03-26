package com.phongdo.osahaneat.repository;

import com.phongdo.osahaneat.entity.OrderItem;
import com.phongdo.osahaneat.entity.keys.KeyOrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
