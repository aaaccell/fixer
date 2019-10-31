package com.aaaccell.fixer.request;

import com.aaaccell.fixer.FixerService;
import com.aaaccell.fixer.response.Response;

public abstract class AuthenticatedRequest<T extends Response> extends Request<T> {
    FixerService fixerService;
    String accessKey;

    AuthenticatedRequest(FixerService fixerService, String accessKey) {
        this.fixerService = fixerService;
        this.accessKey = accessKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
