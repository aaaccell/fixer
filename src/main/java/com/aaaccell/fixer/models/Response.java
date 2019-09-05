package com.aaaccell.fixer.models;

abstract public class Response {
    private boolean success;

    Response(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
