package com.aaaccell.fixer;

import com.aaaccell.fixer.models.ConvertResponse;
import com.aaaccell.fixer.models.TimeSeriesResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class FixerBuilderTest {
    private FixerRequestBuilder builder = Fixer.builder(
            System.getProperty("fixerApiKey") != null ?
                    System.getProperty("fixerApiKey") :
                    System.getenv("FIXER_API_KEY")
    );

    @Test
    void symbolsRequestTest() throws IOException {
        Assertions.assertTrue(0 < builder.symbols().call().getSymbols().keySet().size());
    }

    @Test
    void convertRequestTest() throws IOException {
        ConvertResponse r = builder
                .convert()
                .withDate(LocalDate.parse("2019-01-01"))
                .withAmount(BigDecimal.valueOf(1))
                .fromCurrency("CHF")
                .toCurrency("EUR")
                .call();
        Assertions.assertEquals(0.888317, r.getResult().doubleValue());
    }

    @Test
    void timeSeriesRequestTest() throws IOException {
        TimeSeriesResponse r = builder.timeSeries()
                .withBase("CHF")
                .forSymbols("EUR", "USD")
                .withStartDate("2012-05-01")
                .withEndDate("2012-05-05")
                .call();
        Assertions.assertEquals(5, r.getRates().size());
        Assertions.assertEquals(10L, r.getRates().values().stream()
                .map(HashMap::values)
                .flatMap(Collection::stream)
                .mapToDouble(BigDecimal::doubleValue)
                .count()
        );
    }

    @Test
    void timeSeriesRequestSegmentedTest() throws IOException {
        TimeSeriesResponse r = builder.timeSeries()
                .withBase("CHF")
                .forSymbols("EUR", "USD")
                .withStartDate("2012-05-01")
                .withEndDate("2013-05-05")
                .call();
        Assertions.assertEquals(370, r.getRates().size());
        Assertions.assertEquals(740, r.getRates().values().stream()
                .map(HashMap::values)
                .flatMap(Collection::stream)
                .mapToDouble(BigDecimal::doubleValue)
                .count()
        );

        Assertions.assertTrue(isSorted(r.getRates().entrySet()));
    }

    @Test
    void timeSeriesRequestSegmentedWithLeapYearTest() throws IOException {
        TimeSeriesResponse r = builder.timeSeries()
                .withBase("CHF")
                .forSymbols("EUR", "USD")
                .withStartDate("2012-05-01")
                .withEndDate("2018-05-05")
                .call();
        Assertions.assertTrue(isSorted(r.getRates().entrySet()));
    }

    private boolean isSorted(Set<Map.Entry<String, HashMap<String, BigDecimal>>> entrySet) {
        LocalDate d = LocalDate.parse("2010-01-01");
        for (Map.Entry<String, HashMap<String, BigDecimal>> me : entrySet) {
            LocalDate date = LocalDate.parse(me.getKey());
            if (!date.isAfter(d)) {
                return false;
            }
            d = date;
        }
        return true;
    }
}
