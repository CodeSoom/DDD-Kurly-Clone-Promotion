package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.domain.DiscountCommand;
import com.kurly.promotion.domain.DiscountFactory;
import com.kurly.promotion.domain.DiscountType;
import com.kurly.promotion.infra.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Discount> getDiscounts(PageRequest pageRequest) {
        return discountRepository.findAll(pageRequest)
                .getContent();
    }

    /**
     * 주어진 상품 식별자에 해당하는 할인 목록을 리턴합니다.
     *
     * @param productId 상품 식별자
     * @return 할인 목록
     */
    public List<Discount> getDiscountsByProductId(Long productId) {
        return discountRepository.findAllByProductId(productId);
    }
}
