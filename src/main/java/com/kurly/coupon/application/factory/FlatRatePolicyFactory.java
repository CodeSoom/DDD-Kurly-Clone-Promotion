package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.domain.policy.MinimumRedeemPrice;
import com.kurly.coupon.domain.policy.Name;
import com.kurly.coupon.domain.policy.Period;
import com.kurly.coupon.domain.policy.PolicyType;
import com.kurly.coupon.domain.policy.Rate;
import com.kurly.coupon.domain.policy.TotalCount;
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
        final TotalCount totalCount = TotalCount.valueOf(dto.getCount());
        final Rate rate = Rate.valueOf(dto.getRate());
        final Name name = Name.valueOf(dto.getName());
        final MinimumRedeemPrice minimumRedeemPrice = MinimumRedeemPrice.valueOf(dto.getMinimumRedeemPrice());

        return CouponPolicy.publishRatePolicy(name, keyword, period, rate, totalCount, minimumRedeemPrice);
    }
}
