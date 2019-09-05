package com.aaaccell.fixer;

import com.aaaccell.fixer.request.ConvertRequest;
import com.aaaccell.fixer.request.SymbolsRequest;
import com.aaaccell.fixer.request.TimeSeriesRequest;

public class FixerRequestBuilder {

    private FixerService fixerService;
    private String accessKey;

    public FixerRequestBuilder(FixerService fixerService, String accessKey) {
        this.fixerService = fixerService;
        this.accessKey = accessKey;
    }

    public SymbolsRequest symbols() {
        return new SymbolsRequest(fixerService, accessKey);
    }

    public TimeSeriesRequest timeSeries() {
        return new TimeSeriesRequest(fixerService, accessKey);
    }

    public ConvertRequest convert() { return new ConvertRequest(fixerService, accessKey); }

}
