package com.aaaccell.fixer.exception;

import com.aaaccell.fixer.response.ErrorResponse;

import java.io.IOException;

public class FixerException extends IOException {

    private ErrorResponse.Error error;

    public FixerException(ErrorResponse errorResponse) {
        super(errorResponse.getError().toString());
        this.error = errorResponse.getError();
    }

    public ErrorResponse.Error getError() {
        return error;
    }
}
