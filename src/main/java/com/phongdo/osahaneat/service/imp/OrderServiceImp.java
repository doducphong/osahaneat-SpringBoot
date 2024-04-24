package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.request.OrderRequest;

public interface OrderServiceImp {
    boolean insertOrder(OrderRequest orderRequest);
}
