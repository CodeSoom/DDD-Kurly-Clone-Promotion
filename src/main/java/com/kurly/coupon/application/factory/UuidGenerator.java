package com.kurly.coupon.application.factory;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator implements CouponNumberGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
