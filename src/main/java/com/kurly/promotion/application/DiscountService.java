package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.domain.DiscountCommand;
import com.kurly.promotion.domain.DiscountFactory;
import com.kurly.promotion.domain.DiscountType;
import com.kurly.promotion.dto.DiscountRegistrationData;
import com.kurly.promotion.infra.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DiscountService {
    private final DiscountFactory discountFactory;
    private final DiscountRepository discountRepository;

    @Transactional
    public Long registerDiscount(
            DiscountCommand.RegisterDiscount requestDiscount,
            Long productId,
            DiscountType type
    ) {
        Discount discount = discountFactory.createDiscount(requestDiscount, type);
        discount.apply(productId);

        Discount savedDiscount = discountRepository.save(discount);

        return savedDiscount.getId();
    }
}
