package com.gapcoder.weico.User;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gapcoder.weico.Account.Account;
import com.gapcoder.weico.Config;
import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.General.UserModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.UserList.UserList;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Token;

import butterknife.BindView;
import butterknife.OnClick;

public class User extends Base {


    UserModel.InnerBean user;
    int uid = 0;
    String n="";

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

    @OnClick(R.id.add)
    void  add() {
        if(user.getFlag()==0)
            return ;

        Pool.run(new Runnable() {
            @Override
            public void run() {
                final SysMsg m=URLService.get("follow.php?token="+Token.token+"&flag="+user.getFlag()+"&id="+user.getId(),SysMsg.class);

                UI(new Runnable() {
                    @Override
                    public void run() {

                        if(m.getCode().equals(Config.SUCCESS)) {
                            if(user.getFlag()==2) {
                                Log.i("tag","222");
                                add.setText("添加关注");
                                user.setFlag(1);
                            }else {
                                Log.i("tag","111");
                                add.setText("取消关注");
                                user.setFlag(2);
                            }
                        }
                        T.show(User.this,m.getMsg());
                    }
                });
            }
        });

    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_user);
    }

    @Override
    public void init() {
            uid = getIntent().getIntExtra("uid",0);
            n=getIntent().getStringExtra("name");
            if(uid>0||n.length()>0)
                Refresh();
    }

    void Refresh() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                String url="user.php?token="+ Token.token+"&id="+uid+"&name="+n;
                final SysMsg m = URLService.get(url, UserModel.class);
                Log.i("tag",url);
                user= ((UserModel) m).getInner();
                UI(new Runnable() {
                    @Override
                    public void run() {
                        name.setText(user.getName());
                        fans.setText(String.valueOf(user.getFans()));
                        care.setText(String.valueOf(user.getCare()));
                        sign.setText(user.getSign());
                        place.setText(user.getPlace());
                        switch(user.getFlag()){
                            case 0:
                                add.setText("我");
                            case 1:
                                add.setText("加为关注");
                                break;
                            case 2:
                                add.setText("取消关注");
                                break;
                        }
                    }
                });
                final Bitmap bit = Curl.getImage(Config.face+user.getFace());
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
