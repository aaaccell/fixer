package com.aaaccell.fixer.models;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SymbolsResponse extends Response {
    private LinkedHashMap<String, String> symbols;

    public SymbolsResponse(boolean success, LinkedHashMap<String, String> symbols) {
        super(success);
        this.symbols = symbols;
    }

    public HashMap<String, String> getSymbols() {
        return symbols;
    }

    public void setSymbols(LinkedHashMap<String, String> symbols) {
        this.symbols = symbols;
    }
}
