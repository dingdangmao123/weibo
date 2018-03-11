package com.gapcoder.weico.Index.FG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gapcoder.weico.Account.Account;
import com.gapcoder.weico.Change.Change;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.General.UserModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.UserList.UserList;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.Token;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;


public class AccountFG extends BaseFG {

    UserModel.InnerBean user;

    final int PLACE=0;
    final int SIGN=1;

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
    Unbinder unbinder;

    boolean flag=false;

    @OnClick(R.id.placeitem)
    void place(){

            Intent i=new Intent(getActivity(),Change.class);
            i.putExtra("key","place");
            i.putExtra("title","地区");
            i.putExtra("text",place.getText().toString());
            startActivityForResult(i,PLACE);
    }


    @OnClick(R.id.signitem)
    void sign(){
        Intent i=new Intent(getActivity(),Change.class);
        i.putExtra("key","sign");
        i.putExtra("title","签名");
        i.putExtra("text",sign.getText().toString());
        startActivityForResult(i,SIGN);
    }

    @OnClick(R.id.weicoitem)
    void jump() {
        Intent i = new Intent(getActivity(), Account.class);
        i.putExtra("uid", user.getId());
        startActivity(i);
    }

    @OnClick(R.id.fansitem)
    void fans() {
        Intent i = new Intent(getActivity(), UserList.class);
        i.putExtra("uid", user.getId());
        i.putExtra("type","fans");
        startActivity(i);
    }

    @OnClick(R.id.careitem)
    void  care() {
        Intent i = new Intent(getActivity(), UserList.class);
        i.putExtra("uid", user.getId());
        i.putExtra("type","care");
        startActivity(i);
    }

    public AccountFG() {

    }

    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_fg, container, false);
    }

    @Override
    public void CreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState, View v) {

    }
    @Override
    public void onResume() {
        super.onResume();
        if(!flag)
        {
            flag=true;
            Refresh();
        }
    }


    void Refresh() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                String url="account.php?token="+ Token.token;
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
                    }
                });
                final Bitmap bit = Curl.getImage(user.getFace());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String res=data.getStringExtra("text");
        switch(requestCode){
            case PLACE:
                if(resultCode==RESULT_OK){
                    place.setText(res);
                    user.setPlace(res);
                }
                break;
            case SIGN:
                if(resultCode==RESULT_OK){
                    sign.setText(res);
                    user.setSign(res);
                }
                break;

        }

    }
}
