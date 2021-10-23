package com.kurly.promotion.ui;

import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.dto.DiscountRegistrationData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
     * @param discountRegistrationData 할인 등록 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> register(
            @RequestBody @Valid DiscountRegistrationData discountRegistrationData
    ) {
        Long registeredId = discountService.registerDiscount(
                discountRegistrationData.getFlatRate(), discountRegistrationData.getProductId()
        );
        return ResponseEntity.created(URI.create("/discounts/" + registeredId)).build();
    }

    @GetMapping
    public List<DiscountData> list() {
        DiscountList discountList = new DiscountList(
                discountService.getDiscounts()
        );
        return discountList.list();
    }
}
