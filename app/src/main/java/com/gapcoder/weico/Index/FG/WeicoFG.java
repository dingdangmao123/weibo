package com.gapcoder.weico.Index.FG;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;
import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.MessageModel;
import com.gapcoder.weico.Index.Adapter.WeicoAdapter;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.Index.Service.WeicoService;
import com.gapcoder.weico.Message.Message;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.LinkedList;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;

import static android.R.attr.data;


public class WeicoFG extends BaseFG {

    LinkedList<WeicoModel> data=new LinkedList<WeicoModel>();
    LinkedList<WeicoModel> tmp=new LinkedList<WeicoModel>();
    WeicoAdapter adapter;
    Handler mh=new Handler();
    TextView title;
    QBadgeView msg;
    View target;
    String type="new";
    boolean reset=false;
    int current=1;
    int cache=10;
    int id=0;
    int uid=1;

    public WeicoFG() {

    }
    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_weico_fg, container, false);
    }

    @Override
    public void CreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState,View v) {
        target=v.findViewById(R.id.msg);
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.hide(false);
                Intent i=new Intent(getActivity(),Message.class);
                startActivity(i);

            }
        });
        title=(TextView)v.findViewById(R.id.weicoTitle);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  EasyPopup mCirclePop;
                mCirclePop = new EasyPopup(getActivity())
                        .setContentView(R.layout.popmenu)
                        .setFocusAndOutsideEnable(true)
                        .createPopup();
                TextView t1=mCirclePop.getView(R.id.t1);
                TextView t2=mCirclePop.getView(R.id.t2);
                TextView t3=mCirclePop.getView(R.id.t3);
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCirclePop.dismiss();
                        type="new";
                        if(current!=1) {
                            reset = true;
                            current=1;
                        }else{
                            reset=false;
                        }
                        title.setText("最新微博");
                        Refresh(1);
                    }
                });
                t2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCirclePop.dismiss();
                        type="hot";
                        if(current!=2) {
                            reset = true;
                            current=2;
                        }else{
                            reset=false;
                        }
                        title.setText("热门微博");
                        Refresh(1);
                    }
                });
                t3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCirclePop.dismiss();
                        type="care";
                        if(current!=3) {
                            reset = true;
                            current=3;
                        }else{
                            reset=false;
                        }
                        title.setText("我关注的");
                        Refresh(1);
                    }
                });
                mCirclePop.showAtAnchorView(v, VerticalGravity.BELOW, HorizontalGravity.ALIGN_LEFT,0, 0);
            }
        });


        adapter=new WeicoAdapter(data,getActivity());
        RecyclerView tl=(RecyclerView)v.findViewById(R.id.timeline);
        tl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        RefreshLayout refreshLayout = (RefreshLayout)v.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Refresh(1);
                getMessage();
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
        getMessage();
    }
    void getMessage(){
        Pool.run(new Runnable() {
            @Override
            public void run() {

                MessageModel m= (MessageModel)Curl.getText("http://10.0.2.2/weico/message.php?id="+uid,MessageModel.class);
                if(m==null)
                    return ;
                final int num=m.getTotal();
                if(msg==null) {
                    msg = new QBadgeView(getActivity());
                    msg.bindTarget(target).setBadgeGravity(Gravity.CENTER | Gravity.END);
                }
                if(num<=0) {
                    mh.post(new Runnable() {
                        @Override
                        public void run() {
                           msg.hide(false);
                        }
                    });
                } else{
                    mh.post(new Runnable() {
                        @Override
                        public void run() {
                            msg.setBadgeNumber(num);
                        }
                    });
                }
            }
        });
    }
    void Refresh(final int  flag){
        if(flag==1){
            if(reset==true) {
                id = 0;
            }else if(data.size()!=0)
                id=data.get(0).getId();
            else
                id=0;
        }else{
            if(data.size()!=0)
                id=data.get(data.size()-1).getId();
            else
                id=0;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                switch(type) {
                    case "new":
                        tmp = WeicoService.getNewList(id, flag);
                        break;
                    case "hot":
                        tmp = WeicoService.getHotList(id, flag);
                        break;
                    case "care":
                        tmp = WeicoService.getCareList(id, flag);
                        break;
                    default:
                        return ;
                }

                if(reset){
                    data.clear();
                }else if(tmp.size()==0){
                    return ;
                }
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
                        reset=false;
                        Toast.makeText(getActivity(),String.valueOf(tmp.size()),Toast.LENGTH_SHORT).show();
                    }
                });
                //tmp.clear();
            }
        }).start();
    }

    @Override
    public void leftSelected() {


    }

    @Override
    public void setLeftIcon(android.support.v7.app.ActionBar bar) {
       // Log.i("tag","lefticon");
       // bar.setHomeAsUpIndicator(R.mipmap.ic_mail_outline);
    }
}
