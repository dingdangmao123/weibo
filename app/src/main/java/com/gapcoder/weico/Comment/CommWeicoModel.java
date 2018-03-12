package com.gapcoder.weico.Comment;

import com.gapcoder.weico.General.SysMsg;

/**
 * Created by suxiaohui on 2018/3/11.
 */

public class CommWeicoModel extends SysMsg{


    /**
     * code : OK
     * msg :
     * inner : {"id":"27","uid":"1","name":"gapcoder","face":"http://10.0.2.2/weico/face/f1.jpg","text":"And take it real slow http://www.baidu.com并且会慢慢的来#Apple#\r\nThis is how you do it. Everybody move it\r\n这就是你怎么做的，大家跟你动","time":"1520054559","comment":"4","love":"0"}
     */
    private InnerBean inner;

    public InnerBean getInner() {
        return inner;
    }

    public void setInner(InnerBean inner) {
        this.inner = inner;
    }

    public static class InnerBean {

        private int id;
        private int uid;
        private String name;
        private String face;
        private String text;
        private int time;
        private int comment;
        private int love;
        private String photo;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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
    }
}
