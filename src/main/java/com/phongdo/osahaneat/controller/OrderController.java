package com.phongdo.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phongdo.osahaneat.dto.request.OrderRequest;
import com.phongdo.osahaneat.dto.response.ResponseData;
import com.phongdo.osahaneat.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping()
    public ResponseEntity<?> insertOrder(@RequestBody OrderRequest orderRequest) {
        ResponseData responseData = new ResponseData();
        responseData.setData(orderService.insertOrder(orderRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
