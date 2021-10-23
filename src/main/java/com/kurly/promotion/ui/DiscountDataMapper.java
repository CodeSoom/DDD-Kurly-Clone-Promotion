package com.kurly.promotion.ui;

import com.kurly.promotion.domain.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountDataMapper {
    DiscountDataMapper INSTANCE = Mappers.getMapper(DiscountDataMapper.class);

    DiscountData toDto(Discount discount);
}
