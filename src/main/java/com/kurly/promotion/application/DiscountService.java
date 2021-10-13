package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.dto.DiscountRegisterData;
import com.kurly.promotion.infra.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 할인과 관련된 비즈니스 로직을 담당합니다.
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    /**
     * 주어진 할인 정보를 등록하고 할인 식별자를 리턴합니다.
     *
     * @param discountRegisterData 할인 등록 정보
     * @return 등록된 할인 식별자
     */
    @Transactional
    public Long registerDiscount(DiscountRegisterData discountRegisterData) {
        Discount discount = Discount.builder()
                .productId(discountRegisterData.getProductId())
                .flatRate(discountRegisterData.getFlatRate())
                .build();

        Discount savedDiscount = discountRepository.save(discount);
        return savedDiscount.getId();
    }
}
