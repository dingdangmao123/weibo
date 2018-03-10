package com.gapcoder.weico.Message.Model;

import com.gapcoder.weico.General.SysMsg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class CommModel extends SysMsg{

    /**
     * code : OK
     * msg :
     * inner : [{"id":"8","wid":"27","hid":"1","uid":"1","oid":"0","text":"baby you are a rich man ","time":"1520347290","face":"http://10.0.2.2/weico/face/f1.jpg","name":"gapcoder"},{"id":"7","wid":"27","hid":"1","uid":"1","oid":"3","text":"this is how we do it ","time":"1520344893","face":"http://10.0.2.2/weico/face/f1.jpg","name":"gapcoder"},{"id":"6","wid":"27","hid":"1","uid":"1","oid":"0","text":"hello world ","time":"1520344871","face":"http://10.0.2.2/weico/face/f1.jpg","name":"gapcoder"},{"id":"5","wid":"27","hid":"1","uid":"1","oid":"0","text":"hello world ","time":"1520344848","face":"http://10.0.2.2/weico/face/f1.jpg","name":"gapcoder"}]
     */


    private LinkedList<InnerBean> inner;

    public LinkedList<InnerBean> getInner() {
        return inner;
    }

    public void setInner(LinkedList<InnerBean> inner) {
        this.inner = inner;
    }

    public static class InnerBean {
        /**
         * id : 8
         * wid : 27
         * hid : 1
         * uid : 1
         * oid : 0
         * text : baby you are a rich man
         * time : 1520347290
         * face : http://10.0.2.2/weico/face/f1.jpg
         * name : gapcoder
         */

        private int id;
        private int wid;
        private int hid;
        private int uid;
        private int oid;
        private String text;
        private int time;
        private String face;
        private String name;

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
    }
}
