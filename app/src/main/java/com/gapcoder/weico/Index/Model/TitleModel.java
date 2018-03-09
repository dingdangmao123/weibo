package com.gapcoder.weico.Index.Model;

import com.gapcoder.weico.General.SysMsg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/3.
 */

public class TitleModel extends SysMsg {


    private List<inner> inner;


    public List<inner> getInner() {
        return inner;
    }


    public void setInner(List<inner> title) {
        this.inner = title;
    }

    public static class inner {

        private int id;

        private String title;

        public inner() {

        }

        public inner(int id, String title) {
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

        @Override
        public String toString() {
            return "TitleModel{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
