package com.kurly.coupon.dto;

import com.kurly.coupon.domain.Policy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class CouponPolicyRegisterData {
    @NotNull
    private String name;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Policy policy;
    @NotNull
    private String keyword;
    private Integer rate;
    private Integer count;
    private Integer amount;


    @Builder
    public CouponPolicyRegisterData(String name, LocalDate startDate, LocalDate endDate, Policy policy, String keyword, Integer rate, Integer count, Integer amount) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.policy = policy;
        this.keyword = keyword;
        this.rate = rate;
        this.count = count;
        this.amount = amount;
    }
}
