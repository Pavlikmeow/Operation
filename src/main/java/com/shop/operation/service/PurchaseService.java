package com.shop.operation.service;

import com.shop.operation.data.entity.Purchase;
import com.shop.operation.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public Purchase findPurchaseById(UUID id) {
        return purchaseRepository.findPurchaseById(id);
    }

    @Transactional
    public void setRefunded(UUID id) {
        Purchase purchase = purchaseRepository.findPurchaseById(id);
        purchase.setRefunded(true);
    }

}
