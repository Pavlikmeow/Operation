package com.shop.operation.service;

import com.shop.operation.data.entity.PurchaseCart;
import com.shop.operation.repository.PurchaseCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseCartService {

    private final PurchaseCartRepository purchaseCartRepository;

    public List<PurchaseCart> getPurchaseCartListByPurchaseId(UUID id) {
        return purchaseCartRepository.findAllByPurchaseId(id);
    }
}
