package com.gapcoder.weico.Message.FG;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.T;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by suxiaohui on 2018/3/10.
 */

public class BaseFG extends Fragment {

    Unbinder binder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=init(inflater,container,savedInstanceState);
        binder = ButterKnife.bind(this,v);
        CreateView(inflater,container,savedInstanceState,v);
        return v;
    }
    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return null;
    }

    void CreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState,View v){

    }

    boolean check(final SysMsg m, final SmartRefreshLayout rf){
        if (!m.getCode().equals(Config.SUCCESS)) {
            UI(new Runnable() {
                @Override
                public void run() {
                   SmartRefresh(rf);
                    T.show(getActivity(), m.getMsg());
                }
            });
            return false;
        }
        return true;
    }

    void UI(Runnable r){
        getActivity().runOnUiThread(r);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }

    public void SmartRefresh(SmartRefreshLayout rf){
        if(rf!=null) {
            if(rf.isRefreshing())
                rf.finishRefresh(true);
            if(rf.isLoading())
                rf.finishLoadmore(true);
        }
    }
}
