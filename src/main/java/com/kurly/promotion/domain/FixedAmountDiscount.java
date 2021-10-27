package com.kurly.promotion.domain;

import com.kurly.promotion.domain.vo.Period;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.security.InvalidParameterException;

/**
 * 정액 할인
 */
@Getter
@Entity
@DiscriminatorValue("fixedAmount")
public class FixedAmountDiscount extends Discount {

    @Column(name = "fixed_amount", nullable = false)
    private Integer fixedAmount;

    @Builder
    public FixedAmountDiscount(Period period, Integer fixedAmount) {
        super(period);

        if(fixedAmount == null){
            throw new InvalidParameterException("빈 값을 입력할 수 없습니다.");
        }

        this.fixedAmount = fixedAmount;
    }

    protected FixedAmountDiscount() { }
}
