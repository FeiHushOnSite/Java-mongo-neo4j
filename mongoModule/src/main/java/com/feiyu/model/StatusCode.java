package com.feiyu.model;

public enum StatusCode {
    SUCCESSFUL(100000, "successful"),
    SUCCESSFUL_NO_DATA(100001, "successful no data"),
    SUCCESSFUL_WITH_DEFAULT_VALUE(100002, "successful with default value"),
    PARTIAL_SUCCESS(100003, "partial success"),
    AUTHORIZATION_ERROR(200000, "authorization error"),
    INVALID_SIGN(200001, "invalid signature"),
    PARAMETERS_MISSING(400001, "parameters missing"),
    INVALID_PARAMETERS(400002, "invalid parameters"),
    INTERNAL_ERROR(400003, "internal error"),
    BUSINESS_ERROR(500001, "business error");

    private int code;
    private String msg;

    private StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static StatusCode getByCode(int code) {
        StatusCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            StatusCode statusCode = var1[var3];
            if (statusCode.code == code) {
                return statusCode;
            }
        }

        return null;
    }

    public String toString() {
        return "[" + this.code + "-" + this.msg + "]";
    }
}
