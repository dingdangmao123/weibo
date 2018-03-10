package com.gapcoder.weico.Message.Model;

import com.gapcoder.weico.General.SysMsg;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/7.
 */


public class AtModel extends SysMsg{
    /**
     * code : OK
     * msg :
     * inner : {"inner":[{"id":"3","wid":"18","hid":"3","oid":"1","time":"0"},{"id":"2","wid":"29","hid":"4","oid":"1","time":"0"}],"user":{"3":{"name":"张无忌","face":"http://10.0.2.2/weico/face/f3.jpg"},"4":{"name":"Apple","face":"http://10.0.2.2/weico/face/f4.jpg"}}}
     */


    private InnerBeanX inner;


    public InnerBeanX getInner() {
        return inner;
    }

    public void setInner(InnerBeanX inner) {
        this.inner = inner;
    }

    public static class InnerBeanX {
        /**
         * inner : [{"id":"3","wid":"18","hid":"3","oid":"1","time":"0"},{"id":"2","wid":"29","hid":"4","oid":"1","time":"0"}]
         * user : {"3":{"name":"张无忌","face":"http://10.0.2.2/weico/face/f3.jpg"},"4":{"name":"Apple","face":"http://10.0.2.2/weico/face/f4.jpg"}}
         */

        private HashMap<Integer,InnerUserBean> user;

        private LinkedList<InnerBean> inner;

        public InnerBeanX() {
            inner=new LinkedList<>();
            user=new HashMap<>();
        }

        public HashMap<Integer, InnerUserBean> getUser() {
            return user;
        }

        public void setUser(HashMap<Integer, InnerUserBean> user) {
            this.user = user;
        }

        public LinkedList<InnerBean> getInner() {
            return inner;
        }

        public void setInner(LinkedList<InnerBean> inner) {
            this.inner = inner;
        }

        public static class UserBean {
            /**
             * 3 : {"name":"张无忌","face":"http://10.0.2.2/weico/face/f3.jpg"}
             * 4 : {"name":"Apple","face":"http://10.0.2.2/weico/face/f4.jpg"}
             */

            private HashMap<Integer,InnerUserBean> user;

            public HashMap<Integer, InnerUserBean> getUser() {
                return user;
            }

            public void setUser(HashMap<Integer, InnerUserBean> user) {
                this.user = user;
            }
        }

         public static class InnerUserBean{

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

        public static class InnerBean {
            /**
             * id : 3
             * wid : 18
             * hid : 3
             * oid : 1
             * time : 0
             */

            private int id;
            private int wid;
            private int hid;
            private int oid;
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

            public int getOid() {
                return oid;
            }

            public void setOid(int oid) {
                this.oid = oid;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }
        }
    }













   /* private LinkedList<InnerAtModel>  inner;
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
    }*/

}
