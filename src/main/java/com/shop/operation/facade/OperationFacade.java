package com.shop.operation.facade;

import com.shop.operation.data.entity.Purchase;
import com.shop.operation.feign.ProductClient;
import com.shop.operation.feign.UserClient;
import com.shop.operation.service.OperationService;
import com.shop.operation.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationFacade {

    private final OperationService operationService;

    private final UserClient userClient;
    private final ProductClient productClient;
    private final PurchaseService purchaseService;

    @Transactional
    public void purchase(Purchase purchase) {
        BigDecimal totalPrice = operationService.priceCalculate(purchase);
        userClient.purchase(purchase.getUserId(), totalPrice);
        productClient.purchase(purchase.getPurchaseCart());
        purchase.setTotalPrice(totalPrice);
        operationService.savePurchase(purchase);
    }

    @Transactional
    public void refund(UUID id) {
        operationService.refundToUser(id);
        operationService.refundToProduct(id);
        purchaseService.setRefunded(id);
    }
}
