package com.shop.operation.controller;

import com.shop.operation.data.entity.Purchase;
import com.shop.operation.facade.OperationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationFacade operationFacade;

    @PostMapping("/purchase")
    public void purchase(@RequestBody Purchase purchase) {
        operationFacade.purchase(purchase);
    }
}
