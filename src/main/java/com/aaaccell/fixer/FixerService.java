package com.aaaccell.fixer;

import com.aaaccell.fixer.response.ConvertResponse;
import com.aaaccell.fixer.response.SymbolsResponse;
import com.aaaccell.fixer.response.TimeSeriesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FixerService {

    @GET("/symbols")
    public Call<SymbolsResponse> getSymbols(@Query("access_key") String accessKey);

    @GET("/convert")
    public Call<ConvertResponse> convert(
            @Query("access_key") String accessKey,
            @Query("from") String from,
            @Query("to") String to,
            @Query("amount") BigDecimal amount,
            @Query("date") LocalDate date
    );

    @GET("/timeseries")
    public Call<TimeSeriesResponse> getTimeSeries(
            @Query("access_key") String accessKey,
            @Query("start_date") LocalDate startDate,
            @Query("end_date") LocalDate endDate,
            @Query("base") String base,
            @Query("symbols") String symbols
    );

}
