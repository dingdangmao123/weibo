package com.gapcoder.weico.Login;

/**
 * Created by suxiaohui on 2018/3/8.
 */

public class LoginModel {
    private String token;
    private int ok;
    private String msg;

    public LoginModel(String token, int ok, String msg) {
        this.token = token;
        this.ok = ok;
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
