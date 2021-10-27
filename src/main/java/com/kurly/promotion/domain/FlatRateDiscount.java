package com.kurly.promotion.domain;

import com.kurly.promotion.domain.vo.Period;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.security.InvalidParameterException;

/**
 * 정률 할인
 */
@Getter
@DiscriminatorValue("Rate")
@Entity
public class FlatRateDiscount extends Discount{
    private static final int MINIMUM_RATE = 0;
    private static final int MAXIMUM_RATE = 100;

    @Column(name = "flat_rate", nullable = false)
    private Integer flatRate;

    @Builder
    public FlatRateDiscount(Period period, Integer flatRate) {
        super(period);

        if(flatRate == null){
            throw new InvalidParameterException("flatRate에 빈 값을 입력할 수 없습니다.");
        }
        if (flatRate <= MINIMUM_RATE) {
            throw new IllegalArgumentException("0보다 작은 값을 입력할 수 없습니다.");
        }
        if (flatRate > MAXIMUM_RATE) {
            throw new IllegalArgumentException("최대 할인 값을 초과하였습니다.");
        }

        this.flatRate = flatRate;
    }

    protected FlatRateDiscount() {    }
}
