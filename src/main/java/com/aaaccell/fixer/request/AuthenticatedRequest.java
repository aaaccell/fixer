package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.response.Response;

import java.util.Objects;

public abstract class AuthenticatedRequest<T extends Response> extends Request<T> {

    FixerService fixerService;

    String accessKey;

    AuthenticatedRequest(FixerService fixerService, String accessKey) {
        this.fixerService = fixerService;
        this.accessKey = accessKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticatedRequest<?> that = (AuthenticatedRequest<?>) o;
        return Objects.equals(accessKey, that.accessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessKey);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
