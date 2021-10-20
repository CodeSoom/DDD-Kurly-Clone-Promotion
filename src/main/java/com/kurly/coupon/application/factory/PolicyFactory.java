package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Policy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;

public interface PolicyFactory {

    boolean matchPolicy(Policy policy);

    CouponPolicy createPolicy(CouponPolicyRegisterData dto);
}
