package com.isproj2.regainmobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.CommissionsDTO;
import com.isproj2.regainmobile.services.CommissionService;

@RestController
@RequestMapping("/api/commissions")
public class CommissionsController {

    @Autowired
    private CommissionService commissionService;

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<CommissionsDTO>> getCommsByUser(@PathVariable Integer userId) {
        List<CommissionsDTO> commList = commissionService.getCommissionsByUserId(userId);
        return ResponseEntity.ok(commList);
    }

    // @GetMapping("/logs/{orderId}")
    // public ResponseEntity<List<OrderLogDTO>> getOrderLogsByOrder(@PathVariable
    // Integer orderId) {
    // List<OrderLogDTO> orders = orderService.getOrderLogsByOrderId(orderId);
    // return ResponseEntity.ok(orders);
    // }

    // @GetMapping("/seller/{sellerId}")
    // public ResponseEntity<List<OrderDTO>> getOrdersBySeller(@PathVariable Integer
    // sellerId) {
    // List<OrderDTO> orders = orderService.getOrdersBySeller(sellerId);
    // return ResponseEntity.ok(orders);
    // }
}