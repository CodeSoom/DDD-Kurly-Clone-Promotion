package com.kurly.coupon.application;

import com.kurly.coupon.application.factory.CouponFactory;
import com.kurly.coupon.domain.coupon.Coupon;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.dto.CouponIssueData;
import com.kurly.coupon.infra.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponFactory couponFactory;

    @Transactional
    public Long issueCoupon(CouponIssueData dto) {
        Optional<Coupon> findCoupon = findCoupon(dto);
        if (findCoupon(dto).isPresent()) {
            return increaseCouponCount(dto, findCoupon.get());
        }
        final Coupon coupon = couponFactory.issueCoupon(dto);
        final Coupon saved = couponRepository.save(coupon);
        return saved.getId();
    }

    private Long increaseCouponCount(CouponIssueData dto, Coupon coupon) {
        coupon.increaseCouponCount(dto.getCount());
        return coupon.getId();
    }

    private Optional<Coupon> findCoupon(CouponIssueData dto) {
        final Long userId = dto.getUserId();
        final Keyword keyword = Keyword.valueOf(dto.getKeyword());
        return couponRepository.findByUserIdAndKeyword(userId, keyword);
    }
}
