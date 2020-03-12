package com.aaaccell.fixer.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class TimeSeriesResponse extends Response {

    private boolean timeseries;
    private LocalDate startDate;
    private LocalDate endDate;
    private String base;
    private LinkedHashMap<String, HashMap<String, BigDecimal>> rates;

    public TimeSeriesResponse(
            boolean success,
            boolean timeseries,
            LocalDate startDate,
            LocalDate endDate,
            String base,
            LinkedHashMap<String, HashMap<String, BigDecimal>> rates
    ) {
        super(success);
        this.timeseries = timeseries;
        this.startDate = startDate;
        this.endDate = endDate;
        this.base = base;
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeSeriesResponse that = (TimeSeriesResponse) o;
        return timeseries == that.timeseries &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(base, that.base) &&
                Objects.equals(rates, that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timeseries, startDate, endDate, base, rates);
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

    public void addRates(LinkedHashMap<String, HashMap<String, BigDecimal>> rates) {
        if (this.rates == null) {
            setRates(rates);
        } else {
            this.rates.putAll(rates);
        }
    }
}
