package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Period;
import com.kurly.coupon.domain.Policy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;

import java.util.Objects;

public class FlatRatePolicyFactory implements PolicyFactory {
    private final CouponNumberGenerator couponNumberGenerator;

    public FlatRatePolicyFactory(CouponNumberGenerator couponNumberGenerator) {
        this.couponNumberGenerator = couponNumberGenerator;
    }

    @Override
    public boolean matchPolicy(Policy policy) {
        return Objects.equals(Policy.FLAT, policy);
    }

    @Override
    public CouponPolicy createPolicy(CouponPolicyRegisterData dto) {
        final String generatedNumber = couponNumberGenerator.generate();
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());

        return CouponPolicy.createWithRatePolicy(dto.getName(), generatedNumber, period, dto.getRate(), dto.getCount());
    }
}
