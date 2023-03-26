package com.shop.operation.data.mapper;

import com.shop.operation.data.dto.PurchaseCartDTO;
import com.shop.operation.data.entity.PurchaseCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseCartMapper {

    PurchaseCartDTO mapToPurchaseCartDTO(PurchaseCart purchaseCart);
}
