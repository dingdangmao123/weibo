package com.gapcoder.weico;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.shuyu.textutillib.EmojiLayout;
import com.shuyu.textutillib.RichEditBuilder;
import com.shuyu.textutillib.RichEditText;
import com.shuyu.textutillib.listener.OnEditTextUtilJumpListener;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Post extends Base {

    Handler mh = new Handler();
    @BindView(R.id.text)
    EditText text;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_post);
    }


    @Override
    public void init() {

    }


    void post() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient cli = new OkHttpClient();
                    RequestBody rb = new FormBody.Builder().add("uid", "1").add("text", text.getText().toString()).build();
                    Request req = new Request.Builder().url(Config.url+"post.php").post(rb).build();
                    Response res = cli.newCall(req).execute();
                    final String ok = res.body().string();
                    mh.post(new Runnable() {
                        @Override
                        public void run() {
                            T.show(Post.this, ok);
                        }
                    });
                } catch (Exception e) {
                    // Toast.makeText(Post.this,e.toString(),Toast.LENGTH_SHORT).show();
                    T.show(Post.this, e.toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok, menu);
        return true;
    }

    @Override
    public  void onItemSelected(int id){
            if(id==R.id.TextOk)
                post();

    }

}
