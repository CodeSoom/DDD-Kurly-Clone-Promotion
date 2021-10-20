package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Period;
import com.kurly.coupon.domain.Policy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;

import java.util.Objects;

public class FixedAmountPolicyFactory implements PolicyFactory {
    private final CouponNumberGenerator couponNumberGenerator;

    public FixedAmountPolicyFactory(CouponNumberGenerator couponNumberGenerator) {
        this.couponNumberGenerator = couponNumberGenerator;
    }

    @Override
    public boolean matchPolicy(Policy policy) {
        return Objects.equals(Policy.FIXED, policy);
    }

    @Override
    public CouponPolicy createPolicy(CouponPolicyRegisterData dto) {
        final String generatedNumber = couponNumberGenerator.generate();
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());

        return CouponPolicy.createWithFixedPolicy(dto.getName(), generatedNumber, period, dto.getCount(), dto.getAmount());
    }
}
