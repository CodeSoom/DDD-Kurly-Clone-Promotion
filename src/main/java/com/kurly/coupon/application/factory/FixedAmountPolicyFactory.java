package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.policy.Amount;
import com.kurly.coupon.domain.policy.Count;
import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.domain.policy.MinimumRedeemPrice;
import com.kurly.coupon.domain.policy.Name;
import com.kurly.coupon.domain.policy.Period;
import com.kurly.coupon.domain.policy.PolicyType;
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
        final MinimumRedeemPrice minimumRedeemPrice = MinimumRedeemPrice.valueOf(dto.getMinimumRedeemPrice());

        return CouponPolicy.publishFixedAmountPolicy(name, keyword, period, amount, count, minimumRedeemPrice);
    }
}
