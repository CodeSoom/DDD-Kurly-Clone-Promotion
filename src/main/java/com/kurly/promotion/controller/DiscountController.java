package com.kurly.promotion.controller;

import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.domain.DiscountCommand;
import com.kurly.promotion.domain.DiscountDtoMapper;
import com.kurly.promotion.domain.DiscountType;
import com.kurly.promotion.dto.DiscountRegistrationData;
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
    private final DiscountDtoMapper discountDtoMapper;


    /**
     * 주어진 할인 정보를 등록합니다.
     * @param dto 할인 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> register(
            @RequestBody @Valid DiscountRegistrationData dto
    ) {
        DiscountCommand.RegisterDiscount requestDiscount = discountDtoMapper.of(dto);
        Long productId = dto.getProductId();
        DiscountType type = dto.getDiscountType();

        Long registeredId = discountService.registerDiscount(requestDiscount, productId, type);

        return ResponseEntity.created(URI.create("/discounts/" + registeredId)).build();
    }
}
