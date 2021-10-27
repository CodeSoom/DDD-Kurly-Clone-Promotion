package com.kurly.promotion.domain;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class DiscountFactory {
    public Discount createDiscount(DiscountCommand.RegisterDiscount requestDiscount, DiscountType type) {
        switch(type){
            case FIXED_AMOUNT: return requestDiscount.createFixedAmoutDiscount();
            case FLAT_RATE : return requestDiscount.createFlatRateDiscount();
            default: new IllegalArgumentException("할인 타입은 필수 파라미터입니다.");
        }
        return null;
    }
}
