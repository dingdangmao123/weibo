package com.gapcoder.weico.Login;

import com.gapcoder.weico.General.SysMsg;

/**
 * Created by suxiaohui on 2018/3/8.
 */

public class LoginModel extends SysMsg {


    private InnerBean inner;

    public InnerBean getInner() {
        return inner;
    }

    public void setInner(InnerBean inner) {
        this.inner = inner;
    }

    public static class InnerBean {


        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
