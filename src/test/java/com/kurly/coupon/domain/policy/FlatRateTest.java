package com.kurly.coupon.domain.policy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FlatRateTest {

    @Test
    void create() {
        final Name name = Name.valueOf("test");
        final Keyword keyword = Keyword.valueOf("여름할인");
        final Period period = new Period();
        final Rate rate = Rate.valueOf(100);
        final TotalCount totalCount = TotalCount.valueOf(100);
        final MinimumRedeemPrice minimumRedeemPrice = MinimumRedeemPrice.valueOf(1000);
        final FlatRate flatRate = new FlatRate(name, keyword, period, rate, totalCount, minimumRedeemPrice);

        assertThat(flatRate.getRate()).isNotNull();
    }
}
