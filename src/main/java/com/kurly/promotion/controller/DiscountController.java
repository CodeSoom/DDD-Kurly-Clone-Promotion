package com.kurly.promotion.controller;

import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.dto.DiscountRegisterData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * 할인과 관련된 HTTP 요청 처리를 담당합니다.
 */
@RequiredArgsConstructor
@RequestMapping("/discounts")
@RestController
public class DiscountController {

    private final DiscountService discountService;

    /**
     * 주어진 할인 정보를 등록합니다.
     *
     * @param discountRegisterData 할인 등록 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> register(
            @RequestBody @Valid DiscountRegisterData discountRegisterData
    ) {
        Long registeredId = discountService.registerDiscount(discountRegisterData);
        return ResponseEntity.created(URI.create("/discounts/" + registeredId)).build();
    }
}
