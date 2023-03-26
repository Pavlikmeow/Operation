package com.shop.operation.controller;

import com.shop.operation.data.entity.Purchase;
import com.shop.operation.facade.OperationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationFacade operationFacade;

    @PostMapping("/purchase")
    public void purchase(@RequestBody Purchase purchase) {
        operationFacade.purchase(purchase);
    }

    @PostMapping("/refund/{id}")
    public void refundById(@PathVariable UUID id) {
        operationFacade.refund(id);
    }
}
