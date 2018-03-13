package com.gapcoder.weico.Account;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.General.UserModel;
import com.gapcoder.weico.Index.Adapter.WeicoAdapter;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

import butterknife.BindView;

public class Account extends Base {

    LinkedList<WeicoModel.InnerBean> data = new LinkedList<>();
    LinkedList<WeicoModel.InnerBean> tmp = new LinkedList<>();
    WeicoAdapter adapter;
    UserModel.InnerBean m;

    int cache = 10;
    int id = 0;


    @BindView(R.id.bg)
    ImageView bg;


    @BindView(R.id.timeline)
    RecyclerView tl;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout rf;


    private int uid = 0;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_account);
    }


    @Override
    public void init() {
        if ((uid = getIntent().getIntExtra("uid", 0)) == 0)
            return;


        adapter = new WeicoAdapter(data, this);

        tl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        tl.setNestedScrollingEnabled(false);
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

        getUser();
        rf.autoRefresh();

    }

    void getUser() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                String url = "account.php?uid=" + "" + uid;
                final SysMsg t = URLService.get(url, UserModel.class);
                if (!check(t, rf)) {
                    return;
                }
                m = ((UserModel) t).getInner();
                final Bitmap f = Curl.getImage(m.getFace());
                final Bitmap b = Curl.getImage(m.getBg());
                UI(new Runnable() {
                    @Override
                    public void run() {
                        bg.setImageBitmap(b);
                    }
                });

            }
        });
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

                String url = "myweico.php?uid=" + uid + "&flag=" + String.valueOf(flag) + "&id=" + String.valueOf(id);
                Log.i("tag", url);
                SysMsg t = URLService.get(url, WeicoModel.class);
                if (!check(t, rf)) {
                    return;
                }

                tmp = ((WeicoModel) t).getInner();
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
