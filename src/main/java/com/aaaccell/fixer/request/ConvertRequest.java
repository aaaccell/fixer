package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.response.ConvertResponse;
import lombok.EqualsAndHashCode;
import retrofit2.Call;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class ConvertRequest extends AuthenticatedRequest<ConvertResponse> {

    private String from;
    private String to;
    private BigDecimal amount;
    private LocalDate date;

    public ConvertRequest(FixerService fixerService, String accessKey) {
        super(fixerService, accessKey);
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
