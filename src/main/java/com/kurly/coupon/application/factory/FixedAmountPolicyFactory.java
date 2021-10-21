package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.Amount;
import com.kurly.coupon.domain.Count;
import com.kurly.coupon.domain.CouponNumber;
import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Name;
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
        return Objects.equals(Policy.FIXED_AMOUNT, policy);
    }

    @Override
    public CouponPolicy createPolicy(CouponPolicyRegisterData dto) {
        final CouponNumber generatedNumber = CouponNumber.of(couponNumberGenerator.generate());
        final Period period = Period.createPeriod(dto.getStartDate(), dto.getEndDate());
        final Name name = Name.of(dto.getName());
        final Amount amount = Amount.valueOf(dto.getAmount());
        final Count count = Count.valueOf(dto.getCount());

        return CouponPolicy.createWithFixedPolicy(name, generatedNumber, period, amount, count);
    }
}
