package com.kurly.promotion.ui;

import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.domain.Discount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/discounts")
@RestController
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public List<DiscountData> list() {
        DiscountList discountList = new DiscountList(
                discountService.getDiscounts()
        );
        return discountList.list();
    }
}
