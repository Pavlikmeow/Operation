package com.shop.operation.repository;

import com.shop.operation.data.entity.Purchase;
import com.shop.operation.data.entity.PurchaseCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseCartRepository extends JpaRepository<PurchaseCart, UUID> {
}
