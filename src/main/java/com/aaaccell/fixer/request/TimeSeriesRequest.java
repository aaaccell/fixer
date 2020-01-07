package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.response.TimeSeriesResponse;
import com.aaaccell.fixer.util.LocalDateHelper;
import lombok.EqualsAndHashCode;
import retrofit2.Call;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
public class TimeSeriesRequest extends AuthenticatedRequest<TimeSeriesResponse> {

    private static final long MAX_TIME_FRAME = 365;

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

    /**
     * Calls Time-Series Endpoint
     *
     * If the given start and end dates overcome the maximum time frame of 365 days (see https://fixer.io/documentation),
     * then the execution of the calls are segmented and their results (e.g. rates) combined to a single response.
     *
     * @return {@link TimeSeriesResponse}
     * @throws IOException api error
     */
    public TimeSeriesResponse call() throws IOException {
        List<Call<TimeSeriesResponse>> calls = LocalDateHelper
                .splitDateRange(startDate, endDate, Duration.ofDays(MAX_TIME_FRAME))
                .stream()
                .map(tuple -> fixerService.getTimeSeries(accessKey, tuple.a, tuple.b, base, symbols))
                .collect(Collectors.toList());

        if (calls.size() == 1) {
            return super.call();
        }

        ExecutorService executor = Executors.newFixedThreadPool(calls.size());
        List<Future<TimeSeriesResponse>> futures = calls
                .stream()
                .map(call -> executor.submit(() -> call.execute().body()))
                .collect(Collectors.toList());

        TimeSeriesResponse response = new TimeSeriesResponse(true, true, startDate, endDate, base, new LinkedHashMap<>());
        for (Future<TimeSeriesResponse> future : futures) {
            TimeSeriesResponse body = null;
            try {
                body = future.get();
                if (body != null && !body.isSuccess()) {
                    response.setSuccess(false);
                }
                if (body != null) {
                    response.addRates(body.getRates());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                throw new IOException(e);
            }

        }

        return response;
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
