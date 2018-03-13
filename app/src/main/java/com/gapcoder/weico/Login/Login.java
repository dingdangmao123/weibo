package com.gapcoder.weico.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Index.index;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Token;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    Handler mh=new Handler();
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.psd)
    EditText psd;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Token.initToken(this);
        if(!Token.token.equals("")){
            Intent i=new Intent(Login.this,index.class);
            Login.this.startActivity(i);
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    void login(){

       final  String key=name.getText().toString();
       final  String p=psd.getText().toString();

        Pool.run(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> map = new HashMap<>();
                map.put("key", key);
                map.put("psd", p);
                final SysMsg r = URLService.post("login.php",map,LoginModel.class);
                mh.post(new Runnable() {
                    @Override
                    public void run() {
                        if(!r.getCode().equals("OK")){
                            T.show(Login.this,r.getMsg());
                        }else{
                            LoginModel.InnerBean token=((LoginModel)r).getInner();
                            Intent i=new Intent(Login.this,index.class);
                            Token.initToken(Login.this,token.getToken());
                            Login.this.startActivity(i);
                            finish();
                        }
                    }
                });
            }
        });
    }

}
