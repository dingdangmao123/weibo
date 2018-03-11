package com.gapcoder.weico.Comment;

import com.gapcoder.weico.General.SysMsg;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/6.
 */

public class Comm extends SysMsg{


    /**
     * code : OK
     * msg :
     * inner : {"comment":[{"id":"8","wid":"27","hid":"1","uid":"1","oid":"0","text":"baby you are a rich man ","time":"1520347290"},{"id":"7","wid":"27","hid":"1","uid":"1","oid":"3","text":"this is how we do it ","time":"1520344893"},{"id":"6","wid":"27","hid":"1","uid":"1","oid":"0","text":"hello world ","time":"1520344871"},{"id":"5","wid":"27","hid":"1","uid":"1","oid":"0","text":"hello world ","time":"1520344848"}],"user":{"1":{"name":"gapcoder","face":"http://10.0.2.2/weico/face/f1.jpg"},"3":{"name":"张无忌","face":"http://10.0.2.2/weico/face/f3.jpg"}}}
     */

    private InnerBean inner;


    public InnerBean getInner() {
        return inner;
    }

    public void setInner(InnerBean inner) {
        this.inner = inner;
    }

    public static class InnerBean {
        /**
         * comment : [{"id":"8","wid":"27","hid":"1","uid":"1","oid":"0","text":"baby you are a rich man ","time":"1520347290"},{"id":"7","wid":"27","hid":"1","uid":"1","oid":"3","text":"this is how we do it ","time":"1520344893"},{"id":"6","wid":"27","hid":"1","uid":"1","oid":"0","text":"hello world ","time":"1520344871"},{"id":"5","wid":"27","hid":"1","uid":"1","oid":"0","text":"hello world ","time":"1520344848"}]
         * user : {"1":{"name":"gapcoder","face":"http://10.0.2.2/weico/face/f1.jpg"},"3":{"name":"张无忌","face":"http://10.0.2.2/weico/face/f3.jpg"}}
         */

        private HashMap<Integer,UserBean> user;
        private LinkedList<CommentBean> comment;

        public InnerBean() {
            comment=new LinkedList<>();
            user=new HashMap<>();
        }

        public HashMap<Integer, UserBean> getUser() {
            return user;
        }

        public void setUser(HashMap<Integer, UserBean> user) {
            this.user = user;
        }

        public LinkedList<CommentBean> getComment() {
            return comment;
        }

        public void setComment(LinkedList<CommentBean> comment) {
            this.comment = comment;
        }

        public static class UserBean {
            /**
             * 1 : {"name":"gapcoder","face":"http://10.0.2.2/weico/face/f1.jpg"}
             * 3 : {"name":"张无忌","face":"http://10.0.2.2/weico/face/f3.jpg"}
             */
            private String name;
            private String face;

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

        public static class CommentBean {
            /**
             * id : 8
             * wid : 27
             * hid : 1
             * uid : 1
             * oid : 0
             * text : baby you are a rich man
             * time : 1520347290
             */

            private int id;
            private int wid;
            private int hid;
            private int uid;
            private int oid;
            private String text;
            private int time;

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
        }
    }
}
