package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.payload.ResponseData;
import com.phongdo.osahaneat.payload.request.OrderRequest;
import com.phongdo.osahaneat.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;
    @PostMapping()
    public ResponseEntity<?> insertOrder(@RequestBody OrderRequest orderRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.insertOrder(orderRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}

