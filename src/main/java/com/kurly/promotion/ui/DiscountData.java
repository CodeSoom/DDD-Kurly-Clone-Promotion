package com.kurly.promotion.ui;

import lombok.Data;

@Data
public class DiscountData {
    private Long id;

    private Integer flatRate;

    private Long productId;
}
