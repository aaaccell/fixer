package com.aaaccell.fixer.request;

import com.aaaccell.fixer.Fixer;
import com.aaaccell.fixer.FixerRequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SymbolsRequestTest {

    private final FixerRequestBuilder builder = Fixer.builder("key");

    @Test
    void requestEquals() {
        Assertions.assertEquals(builder.symbols(), builder.symbols());
    }
}
