package com.gapcoder.weico.Message;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.MessageModel;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Index.FG.AccountFG;
import com.gapcoder.weico.Index.FG.TitleFG;
import com.gapcoder.weico.Index.FG.WeicoFG;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.Message.FG.AtFG;
import com.gapcoder.weico.Message.FG.CommFG;
import com.gapcoder.weico.Message.FG.FollowFG;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.Token;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

public class Message extends Base {

    FragmentManager fm=getSupportFragmentManager();
    HashMap<Integer,Fragment> map=new HashMap<>();
    HashSet<Integer> flag=new HashSet<>();

    @BindView(R.id.title)
    TextView title;

    EasyPopup mCirclePop;
    QBadgeView[] msg = new QBadgeView[4];
    TextView[] tv = new TextView[4];
    MessageModel.InnerBean m;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_message);
    }

    @Override
    public void init() {

        tv[0] = title;
        FragmentTransaction tran = fm.beginTransaction();
        Fragment fg= new FollowFG();
        map.put(1,fg);
        map.put(2,new AtFG());
        map.put(3,new CommFG());
        tran.add(R.id.container, fg);
        flag.add(1);
        tran.commit();

        popMenu();
        getMessage();

    }
    private void hideFragments(FragmentTransaction transaction) {
        if(map.size()==0)
            return ;
        Iterator<Fragment> it=map.values().iterator();
        while(it.hasNext()){
            transaction.hide(it.next());
        }
    }

    void popMenu() {
        mCirclePop = new EasyPopup(this)
                .setContentView(R.layout.messagemenu)
                .setFocusAndOutsideEnable(true)
                .createPopup();
        tv[1] = mCirclePop.getView(R.id.t1);
        tv[2] = mCirclePop.getView(R.id.t2);
        tv[3] = mCirclePop.getView(R.id.t3);
        tv[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCirclePop.dismiss();
                title.setText("新粉丝");
                msg[1].hide(false);
                msg[0].hide(false);
                change(1);
            }
        });
        tv[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCirclePop.dismiss();
                title.setText("@我的");
                msg[2].hide(false);
                msg[0].hide(false);
                change(2);
            }
        });
        tv[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCirclePop.dismiss();
                title.setText("评论回复");
                msg[3].hide(false);
                msg[0].hide(false);
                change(3);
            }
        });
    }
    void change(int n){
        FragmentTransaction tran = fm.beginTransaction();
        hideFragments(tran);
        if(!flag.contains(n)) {
            tran.add(R.id.container, map.get(n));
            flag.add(n);
        }
        tran.show(map.get(n));
        tran.commit();
    }
    void getMessage() {
        Pool.run(new Runnable() {
            @Override
            public void run() {

                String url ="message.php?token=" + Token.token;
                SysMsg t = URLService.get(url,  MessageModel.class);
                Log.i("tag", url);
                if (!check(t)) {
                    return;
                }
                m=((MessageModel)t).getInner();
                UI(new Runnable() {
                    @Override
                    public void run() {
                        updateBadge();
                    }
                });
            }
        });
    }

    void updateBadge() {

        badge(0, m.getTotal());
        badge(1, m.getFollow());
        badge(2, m.getAt());
        badge(3, m.getComment());
    }

    void badge(int i, int n) {
        if (msg[i] == null) {
            msg[i] = new QBadgeView(this);
            msg[i].bindTarget(tv[i]).setBadgeGravity(Gravity.CENTER | Gravity.END);
        }
        if (n <= 0) {
            msg[i].hide(false);
        } else {
            msg[i].setBadgeNumber(n);
        }
    }

    @OnClick(R.id.title)
    void action(View v) {
        mCirclePop.showAtAnchorView(v, VerticalGravity.BELOW, HorizontalGravity.ALIGN_LEFT, 0, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}