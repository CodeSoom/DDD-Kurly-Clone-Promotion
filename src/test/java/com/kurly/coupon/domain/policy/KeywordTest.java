package com.kurly.coupon.domain.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordTest {

    @DisplayName("쿠폰 키워드를 생성할 수 있다.")
    @Test
    void create() {
        final String givenKeyword = "여름할인";
        final Keyword keyword = Keyword.valueOf(givenKeyword);

        assertThat(keyword.getValue()).isEqualTo(givenKeyword);
    }
}
