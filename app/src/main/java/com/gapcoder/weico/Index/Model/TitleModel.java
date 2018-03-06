package com.gapcoder.weico.Index.Model;

/**
 * Created by suxiaohui on 2018/3/3.
 */

public class TitleModel {

    private int id;
    private String title;

    public TitleModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
