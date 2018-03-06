package com.gapcoder.weico.UserList;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserList extends Base {

    LinkedList<UserListModel> data = new LinkedList<>();
    LinkedList<UserListModel> tmp = new LinkedList<>();
    UserListAdapter adapter;
    Handler mh = new Handler();
    int cache = 10;
    int id = 0;
    int uid = 0;

    String type = "";
    @BindView(R.id.title)
    TextView title;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_user_list);
    }


    @Override
    public void init() {
        if ((uid = getIntent().getIntExtra("uid", 0)) == 0)
            return;

        type = getIntent().getStringExtra("type");
        if(type.equals("fans"))
            title.setText("粉丝");
        else
            title.setText("关注");

        adapter = new UserListAdapter(data, this);
        RecyclerView tl = (RecyclerView) findViewById(R.id.timeline);
        tl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
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


    }

    void Refresh(final int flag) {
        if (flag == 1) {
            if (data.size() != 0) {
                id = data.get(0).getId();
            }
        } else {
            id = data.get(data.size() - 1).getId();
        }
        Pool.run(new Runnable() {
            @Override
            public void run() {

                if (type.equals("fans"))
                    tmp = UserListService.getFans(flag, id, uid);
                else
                    tmp = UserListService.getCare(flag, id, uid);

                if (flag == 1) {
                    for (int i = 0; i < tmp.size(); i++)
                        data.addFirst(tmp.get(tmp.size() - i - 1));
                    int n = data.size() - cache;
                    for (int i = 0; i < n; i++) {
                        data.removeLast();
                    }
                } else {
                    data.addAll(tmp);
                    int n = data.size() - cache;
                    for (int i = 0; i < n; i++) {
                        data.removeFirst();
                    }
                }
                mh.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        //  Toast.makeText(UserList.this,String.valueOf(data.size()),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
