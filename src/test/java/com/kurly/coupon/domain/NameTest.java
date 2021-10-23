package com.kurly.coupon.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class NameTest {

    @Test
    void create() {
        final Name name = Name.valueOf("첫설문 참여 감사 할인");

        assertThat(name).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void create_with_blank_data(String givenName) {
        assertThatCode(() -> Name.valueOf(givenName)).isInstanceOf(IllegalArgumentException.class);
    }
}
