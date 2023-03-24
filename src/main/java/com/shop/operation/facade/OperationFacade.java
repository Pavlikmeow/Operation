package com.shop.operation.facade;

import com.shop.operation.data.entity.Purchase;
import com.shop.operation.feign.ProductClient;
import com.shop.operation.feign.UserClient;
import com.shop.operation.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OperationFacade {

    private final OperationService operationService;

    private final UserClient userClient;
    private final ProductClient productClient;

    @Transactional
    public void purchase(Purchase purchase) {
        BigDecimal totalPrice = operationService.priceCalculate(purchase);
        userClient.purchase(purchase.getUserId(), totalPrice);
        productClient.purchase(purchase.getPurchaseCart());
        operationService.savePurchase(purchase);
    }
}
