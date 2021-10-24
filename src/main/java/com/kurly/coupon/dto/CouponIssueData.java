package com.kurly.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CouponIssueData {
    @NotNull
    private Long userId;
    @NotNull
    private String keyword;
    @NotNull
    private Integer count;

    @Builder
    public CouponIssueData(Long userId, String keyword, Integer count) {
        this.userId = userId;
        this.keyword = keyword;
        this.count = count;
    }
}
