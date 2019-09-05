package com.aaaccell.fixer.models;

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


    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
