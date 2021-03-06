package com.aaaccell.fixer.response;

import java.math.BigDecimal;
import java.util.Objects;

public class ConvertResponse extends Response {

    public class Query {
        private String from;
        private String to;
        private BigDecimal amount;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }

    public class Info {
        private Integer timestamp;
        private BigDecimal rate;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return Objects.equals(timestamp, info.timestamp) &&
                    Objects.equals(rate, info.rate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, rate);
        }
    }

    private Query query;
    private Info info;
    private String date;
    private boolean historical;
    private BigDecimal result;

    public ConvertResponse(boolean success, Query query, Info info, String date, boolean historical, BigDecimal result) {
        super(success);
        this.query = query;
        this.info = info;
        this.date = date;
        this.historical = historical;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConvertResponse that = (ConvertResponse) o;
        return historical == that.historical &&
                Objects.equals(query, that.query) &&
                Objects.equals(info, that.info) &&
                Objects.equals(date, that.date) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), query, info, date, historical, result);
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isHistorical() {
        return historical;
    }

    public void setHistorical(boolean historical) {
        this.historical = historical;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
