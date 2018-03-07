package com.gapcoder.weico.General;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class MessageModel {
    private int id;
    private int uid;
    private int at;
    private int follow;
    private int comment;

    public MessageModel(int id, int uid, int at, int follow, int comment) {
        this.id = id;
        this.uid = uid;
        this.at = at;
        this.follow = follow;
        this.comment = comment;
    }

    public int getId() {
        return id;
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

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getTotal(){
        return at+follow+comment;
    }
}
