package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.request.OrderRequest;

public interface OrderService {
    boolean insertOrder(OrderRequest orderRequest);
}
