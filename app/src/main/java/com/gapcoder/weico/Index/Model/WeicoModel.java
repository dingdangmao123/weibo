package com.gapcoder.weico.Index.Model;

/**
 * Created by suxiaohui on 2018/3/2.
 */

public class WeicoModel {
    private int id;
    private int uid;

    private int tid;
    private String title;

    private String face;
    private String name;
    private int time;
    private String text;
    private int comment;
    private int like;

    public WeicoModel(int id, int uid, int tid, String title, String face, String name, int time, String text, int comment, int like) {
        this.id = id;
        this.uid = uid;
        this.tid = tid;
        this.title = title;
        this.face = face;
        this.name = name;
        this.time = time;
        this.text = text;
        this.comment = comment;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
