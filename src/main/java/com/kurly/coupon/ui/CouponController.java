package com.kurly.coupon.ui;

import com.kurly.coupon.application.CouponService;
import com.kurly.coupon.dto.CouponPolicyPublishData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/coupons")
@RestController
public class CouponController {
    private final CouponService couponService;

    @PostMapping("/policies")
    public ResponseEntity<Void> publishCouponPolicy(@RequestBody @Valid CouponPolicyPublishData policyRegisterDto) {
        log.info("publish policy: {} ", policyRegisterDto);
        return ResponseEntity.created(URI.create("/coupons/policies/" + couponService.publishCouponPolicy(policyRegisterDto))).build();
    }
}
