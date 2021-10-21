package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.*;
import com.kurly.coupon.dto.CouponPolicyRegisterData;

import java.util.Objects;

public class FixedAmountPolicyFactory implements PolicyFactory {

    @Override
    public boolean matchPolicy(Policy policy) {
        return Objects.equals(Policy.FIXED_AMOUNT, policy);
    }

    @Override
    public CouponPolicy createPolicy(CouponPolicyRegisterData dto) {
        final Keyword keyword = Keyword.valueOf(dto.getKeyword());
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());
        final Name name = Name.valueOf(dto.getName());
        final Amount amount = Amount.valueOf(dto.getAmount());
        final Count count = Count.valueOf(dto.getCount());

        return CouponPolicy.createWithFixedPolicy(name, keyword, period, amount, count);
    }
}
