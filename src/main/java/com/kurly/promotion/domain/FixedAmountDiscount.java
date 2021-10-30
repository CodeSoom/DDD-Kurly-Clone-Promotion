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
@DiscriminatorValue("AMOUNT")
public class FixedAmountDiscount extends Discount {

    @Column(name = "fixed_amount", nullable = false)
    private Integer fixedAmount;

    @Builder
    public FixedAmountDiscount(Period period, Integer fixedAmount) {
        super(period);

        if(fixedAmount == null){
            throw new InvalidParameterException("FixedAmountDiscount.fixedAmount");
        }

        this.fixedAmount = fixedAmount;
    }

    protected FixedAmountDiscount() { }
}
