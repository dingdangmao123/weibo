package com.gapcoder.weico.Index.FG;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gapcoder.weico.Index.Adapter.TitleAdapter;
import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.Index.Service.TitleService;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;
import java.util.List;

public class TitleFG extends BaseFG {
    List<TitleModel.inner> data=new LinkedList<>();
    List<TitleModel.inner> tmp=new LinkedList<>();
    TitleAdapter adapter;


    public TitleFG() {

    }
    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        Log.i("tag","init");
        return   inflater.inflate(R.layout.fragment_title_fg, container, false);
    }
    @Override
    public void  CreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState,View v) {
        adapter=new TitleAdapter(data,getActivity());
        RecyclerView tl=(RecyclerView)v.findViewById(R.id.timeline);
        tl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        RefreshLayout refreshLayout = (RefreshLayout)v.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Refresh();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                //Refresh();
            }
        });
        Refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void Refresh(){

        Pool.run(new Runnable() {
            @Override
            public void run() {

               final  TitleModel t=TitleService.getTitleList(1);
                Log.i("tag",t.getMsg());
                for(int i=0;i<t.getInner().size();i++)
                    Log.i("tag",t.getInner().get(i).getTitle());

              if(!t.getCode().equals("OK")){
                    UI(new Runnable() {
                        @Override
                        public void run() {
                            T.show(getActivity(),t.getMsg());
                        }
                    });
                    return ;
                }

                tmp=t.getInner();
                if(tmp.size()==0)
                    return ;
                else
                    data.clear();
                for(int i=0;i<tmp.size();i++){
                    data.add(tmp.get(i));
                }
                UI(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}
