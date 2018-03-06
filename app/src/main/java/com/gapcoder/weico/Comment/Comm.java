package com.gapcoder.weico.Comment;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/6.
 */

public class Comm {

    private LinkedList<CommentModel> comment;
    private HashMap<Integer,UserBean> user;




    @Override
    public String toString() {

        String s="";
        for(int i=0;i<comment.size();i++)
            s=s+comment.get(i);
        Iterator<Integer> it=user.keySet().iterator();
        while(it.hasNext()){
            UserBean b=user.get(it.next());
            s=s+"["+it.next()+":"+b+"]";
        }
        return s;
    }

    public Comm(LinkedList<CommentModel> comment, HashMap<Integer, UserBean> user) {
        this.comment = comment;
        this.user = user;
    }

    public Comm() {
        this.comment = new LinkedList<CommentModel>();
        this.user = new HashMap<Integer,UserBean>();
    }


    public LinkedList<CommentModel> getComment() {
        return comment;
    }

    public void setComment(LinkedList<CommentModel> comment) {
        this.comment = comment;
    }

    public HashMap<Integer, UserBean> getUser() {
        return user;
    }

    public void setUser(HashMap<Integer, UserBean> user) {
        this.user = user;
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
