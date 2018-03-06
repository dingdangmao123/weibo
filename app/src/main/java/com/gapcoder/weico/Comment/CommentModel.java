package com.gapcoder.weico.Comment;

/**
 * Created by suxiaohui on 2018/3/6.
 */

public class CommentModel {

    private int id;
    private int wid;
    private int uid;
    private int oid;
    private String text;
    private int time;

    public CommentModel(int id, int wid, int uid, int oid, String text, int time) {
        this.id = id;
        this.wid = wid;
        this.uid = uid;
        this.oid = oid;
        this.text = text;
        this.time = time;
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

    @Override
    public String toString() {
        return "CommentModel{" +
                "id=" + id +
                ", wid=" + wid +
                ", uid=" + uid +
                ", oid=" + oid +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
