package com.aaaccell.fixer.request;

import com.aaaccell.fixer.response.Response;
import retrofit2.Call;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.Callable;

public abstract class Request<T extends Response> implements Callable, Serializable {

    abstract Call<T> getCall();

    public T call() throws IOException {
        return getCall().execute().body();
    }

}
