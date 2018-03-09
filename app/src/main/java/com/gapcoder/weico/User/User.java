package com.gapcoder.weico.User;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gapcoder.weico.Account.Account;
import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.UserModel;
import com.gapcoder.weico.General.UserService;
import com.gapcoder.weico.R;
import com.gapcoder.weico.UserList.UserList;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class User extends Base {

    Handler mh = new Handler();

    int uid = 0;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.face)
    ImageView face;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.care)
    TextView care;

    @BindView(R.id.careitem)
    LinearLayout careitem;
    @BindView(R.id.fans)
    TextView fans;
    @BindView(R.id.fansitem)
    LinearLayout fansitem;
    @BindView(R.id.place)
    TextView place;
    @BindView(R.id.placeitem)
    LinearLayout placeitem;
    @BindView(R.id.sign)
    TextView sign;
    @BindView(R.id.signitem)
    LinearLayout signitem;
    @BindView(R.id.weicoitem)
    LinearLayout weicoitem;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.send)
    Button send;


    @OnClick(R.id.weicoitem)
    void jump() {
        Intent i = new Intent(this, Account.class);
        i.putExtra("uid", uid);
        startActivity(i);
    }
    @OnClick(R.id.fansitem)
    void fans() {
        Intent i = new Intent(this, UserList.class);
        i.putExtra("uid", uid);
        i.putExtra("type","fans");
        startActivity(i);
    }
    @OnClick(R.id.careitem)
    void  care() {
       Intent i = new Intent(this, UserList.class);
        i.putExtra("uid", uid);
        i.putExtra("type","care");
        startActivity(i);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_user);
    }

    @Override
    public void init() {
        if ((uid = getIntent().getIntExtra("uid", 0)) != 0)
            Refresh();
    }

    void Refresh() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                final UserModel m = UserService.getUser(uid);
                UI(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(m.getName());
                        fans.setText(String.valueOf(m.getFans()));
                        care.setText(String.valueOf(m.getCare()));
                        sign.setText(m.getSign());
                        place.setText(m.getPlace());
                    }
                });
                final Bitmap bit = Curl.getImage(m.getFace());
                UI(new Runnable() {
                    @Override
                    public void run() {
                        face.setImageBitmap(bit);
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
