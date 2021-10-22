package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.Count;
import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Keyword;
import com.kurly.coupon.domain.Name;
import com.kurly.coupon.domain.Period;
import com.kurly.coupon.domain.PolicyType;
import com.kurly.coupon.domain.Rate;
import com.kurly.coupon.dto.CouponPolicyPublishData;

import java.util.Objects;

public class FlatRatePolicyFactory implements PolicyFactory {

    @Override
    public boolean matchPolicy(PolicyType policyType) {
        return Objects.equals(PolicyType.FLAT_RATE, policyType);
    }

    @Override
    public CouponPolicy publishPolicy(CouponPolicyPublishData dto) {
        final Keyword keyword = Keyword.valueOf(dto.getKeyword());
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());
        final Count count = Count.valueOf(dto.getCount());
        final Rate rate = Rate.valueOf(dto.getRate());
        final Name name = Name.valueOf(dto.getName());

        return CouponPolicy.publishRatePolicy(name, keyword, period, rate, count);
    }
}
