package com.aaaccell.fixer.response;

import java.util.Objects;

public class ErrorResponse extends Response {

    public class Error {
        private Integer code;
        private String type;
        private String info;

        public Error(Integer code, String type, String info) {
            this.code = code;
            this.type = type;
            this.info = info;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Error error = (Error) o;
            return Objects.equals(code, error.code) &&
                    Objects.equals(type, error.type) &&
                    Objects.equals(info, error.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code, type, info);
        }

        public Integer getCode() {
            return code;
        }

        public String getType() {
            return type;
        }

        public String getInfo() {
            return info;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code=" + code +
                    ", type='" + type + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    private Error error;

    ErrorResponse(boolean success) {
        super(success);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), error);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
