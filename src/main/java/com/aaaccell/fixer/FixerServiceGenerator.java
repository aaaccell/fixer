package com.aaaccell.fixer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Collections;

public class FixerServiceGenerator {
    protected static final String BASE_URL = "https://data.fixer.io/api/";

    protected OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();
    }

    protected Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new FixerResponseTypeAdapterFactory())
                .create();
    }

    public <S> S createService(Class<S> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(createHttpClient())
                .build()
                .create(serviceClass);
    }

}
