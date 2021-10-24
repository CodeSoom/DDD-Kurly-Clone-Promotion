package com.kurly.coupon.domain.policy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FixedAmountTest {

    @Test
    void create() {
        final Name name = Name.valueOf("test");
        final Keyword keyword = Keyword.valueOf("여름할인");
        final Period period = new Period();
        final Amount amount = Amount.valueOf(100);
        final TotalCount totalCount = TotalCount.valueOf(100);
        final MinimumRedeemPrice minimumRedeemPrice = MinimumRedeemPrice.valueOf(1000);
        FixedAmount fixedAmount = new FixedAmount(name, keyword, period, amount, totalCount, minimumRedeemPrice);

        assertThat(fixedAmount.getAmount()).isNotNull();
    }
}
