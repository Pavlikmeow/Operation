package com.shop.operation.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.UUID;

@FeignClient(name = "user", url = "localhost:8082/user")
public interface UserClient {

    @PostMapping("/purchase/{id}/{value}")
    void purchase(@PathVariable UUID id, @PathVariable BigDecimal value);
}
