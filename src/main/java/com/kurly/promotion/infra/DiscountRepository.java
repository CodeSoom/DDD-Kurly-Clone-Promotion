package com.kurly.promotion.infra;

import com.kurly.promotion.domain.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 할인 저장소.
 */
@Repository
public interface DiscountRepository
        extends PagingAndSortingRepository<Discount, Long> {

    @Override
    Page<Discount> findAll(Pageable pageable);
}
