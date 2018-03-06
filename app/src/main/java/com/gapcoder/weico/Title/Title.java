package com.gapcoder.weico.Title;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.Index.Adapter.WeicoAdapter;
import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.Index.Service.TitleService;
import com.gapcoder.weico.Post;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

public class Title extends Base {

    LinkedList<WeicoModel> data=new LinkedList<WeicoModel>();
    LinkedList<WeicoModel> tmp=new LinkedList<WeicoModel>();
    WeicoAdapter adapter;
     TitleModel title;
    Handler mh=new Handler();
    int cache=10;
    int id=0;


    public void setContentView(){
        setContentView(R.layout.activity_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void init(){


        title=new TitleModel(getIntent().getIntExtra("id",0),getIntent().getStringExtra("title"));
        getSupportActionBar().setTitle(title.getTitle());

        adapter=new WeicoAdapter(data,this);
        RecyclerView tl=(RecyclerView)findViewById(R.id.timeline);
        tl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
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
    public void Refresh(final int flag){

        if(flag==1){
            if(data.size()!=0){
                id=data.get(0).getId();
            }
        }else{
            id=data.get(data.size()-1).getId();
        }


        Pool.run(new Runnable() {
            @Override
            public void run() {

                Log.i("tag",String.valueOf(title.getId()));
               tmp= WeicoTitleService.getList(id,flag,title.getId(),title.getTitle());

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

}