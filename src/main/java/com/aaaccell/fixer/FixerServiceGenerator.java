package com.aaaccell.fixer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FixerServiceGenerator {
    private static final String BASE_URL = "https://data.fixer.io/api/";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new FixerResponseTypeAdapterFactory())
            .create();

    private static final Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = builder.build();

    private static final OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, HttpLoggingInterceptor.Level.BASIC);
    }

    public static <S> S createService(Class<S> serviceClass, HttpLoggingInterceptor.Level logLevel) {
        logging.setLevel(logLevel);
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }

}
