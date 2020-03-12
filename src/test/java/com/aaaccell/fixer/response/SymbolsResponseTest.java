package com.aaaccell.fixer.response;

import com.aaaccell.fixer.Fixer;
import com.aaaccell.fixer.FixerRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class SymbolsResponseTest {

    private final FixerRequestBuilder builder = Fixer.builder("key");

    @Test
    void requestEquals() {
        Assertions.assertEquals(
                new SymbolsResponse(true, new LinkedHashMap<>(Map.of("key", "value"))),
                new SymbolsResponse(true, new LinkedHashMap<>(Map.of("key", "value")))
        );
    }
}
