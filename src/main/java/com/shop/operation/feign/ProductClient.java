package com.shop.operation.feign;

import com.shop.operation.data.dto.RefundRequest;
import com.shop.operation.data.entity.PurchaseCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product", url = "localhost:8083/product")
public interface ProductClient {

    @PostMapping("/purchase")
    void purchase(@RequestBody List<PurchaseCart> purchaseCartList);

    @PostMapping("/refund")
    void refund(@RequestBody RefundRequest refundRequest);
}
