package com.kurly.coupon.dto;

import com.kurly.coupon.domain.PolicyType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class CouponPolicyPublishData {
    @NotNull
    private String name;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private PolicyType policyType;
    @NotNull
    private String keyword;
    private Integer rate;
    private Integer count;
    private Integer amount;


    @Builder
    public CouponPolicyPublishData(String name, LocalDate startDate, LocalDate endDate, PolicyType policyType, String keyword, Integer rate, Integer count, Integer amount) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.policyType = policyType;
        this.keyword = keyword;
        this.rate = rate;
        this.count = count;
        this.amount = amount;
    }
}
