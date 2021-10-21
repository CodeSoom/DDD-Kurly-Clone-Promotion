package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.Count;
import com.kurly.coupon.domain.CouponCode;
import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Name;
import com.kurly.coupon.domain.Period;
import com.kurly.coupon.domain.Policy;
import com.kurly.coupon.domain.Rate;
import com.kurly.coupon.dto.CouponPolicyRegisterData;

import java.util.Objects;

public class FlatRatePolicyFactory implements PolicyFactory {
    private final CouponNumberGenerator couponNumberGenerator;

    public FlatRatePolicyFactory(CouponNumberGenerator couponNumberGenerator) {
        this.couponNumberGenerator = couponNumberGenerator;
    }

    @Override
    public boolean matchPolicy(Policy policy) {
        return Objects.equals(Policy.FLAT_RATE, policy);
    }

    @Override
    public CouponPolicy createPolicy(CouponPolicyRegisterData dto) {
        final CouponCode generatedNumber = CouponCode.of(couponNumberGenerator.generate());
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());
        final Count count = Count.valueOf(dto.getCount());
        final Rate rate = Rate.valueOf(dto.getRate());
        final Name name = Name.of(dto.getName());

        return CouponPolicy.createWithRatePolicy(name, generatedNumber, period, rate, count);
    }
}
