package com.gapcoder.weico;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.gapcoder.weico.Comment.Comment;
import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Token;
import com.shuyu.textutillib.EmojiLayout;
import com.shuyu.textutillib.RichEditBuilder;
import com.shuyu.textutillib.RichEditText;
import com.shuyu.textutillib.listener.OnEditTextUtilJumpListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Post extends Base {

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
                HashMap<String, String> map = new HashMap<>();
                map.put("token", Token.token);
                map.put("text", "" +text.getText().toString());
                final SysMsg r = URLService.post("post.php", map, SysMsg.class);
                if (!check(r,null)) {
                    return;
                }
                UI(new Runnable() {
                    @Override
                    public void run() {
                        T.show(Post.this, r.getMsg());
                        text.setText("");
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ok, menu);
        return true;
    }
    @Override
    public void onItemSelected(int id) {
        if (id == R.id.TextOk)
            post();
    }

}
