package com.gapcoder.weico.UserList;

import com.gapcoder.weico.General.SysMsg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/5.
 */

public class UserListModel extends SysMsg{

    /**
     * code : OK
     * msg :
     * inner : [{"id":"3","name":"张无忌","face":"http://10.0.2.2/weico/face/f3.jpg"},{"id":"2","name":"奥巴马","face":"http://10.0.2.2/weico/face/f2.jpg"}]
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
         * id : 3
         * name : 张无忌
         * face : http://10.0.2.2/weico/face/f3.jpg
         */

        private int id;
        private String name;
        private String face;

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
    }
}
