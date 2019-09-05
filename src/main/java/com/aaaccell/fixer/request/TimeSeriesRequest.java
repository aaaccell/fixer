package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.models.TimeSeriesResponse;
import retrofit2.Call;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TimeSeriesRequest extends AuthenticatedRequest<TimeSeriesResponse> {

    private LocalDate startDate;
    private LocalDate endDate;
    private String base;
    private String symbols;

    public TimeSeriesRequest(FixerService fixerService, String accessKey) {
        super(fixerService, accessKey);
    }

    @Override
    Call<TimeSeriesResponse> getCall() {
        return fixerService.getTimeSeries(accessKey, startDate, endDate, base, symbols);
    }

    public TimeSeriesRequest withStartDate(LocalDate date) {
        return buildStartDate(date);
    }

    public TimeSeriesRequest withStartDate(String date) {
        return buildStartDate(LocalDate.parse(date));
    }

    public TimeSeriesRequest withEndDate(LocalDate date) {
        return buildEndDate(date);
    }

    public TimeSeriesRequest withEndDate(String date) {
        return buildEndDate(LocalDate.parse(date));
    }

    public TimeSeriesRequest withBase(String currencyCode) {
        this.base = currencyCode;
        return this;
    }

    public TimeSeriesRequest forSymbols(List<String> symbols) {
        return buildSymbols(symbols);
    }

    public TimeSeriesRequest forSymbols(String... symbols) {
        return buildSymbols(Arrays.asList(symbols));
    }

    public TimeSeriesRequest forSymbol(String symbol) {
        return buildSymbols(Collections.singletonList(symbol));
    }

    public TimeSeriesRequest forAllSymbols() {
        this.symbols = null;
        return this;
    }

    private TimeSeriesRequest buildStartDate(LocalDate date) {
        this.startDate = date;
        return this;
    }

    private TimeSeriesRequest buildEndDate(LocalDate date) {
        this.endDate = date;
        return this;
    }

    private TimeSeriesRequest buildSymbols(Collection<String> symbols) {
        this.symbols = String.join(",", symbols);
        return this;
    }

}
