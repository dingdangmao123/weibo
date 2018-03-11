package com.gapcoder.weico.Title;

import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.Index.Model.WeicoModel;

import java.util.LinkedList;

/**
 * Created by suxiaohui on 2018/3/11.
 */

public class TitleSearchModel extends SysMsg {

    private Bean inner;

    public Bean getInner() {
        return inner;
    }

    public void setInner(Bean inner) {
        this.inner = inner;
    }

    public static class Bean{
        private int id;

        private LinkedList<com.gapcoder.weico.Index.Model.WeicoModel.InnerBean> inner;

        public LinkedList<com.gapcoder.weico.Index.Model.WeicoModel.InnerBean> getInner() {
            return inner;
        }

        public void setInner(LinkedList<WeicoModel.InnerBean> inner) {
            this.inner = inner;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}
