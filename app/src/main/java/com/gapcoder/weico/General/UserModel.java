package com.gapcoder.weico.General;

/**
 * Created by suxiaohui on 2018/3/5.
 */

public class UserModel {

    private int id;
    private String face;
    private String name;
    private String sign;
    private String place;
    private String bg;
    private int fans;
    private int care;

    public UserModel(int id, String face, String name, String sign, String place, String bg, int fans, int care) {
        this.id = id;
        this.face = face;
        this.name = name;
        this.sign = sign;
        this.place = place;
        this.bg = bg;
        this.fans = fans;
        this.care = care;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getCare() {
        return care;
    }

    public void setCare(int care) {
        this.care = care;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
