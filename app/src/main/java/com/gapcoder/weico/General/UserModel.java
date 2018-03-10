package com.gapcoder.weico.General;

/**
 * Created by suxiaohui on 2018/3/5.
 */

public class UserModel extends  SysMsg{

    /**
     * code : OK
     * msg :
     * inner : {"id":"1","name":"gapcoder","face":"http://10.0.2.2/weico/face/f1.jpg","sign":"无招胜有招","place":"安徽 芜湖","bg":"http://10.0.2.2/weico/photo/3.jpg","fans":"2","care":"2","flag":0}
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
         * id : 1
         * name : gapcoder
         * face : http://10.0.2.2/weico/face/f1.jpg
         * sign : 无招胜有招
         * place : 安徽 芜湖
         * bg : http://10.0.2.2/weico/photo/3.jpg
         * fans : 2
         * care : 2
         * flag : 0
         */

        private int id;
        private String name;
        private String face;
        private String sign;
        private String place;
        private String bg;
        private int fans;
        private int care;
        private int flag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getCare() {
            return care;
        }

        public void setCare(int care) {
            this.care = care;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }
    }
}
