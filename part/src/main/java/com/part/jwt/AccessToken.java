package com.part.jwt;

public class AccessToken {
    private Integer access_id;
    private String access_token;
    private String access_phone;

    public String getAccess_phone() {
        return access_phone;
    }

    public void setAccess_phone(String access_phone) {
        this.access_phone = access_phone;
    }

    public Integer getAccess_id() {
        return access_id;
    }
    public void setAccess_id(Integer access_id) {
        this.access_id = access_id;
    }

    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
