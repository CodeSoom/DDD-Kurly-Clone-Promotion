package com.kurly.promotion.infra;

import com.kurly.promotion.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 할인 저장소.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
