package com.kurly.promotion.dto;

import com.kurly.promotion.domain.DiscountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DiscountDto {

    @Getter
    @Builder
    public static class RegisterDiscount{
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
    }
}
