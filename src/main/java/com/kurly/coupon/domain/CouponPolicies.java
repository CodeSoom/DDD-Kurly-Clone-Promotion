package com.kurly.coupon.domain;

import java.util.Collections;
import java.util.List;

public class CouponPolicies {
    private List<CouponPolicy> policies;

    public CouponPolicies(List<CouponPolicy> policies) {
        this.policies = policies;
    }

    public boolean isNameDuplicated(Name name) {
        return policies.stream()
                .anyMatch(v -> v.isSameName(name));
    }

    public boolean isKeywordDuplicated(Keyword keyword) {
        return policies.stream()
                .anyMatch(v -> v.isSameKeyword(keyword));
    }

    public List<CouponPolicy> getPolicies() {
        return Collections.unmodifiableList(policies);
    }
}
