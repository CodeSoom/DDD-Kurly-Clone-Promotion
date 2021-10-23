package com.kurly.promotion.ui;

import com.kurly.promotion.domain.Discount;

import java.util.List;
import java.util.stream.Collectors;

public class DiscountList {
    private final List<Discount> discountList;

    public DiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    public List<DiscountData> list() {
        return this.discountList.stream()
                .map(DiscountDataMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
