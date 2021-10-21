package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.*;
import com.kurly.coupon.dto.CouponPolicyRegisterData;

import java.util.Objects;

public class FlatRatePolicyFactory implements PolicyFactory {

    @Override
    public boolean matchPolicy(Policy policy) {
        return Objects.equals(Policy.FLAT_RATE, policy);
    }

    @Override
    public CouponPolicy createPolicy(CouponPolicyRegisterData dto) {
        final Keyword generatedNumber = Keyword.valueOf(dto.getKeyword());
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());
        final Count count = Count.valueOf(dto.getCount());
        final Rate rate = Rate.valueOf(dto.getRate());
        final Name name = Name.valueOf(dto.getName());

        return CouponPolicy.createWithRatePolicy(name, generatedNumber, period, rate, count);
    }
}
