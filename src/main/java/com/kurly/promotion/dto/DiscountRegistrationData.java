package com.kurly.promotion.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 할인 등록 정보.
 */
@Getter
@NoArgsConstructor
public class DiscountRegistrationData {

    @NotNull
    private Long productId;

    @NotNull
    private Integer flatRate;

    @Builder
    private DiscountRegistrationData(Long productId, Integer flatRate) {
        this.productId = productId;
        this.flatRate = flatRate;
    }
}
