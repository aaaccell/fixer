package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.response.ConvertResponse;
import retrofit2.Call;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ConvertRequest extends AuthenticatedRequest<ConvertResponse> {

    private String from;
    private String to;
    private BigDecimal amount;
    private LocalDate date;

    public ConvertRequest(FixerService fixerService, String accessKey) {
        super(fixerService, accessKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConvertRequest that = (ConvertRequest) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), from, to, amount, date);
    }

    @Override
    Call<ConvertResponse> getCall() {
        return fixerService.convert(accessKey, from, to, amount, date);
    }

    public ConvertRequest fromCurrency(String currency) {
        this.from = currency;
        return this;
    }

    public ConvertRequest toCurrency(String currency) {
        this.to = currency;
        return this;
    }

    public ConvertRequest withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public ConvertRequest withDate(String date) {
        return withDate(LocalDate.parse(date));
    }

    public ConvertRequest withDate(LocalDate date) {
        this.date = date;
        return this;
    }

}
