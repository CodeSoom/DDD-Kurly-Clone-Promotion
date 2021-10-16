package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.infra.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Transactional
    public Long registerDiscount(
            Integer flatRate,
            Long productId
    ) {
        Discount discount = Discount.createFlatRateDiscount(flatRate);
        discount.apply(productId);

        Discount savedDiscount = discountRepository.save(discount);

        return savedDiscount.getId();
    }
}
