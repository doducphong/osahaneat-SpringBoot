package com.phongdo.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongdo.osahaneat.domain.entity.OrderItem;
import com.phongdo.osahaneat.domain.entity.keys.KeyOrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {}
