package com.cdilansrod.apirestlansrod.dto;

import java.io.Serializable;

public class OullaResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean status;
    private String code;
    private String message;
    private T record;

    // constructeur sans paramètre
    public OullaResponse() {
    }
    // constructeur avec 2 paramètres
    public OullaResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
    // constructeur avec tous les paramètres
    public OullaResponse(boolean status, String code, String message, T record) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.record = record;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public T getRecord() {
        return record;
    }

    public void setRecord(T record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "OullaResponse [status=" + status + ", code=" + code + ", message=" + message + ", record="
                + record + "]";
    }
}
