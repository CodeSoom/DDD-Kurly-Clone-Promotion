package com.kurly.promotion.domain;

import com.kurly.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 할인.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Discount extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private int flatRate;

    @Builder
    private Discount(Long id, int flatRate, Long productId) {
        this.id = id;
        this.flatRate = flatRate;
        this.productId = productId;
    }
}
