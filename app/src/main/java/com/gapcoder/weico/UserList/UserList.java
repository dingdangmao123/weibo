package com.gapcoder.weico.UserList;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.TextView;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.Token;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

import butterknife.BindView;

public class UserList extends Base {

    LinkedList<UserListModel.InnerBean> data = new LinkedList<>();
    LinkedList<UserListModel.InnerBean> tmp = new LinkedList<>();
    UserListAdapter adapter;

    int cache = 10;
    int id = 0;
    int uid = 0;

    String type = "";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.timeline)
    RecyclerView tl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout rf;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_user_list);
    }


    @Override
    public void init() {
        if ((uid = getIntent().getIntExtra("uid", 0)) == 0)
            return;

        type = getIntent().getStringExtra("type");
        if (type.equals("fans"))
            title.setText("粉丝");
        else
            title.setText("关注");

        adapter = new UserListAdapter(data, this);
        tl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);

        rf.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Refresh(1);
            }
        });
        rf.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Refresh(0);
            }
        });
        Refresh(1);
        rf.autoRefresh();

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

                String key = "";
                if (type.equals("fans"))
                    key = "myfans";
                else
                    key = "mycare";

                String url = key + ".php?uid=" +""+uid+ "&flag=" + String.valueOf(flag) + "&id=" + String.valueOf(id);
                final SysMsg m = URLService.get(url, UserListModel.class);
                if (!check(m, rf)) {
                    return;
                }
                tmp = ((UserListModel) m).getInner();

                if (flag == 1) {
                    for (int i = 0; i < tmp.size(); i++)
                        data.addFirst(tmp.get(tmp.size() - i - 1));
                    int n = data.size() - cache;
                    for (int i = 0; i < n; i++) {
                        data.removeLast();
                    }
                } else if (tmp.size() > 0) {
                    data.addAll(tmp);
                    int n = data.size() - cache;
                    for (int i = 0; i < n; i++) {
                        data.removeFirst();
                    }
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
