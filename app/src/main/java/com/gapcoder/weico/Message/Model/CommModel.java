package com.gapcoder.weico.Message.Model;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class CommModel {
    private int id;
    private int wid;
    private int hid;
    private int uid;
    private int oid;
    private String text;
    private int time;
    private String name;
    private String face;

    public CommModel(int id, int wid, int hid, int uid, int oid, String text, int time, String name, String face) {
        this.id = id;
        this.wid = wid;
        this.hid = hid;
        this.uid = uid;
        this.oid = oid;
        this.text = text;
        this.time = time;
        this.name = name;
        this.face = face;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
}
