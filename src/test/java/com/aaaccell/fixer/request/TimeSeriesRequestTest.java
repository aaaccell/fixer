package com.aaaccell.fixer.request;

import com.aaaccell.fixer.Fixer;
import com.aaaccell.fixer.FixerRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TimeSeriesRequestTest {

    private final FixerRequestBuilder builder = Fixer.builder("key");

    @Test
    void requestEquals() {
        TimeSeriesRequest request1 = builder
                .timeSeries()
                .withBase("USD")
                .forSymbols("CHF")
                .withStartDate(LocalDateTime.now().minusDays(1).toLocalDate())
                .withEndDate(LocalDateTime.now().toLocalDate());

        TimeSeriesRequest request2 = builder
                .timeSeries()
                .withBase("USD")
                .forSymbols("CHF")
                .withStartDate(LocalDateTime.now().minusDays(1).toLocalDate())
                .withEndDate(LocalDateTime.now().toLocalDate());

        Assertions.assertEquals(request1, request2);
    }
}
