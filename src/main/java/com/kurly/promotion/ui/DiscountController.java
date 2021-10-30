package com.kurly.promotion.ui;

import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.domain.DiscountCommand;
import com.kurly.promotion.domain.DiscountDtoMapper;
import com.kurly.promotion.domain.DiscountType;
import com.kurly.promotion.dto.DiscountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
     *
     * @param requestDiscount 할인 등록 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> register(
            @RequestBody @Valid DiscountDto.RegisterDiscount requestDiscount
            ) {
        DiscountCommand.RegisterDiscount registerDiscount = discountDtoMapper.of(requestDiscount);
        Long productId = requestDiscount.getProductId();
        DiscountType type = requestDiscount.getDiscountType();

        Long registeredId = discountService.registerDiscount(
                registerDiscount,
                productId,
                type
        );

        return ResponseEntity.created(URI.create("/discounts/" + registeredId)).build();
    }

    @GetMapping
    public List<DiscountData> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Discount> discountList = discountService.getDiscounts(pageRequest);
        return discountList.stream()
                .map(DiscountDataMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 주어진 상품 식별자에 해당하는 할인 목록을 응답합니다.
     *
     * @param productId 상품 식별자
     * @return 할인 목록
     */
    @GetMapping("/products/{productId}")
    public List<DiscountData> listByProductId(@PathVariable Long productId) {
        List<Discount> discountList = discountService.getDiscountsByProductId(productId);
        return discountList.stream()
                .map(DiscountDataMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
