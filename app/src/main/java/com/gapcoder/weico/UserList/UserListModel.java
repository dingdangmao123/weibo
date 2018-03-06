package com.gapcoder.weico.UserList;

/**
 * Created by suxiaohui on 2018/3/5.
 */

public class UserListModel {
    private int id;
    private String name;
    private String face;

    public UserListModel(int id, String name, String face) {
        this.id = id;
        this.name = name;
        this.face = face;
    }

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
