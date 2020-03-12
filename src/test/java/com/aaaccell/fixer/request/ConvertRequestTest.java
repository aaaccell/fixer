package com.aaaccell.fixer.request;

import com.aaaccell.fixer.Fixer;
import com.aaaccell.fixer.FixerRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class ConvertRequestTest {

    private final FixerRequestBuilder builder = Fixer.builder("key");

    @Test
    void requestEquals() {
        Assertions.assertEquals(
                builder
                        .convert()
                        .fromCurrency("USD")
                        .toCurrency("CHF")
                        .withAmount(BigDecimal.ONE)
                        .withDate(LocalDateTime.now().toLocalDate()),
                builder
                        .convert()
                        .fromCurrency("USD")
                        .toCurrency("CHF")
                        .withAmount(BigDecimal.ONE)
                        .withDate(LocalDateTime.now().toLocalDate())
        );
    }
}
