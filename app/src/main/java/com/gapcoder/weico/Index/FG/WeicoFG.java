package com.gapcoder.weico.Index.FG;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.General.MessageModel;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Index.Adapter.WeicoAdapter;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.Message.Message;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.Token;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.Unbinder;
import q.rorbin.badgeview.QBadgeView;


public class WeicoFG extends BaseFG {

    LinkedList<WeicoModel.InnerBean> data = new LinkedList<>();
    LinkedList<WeicoModel.InnerBean> tmp = new LinkedList<>();
    WeicoAdapter adapter;
    QBadgeView msg;

    String type = "new";
    boolean reset = false;
    int current = 1;
    int cache = 10;
    int id = 0;

    @BindView(R.id.timeline)
    RecyclerView tl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout rf;
    Unbinder unbinder;
    @BindView(R.id.msg)
    TextView target;
    @BindView(R.id.weicoTitle)
    TextView title;

    public WeicoFG() {

    }

    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weico_fg, container, false);
    }

    @Override
    public void CreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState, View v) {

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.hide(false);
                Intent i = new Intent(getActivity(), Message.class);
                startActivity(i);

            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EasyPopup mCirclePop;
                mCirclePop = new EasyPopup(getActivity())
                        .setContentView(R.layout.popmenu)
                        .setFocusAndOutsideEnable(true)
                        .createPopup();
                TextView t1 = mCirclePop.getView(R.id.t1);
                TextView t2 = mCirclePop.getView(R.id.t2);
                TextView t3 = mCirclePop.getView(R.id.t3);
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCirclePop.dismiss();
                        type = "new";
                        if (current != 1) {
                            reset = true;
                            current = 1;
                        } else {
                            reset = false;
                        }
                        title.setText("最新微博");
                        Refresh(1);
                    }
                });
                t2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCirclePop.dismiss();
                        type = "hot";
                        if (current != 2) {
                            reset = true;
                            current = 2;
                        } else {
                            reset = false;
                        }
                        title.setText("热门微博");
                        Refresh(1);
                    }
                });
                t3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCirclePop.dismiss();
                        type = "care";
                        if (current != 3) {
                            reset = true;
                            current = 3;
                        } else {
                            reset = false;
                        }
                        title.setText("我关注的");
                        Refresh(1);
                    }
                });
                mCirclePop.showAtAnchorView(v, VerticalGravity.BELOW, HorizontalGravity.ALIGN_LEFT, 0, 0);
            }
        });
        adapter = new WeicoAdapter(data, getActivity());
        tl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        rf.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Refresh(1);
                getMessage();
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
        getMessage();
    }

    void getMessage() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                MessageModel m = (MessageModel) Curl.getText(Config.url + "message.php?token=" + Token.token, MessageModel.class);
                if (m == null)
                    return;
                final int num = m.getInner().getTotal();
                if (msg == null) {
                    UI(new Runnable() {
                        @Override
                        public void run() {
                            msg = new QBadgeView(getActivity());
                            msg.bindTarget(target).setBadgeGravity(Gravity.CENTER | Gravity.END);
                        }
                    });
                }
                if (num <= 0) {
                    UI(new Runnable() {
                        @Override
                        public void run() {
                            msg.hide(false);
                        }
                    });
                } else {
                    UI(new Runnable() {
                        @Override
                        public void run() {
                            msg.setBadgeNumber(num);
                        }
                    });
                }
            }
        });
    }

    void Refresh(final int flag) {
        if (flag == 1) {
            if (reset == true) {
                id = 0;
            } else if (data.size() != 0)
                id = data.get(0).getId();
            else
                id = 0;
        } else {
            if (data.size() != 0)
                id = data.get(data.size() - 1).getId();
            else
                id = 0;
        }

        Pool.run(new Runnable() {
            @Override
            public void run() {
                String url =type + ".php?flag=" + String.valueOf(flag) + "&id=" + String.valueOf(id);
                SysMsg m = URLService.get(url, WeicoModel.class);
                Log.i("tag", url);
                if (!check(m, rf)) {
                    return;
                }
                tmp = ((WeicoModel) m).getInner();
                if (reset) {
                    data.clear();
                }

                if (flag == 1) {
                    for (int i = 0; i < tmp.size(); i++)
                        data.addFirst(tmp.get(tmp.size() - i - 1));
                    int n = data.size() - cache;
                    for (int i = 0; i < n; i++) {
                        data.removeLast();
                    }
                } else if(tmp.size()>0){

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
                        reset = false;
                    }
                });
                tmp.clear();
            }
        });
    }
}
