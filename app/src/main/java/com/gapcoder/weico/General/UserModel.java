package com.gapcoder.weico.General;

/**
 * Created by suxiaohui on 2018/3/5.
 */

public class UserModel {


    /**
     * id : 1
     * phone : 18756969783
     * psd : 7c4a8d09ca3762af61e59520943dc26494f8941b
     * name : gapcoder
     * face : http://10.0.2.2/weico/face/f1.jpg
     * sign : 无招胜有招
     * place : 安徽 芜湖
     * bg : http://10.0.2.2/weico/photo/3.jpg
     * fans : 2
     * care : 2
     */

    private String id;
    private String phone;
    private String psd;
    private String name;
    private String face;
    private String sign;
    private String place;
    private String bg;
    private String fans;
    private String care;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }
}
