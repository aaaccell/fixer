package com.aaaccell.fixer.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TimeSeriesResponse extends Response {

    private boolean timeseries;
    private LocalDate startDate;
    private LocalDate endDate;
    private String base;
    private LinkedHashMap<String, HashMap<String, BigDecimal>> rates;

    public TimeSeriesResponse(boolean success, boolean timeseries, LocalDate startDate, LocalDate endDate, String base, LinkedHashMap<String, HashMap<String, BigDecimal>> rates) {
        super(success);
        this.timeseries = timeseries;
        this.startDate = startDate;
        this.endDate = endDate;
        this.base = base;
        this.rates = rates;
    }

    public boolean isTimeseries() {
        return timeseries;
    }

    public void setTimeseries(boolean timeseries) {
        this.timeseries = timeseries;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LinkedHashMap<String, HashMap<String, BigDecimal>> getRates() {
        return rates;
    }

    public void setRates(LinkedHashMap<String, HashMap<String, BigDecimal>> rates) {
        this.rates = rates;
    }
}
