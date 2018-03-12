package com.gapcoder.weico.Index.Model;

import com.gapcoder.weico.General.SysMsg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/2.
 */

public class WeicoModel extends SysMsg{

    private LinkedList<InnerBean> inner;

    public LinkedList<InnerBean> getInner() {
        return inner;
    }

    public void setInner(LinkedList<InnerBean> inner) {
        this.inner = inner;
    }

    public static class InnerBean {
        /**
         * id : 29
         * uid : 4
         * name : Apple
         * face : http://10.0.2.2/weico/face/f4.jpg
         * text : this is a beautiful day! @gapcoder @Apple
         * time : 1520419999
         * comment : 0
         * love : 0
         */

        private int id;
        private int uid;
        private String name;
        private String face;
        private String text;
        private int time;
        private int comment;
        private int love;
        private String photo;

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

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getLove() {
            return love;
        }

        public void setLove(int love) {
            this.love = love;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }





  /*  private int id;
    private int uid;

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

    @Override
    public String toString() {
        return "WeicoModel{" +
                "id=" + id +
                ", uid=" + uid +
                ", tid=" + tid +
                ", title='" + title + '\'' +
                ", face='" + face + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", text='" + text + '\'' +
                ", comment=" + comment +
                ", like=" + like +
                '}';
    }*/
}
