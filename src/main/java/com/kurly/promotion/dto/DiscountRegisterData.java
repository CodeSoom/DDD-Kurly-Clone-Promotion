package com.kurly.promotion.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 할인 등록 정보.
 */
@Getter
public class DiscountRegisterData {

    @NotNull
    private Long productId;

    @Range(min = 0, max = 100)
    private int flatRate;

    @Builder
    private DiscountRegisterData(Long productId, int flatRate) {
        this.productId = productId;
        this.flatRate = flatRate;
    }
}
