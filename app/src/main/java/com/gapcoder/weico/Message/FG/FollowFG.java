package com.gapcoder.weico.Message.FG;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.R;
import com.gapcoder.weico.UserList.UserListAdapter;
import com.gapcoder.weico.UserList.UserListModel;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.Token;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

import butterknife.BindView;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class FollowFG extends BaseFG {

    LinkedList<UserListModel.InnerBean> data = new LinkedList<>();
    LinkedList<UserListModel.InnerBean> tmp = new LinkedList<>();
    UserListAdapter adapter;
    @BindView(R.id.timeline)
    RecyclerView tl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout rf;

    boolean flag=false;

    @Override
    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_follow_fg, container, false);
    }

    @Override
    public void CreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState, View v) {
        adapter = new UserListAdapter(data, getActivity());
        tl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);

        rf.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Refresh();
            }
        });
        rf.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                rf.finishLoadmore();
            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
        if(!flag)
        {
            flag=true;
            rf.autoRefresh();
        }
    }

    void Refresh() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                String url = "newfans.php?token=" + Token.token;
                final SysMsg m = URLService.get(url, UserListModel.class);
                if (!check(m, rf)) {
                    return;
                }
                tmp = ((UserListModel) m).getInner();
                data.clear();
                data.addAll(tmp);
                UI(new Runnable() {
                    @Override
                    public void run() {
                        SmartRefresh(rf);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

}

