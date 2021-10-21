package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.*;
import com.kurly.coupon.dto.CouponPolicyPublishData;

import java.util.Objects;

public class FixedAmountPolicyFactory implements PolicyFactory {

    @Override
    public boolean matchPolicy(PolicyType policyType) {
        return Objects.equals(PolicyType.FIXED_AMOUNT, policyType);
    }

    @Override
    public CouponPolicy publishPolicy(CouponPolicyPublishData dto) {
        final Keyword keyword = Keyword.valueOf(dto.getKeyword());
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());
        final Name name = Name.valueOf(dto.getName());
        final Amount amount = Amount.valueOf(dto.getAmount());
        final Count count = Count.valueOf(dto.getCount());

        return CouponPolicy.publishFixedAmountPolicy(name, keyword, period, amount, count);
    }
}
