package com.aaaccell.fixer.response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class SymbolsResponse extends Response {
    private LinkedHashMap<String, String> symbols;

    public SymbolsResponse(boolean success, LinkedHashMap<String, String> symbols) {
        super(success);
        this.symbols = symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SymbolsResponse that = (SymbolsResponse) o;
        return Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), symbols);
    }

    public HashMap<String, String> getSymbols() {
        return symbols;
    }

    public void setSymbols(LinkedHashMap<String, String> symbols) {
        this.symbols = symbols;
    }
}
