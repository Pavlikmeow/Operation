package com.shop.operation.service;

import com.shop.operation.data.entity.Purchase;
import com.shop.operation.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final PurchaseRepository purchaseRepository;

    public BigDecimal priceCalculate(Purchase purchase) {
        final BigDecimal[] totalPrice = {new BigDecimal(0)};
        var purchaseCartList = purchase.getPurchaseCart();
        purchaseCartList.forEach(purchaseCart -> {
            totalPrice[0] = totalPrice[0].add(purchaseCart.
                    getPrice().
                    multiply(
                            new BigDecimal(purchaseCart.getQuantity())
                    )
            );
        });
        return totalPrice[0];
    }

    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
