package com.gapcoder.weico.Message.Model;

import com.gapcoder.weico.Comment.Comm;
import com.gapcoder.weico.Comment.CommentModel;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by suxiaohui on 2018/3/7.
 */


public class AtModel {
    private LinkedList<InnerAtModel>  inner;
    private HashMap<Integer,UserBean> user;

    public AtModel() {
        inner=new  LinkedList<InnerAtModel>();
        user=new HashMap<Integer,UserBean>();
    }

    public AtModel(LinkedList<InnerAtModel> comment, HashMap<Integer, UserBean> user) {
        this.inner = comment;
        this.user = user;
    }

    public LinkedList<InnerAtModel> getInner() {
        return inner;
    }

    public void setInner(LinkedList<InnerAtModel> inner) {
        this.inner = inner;
    }

    public HashMap<Integer, UserBean> getUser() {
        return user;
    }

    public void setUser(HashMap<Integer, UserBean> user) {
        this.user = user;
    }

    public static class InnerAtModel {
        private int id;
        private int wid;
        private int hid;
        private int oid;
        private int time;
        public InnerAtModel(int id, int wid, int hid, int oid,int time) {
            this.id = id;
            this.wid = wid;
            this.hid = hid;
            this.oid = oid;
            this.time=time;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
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

    public static class  UserBean{
        private String name;
        private String face;

        public UserBean(String name, String face) {
            this.name = name;
            this.face = face;
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

        @Override
        public String toString() {
            return "UserBean{" +
                    "name='" + name + '\'' +
                    ", face='" + face + '\'' +
                    '}';
        }
    }

}
