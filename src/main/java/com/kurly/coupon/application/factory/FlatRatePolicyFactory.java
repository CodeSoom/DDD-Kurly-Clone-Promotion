package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.*;
import com.kurly.coupon.dto.CouponPolicyPublishData;

import java.util.Objects;

public class FlatRatePolicyFactory implements PolicyFactory {

    @Override
    public boolean matchPolicy(PolicyType policyType) {
        return Objects.equals(PolicyType.FLAT_RATE, policyType);
    }

    @Override
    public CouponPolicy publishPolicy(CouponPolicyPublishData dto) {
        final Keyword generatedNumber = Keyword.valueOf(dto.getKeyword());
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());
        final Count count = Count.valueOf(dto.getCount());
        final Rate rate = Rate.valueOf(dto.getRate());
        final Name name = Name.valueOf(dto.getName());

        return CouponPolicy.publishRatePolicy(name, generatedNumber, period, rate, count);
    }
}
