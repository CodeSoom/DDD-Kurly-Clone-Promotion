package com.kurly.promotion.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer flatRate;

    private Long productId;

    private Discount(Integer flatRate) {
        this.flatRate = flatRate;
    }

    public static Discount createFlatRateDiscount(Integer flatRate) {
        return new Discount(flatRate);
    }

    public void apply(Long productId) {
        this.productId = productId;
    }
}
