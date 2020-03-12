package com.aaaccell.fixer.response;

import java.util.Objects;

abstract public class Response {
    private boolean success;

    Response(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return success == response.success;
    }

    @Override
    public int hashCode() {
        return Objects.hash(success);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
