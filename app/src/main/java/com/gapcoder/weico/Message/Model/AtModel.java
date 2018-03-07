package com.gapcoder.weico.Message.Model;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class AtModel {
    private int id;
    private int wid;
    private int hid;
    private int oid;

    public AtModel(int id, int wid, int hid, int oid) {
        this.id = id;
        this.wid = wid;
        this.hid = hid;
        this.oid = oid;
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

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }
}
