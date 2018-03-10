package com.gapcoder.weico.Title;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Index.Adapter.WeicoAdapter;
import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

import butterknife.BindView;

public class Title extends Base {

    LinkedList<WeicoModel.InnerBean> data = new LinkedList<>();
    LinkedList<WeicoModel.InnerBean> tmp = new LinkedList<>();
    WeicoAdapter adapter;
    TitleModel.inner title;
    int cache = 10;
    int id = 0;
    @BindView(R.id.timeline)
    RecyclerView tl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout rf;


    public void setContentView() {
        setContentView(R.layout.activity_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void init() {


        title = new TitleModel.inner(getIntent().getIntExtra("id", 0), getIntent().getStringExtra("title"));
        getSupportActionBar().setTitle(title.getTitle());

        adapter = new WeicoAdapter(data, this);

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
        rf.autoRefresh();
        Refresh(1);
    }

    public void Refresh(final int flag) {

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

                String url = "weicotitle.php?tid=" + String.valueOf(title.getId()) + "&flag=" + String.valueOf(flag) + "&id=" + String.valueOf(id);
                SysMsg m = URLService.get(url, WeicoModel.class);
                Log.i("tag", url);
                if (!check(m, rf)) {
                    return;
                }
                tmp = ((WeicoModel) m).getInner();

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
                        adapter.notifyDataSetChanged();
                        SmartRefresh(rf);
                    }
                });
            }
        });

    }

}