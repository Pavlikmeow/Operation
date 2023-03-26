package com.shop.operation.service;

import com.shop.operation.data.dto.PurchaseCartDTO;
import com.shop.operation.data.dto.RefundRequest;
import com.shop.operation.data.entity.Purchase;
import com.shop.operation.data.mapper.PurchaseCartMapper;
import com.shop.operation.feign.ProductClient;
import com.shop.operation.feign.UserClient;
import com.shop.operation.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseService purchaseService;
    private final PurchaseCartService purchaseCartService;
    private final UserClient userClient;
    private final ProductClient productClient;
    private final PurchaseCartMapper purchaseCartMapper;

    public BigDecimal priceCalculate(Purchase purchase) {
        final BigDecimal[] totalPrice = {new BigDecimal(0)};
        var purchaseCartList = purchase.getPurchaseCart();
        purchaseCartList.forEach(purchaseCart -> {
            BigDecimal price = purchaseCart.getPrice();
            if (price != null) {
                totalPrice[0] = totalPrice[0]
                        .add(price.multiply(new BigDecimal(purchaseCart.getQuantity())));
            } else {
                throw new RuntimeException(String.format("Price for purchaseCart with id : {%s} is null", purchaseCart.getId()));
            }
        });
        return totalPrice[0];
    }

    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public void refundToUser(UUID id) {
        Purchase purchase = purchaseService.findPurchaseById(id);
        if (purchase.isRefunded()) {
            throw new RuntimeException(String.format("The purchase with id {%s} is already refunded", id));
        }
        BigDecimal totalPrice = purchase.getTotalPrice();
        UUID userId = purchase.getUserId();
        userClient.refund(userId, totalPrice);
    }

    public void refundToProduct(UUID id) {
        RefundRequest refundRequest = new RefundRequest();
        var purchaseCartList = purchaseCartService.getPurchaseCartListByPurchaseId(id);
        List<PurchaseCartDTO> purchaseCartDTOList = new ArrayList<>();
        purchaseCartList.forEach(purchaseCart -> {
            PurchaseCartDTO purchaseCartDTO = purchaseCartMapper.mapToPurchaseCartDTO(purchaseCart);
            purchaseCartDTOList.add(purchaseCartDTO);
        });
        refundRequest.setPurchaseCartDTOList(purchaseCartDTOList);
        productClient.refund(refundRequest);
    }

}
