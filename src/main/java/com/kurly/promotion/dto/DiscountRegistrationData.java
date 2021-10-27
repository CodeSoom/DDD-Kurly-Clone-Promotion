package com.kurly.promotion.dto;

import com.kurly.promotion.domain.DiscountType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * 할인 등록 정보.
 */
@Getter
public class DiscountRegistrationData {

    @NotNull
    private Long productId;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private DiscountType discountType;
    private Integer flatRate;
    private Integer fixedAmount;

    @Builder
    public DiscountRegistrationData(Long productId, LocalDate startDate, LocalDate endDate, DiscountType discountType, Integer flatRate, Integer fixedAmount) {
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountType = discountType;
        this.flatRate = flatRate;
        this.fixedAmount = fixedAmount;
    }
}
