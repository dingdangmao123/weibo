package com.gapcoder.weico.Message.FG;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gapcoder.weico.Message.Adapter.AtAdapter;
import com.gapcoder.weico.Message.Model.AtModel;
import com.gapcoder.weico.Message.Service.AtService;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class AtFG extends Fragment {

    AtModel data=new AtModel();
    AtAdapter adapter;
    Handler mh=new Handler();
    int cache = 10;
    int id = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_at_fg, container, false);
        adapter=new AtAdapter(data,getActivity());
        RecyclerView tl=(RecyclerView)v.findViewById(R.id.timeline);
        tl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        RefreshLayout refreshLayout = (RefreshLayout)v.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Refresh(1);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                Refresh(0);
            }
        });
        Refresh(1);
        return v;
    }



    void Refresh(final int flag) {

        final LinkedList<AtModel.InnerAtModel> list = data.getInner();
        if (flag == 1) {
            if (list.size() != 0) {
                id = list.get(0).getId();
            }
        } else {
            id = data.getInner().get(list.size() - 1).getId();
        }

        Pool.run(new Runnable() {
            @Override
            public void run() {

                AtModel tmp = AtService.getList(id, flag);
                if (tmp == null)
                    return;
                LinkedList<AtModel.InnerAtModel> c = tmp.getInner();
                data.getUser().putAll(tmp.getUser());

                if (tmp.getInner().size() == 0)
                    return;

                if (flag == 1) {
                    for (int i = 0; i < c.size(); i++)
                        list.addFirst(c.get(c.size() - i - 1));
                    int n = list.size() - cache;
                    for (int i = 0; i < n; i++) {
                        list.removeLast();
                    }
                } else {
                    list.addAll(c);
                    int n = list.size() - cache;
                    for (int i = 0; i < n; i++) {
                        list.removeFirst();
                    }
                }

                for (int i = 0; i < data.getInner().size(); i++)
                    Log.i("tag", data.getInner().get(i).toString());
                Log.i("tag", data.getUser().toString());

                mh.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
