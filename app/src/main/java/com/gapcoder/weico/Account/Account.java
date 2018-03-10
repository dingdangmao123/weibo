package com.gapcoder.weico.Account;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.ImageView;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.UserModel;
import com.gapcoder.weico.General.UserService;
import com.gapcoder.weico.Index.Adapter.WeicoAdapter;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.LinkedList;

import butterknife.BindView;

public class Account extends Base {

    LinkedList<WeicoModel> data=new LinkedList<WeicoModel>();
    LinkedList<WeicoModel> tmp=new LinkedList<WeicoModel>();
    WeicoAdapter adapter;
    UserModel m;

    int cache=10;
    int id=0;

    @BindView(R.id.bg)
    ImageView bg;

    @BindView(R.id.timeline)
    RecyclerView timeline;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private int uid = 0;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_account);
    }


    @Override
    public void init() {
        if ((uid = getIntent().getIntExtra("uid", 0)) == 0)
            return ;

/*
        adapter=new WeicoAdapter(data,this);
        RecyclerView tl=(RecyclerView)findViewById(R.id.timeline);
        tl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        tl.setNestedScrollingEnabled(false);
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000*//*,false*//*);//传入false表示刷新失败
                Refresh(1);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000*//*,false*//*);//传入false表示加载失败
                Refresh(0);
            }
        });

        getUser();
        Refresh(1);*/

    }
    void getUser(){
        Pool.run(new Runnable() {
            @Override
            public void run() {
                final UserModel m= UserService.getUser(uid);
                mh.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                final Bitmap f=Curl.getImage(m.getFace());
                final Bitmap b=Curl.getImage(m.getBg());
                mh.post(new Runnable() {
                    @Override
                    public void run() {
                        bg.setImageBitmap(b);
                    }
                });

            }
        });
    }
    public void Refresh(final int flag){
/*
        if(flag==1){
            if(data.size()!=0){
                id=data.get(0).getId();
            }
        }else{
            id=data.get(data.size()-1).getId();
        }*/


        Pool.run(new Runnable() {
            @Override
            public void run() {


                tmp= AccountService.getCareList(uid,id,flag);

                if(tmp.size()==0)
                    return ;

                if(flag==1) {
                    for (int i = 0; i < tmp.size(); i++)
                        data.addFirst(tmp.get(tmp.size()-i-1));
                    int n = data.size() - cache;
                    for (int i = 0; i < n; i++) {
                        data.removeLast();
                    }
                }else{
                    data.addAll(tmp);
                    int n=data.size()-cache;
                    for(int i=0;i<n;i++){
                        data.removeFirst();
                    }
                }

                mh.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(getActivity(),String.valueOf(data.size()),Toast.LENGTH_SHORT).show();
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
