package com.gapcoder.weico.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.gapcoder.weico.Index.index;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Token;

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

                final  LoginModel m =LoginService.login(key,p);
                mh.post(new Runnable() {
                    @Override
                    public void run() {
                        if(m.getOk()==1)
                        {
                            Intent i=new Intent(Login.this,index.class);
                            Token.token=m.getToken();
                            Login.this.startActivity(i);
                        }else{
                            T.show(Login.this,m.getMsg());
                        }
                    }
                });

            }
        });
    }

}
