package com.gapcoder.weico;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.widget.EditText;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Token;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class Post extends Base {

    @BindView(R.id.text)
    EditText text;

    List<String> url;
    final int IMAGE = 0;

    RxPermissions rxPermissions;

    @OnClick(R.id.select)
    void selectCheck() {
        if (ContextCompat.checkSelfPermission(Post.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Post.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            select();
        }
    }

    void select() {
        MultiImageSelector.create()
                .start(this, IMAGE);
    }


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
                map.put("text", "" + text.getText().toString());
                //final SysMsg r = URLService.post("post.php", map, SysMsg.class);
                final SysMsg r = URLService.upload("upload.php", map, url, SysMsg.class);
                if (!check(r, null)) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == IMAGE) {
            if (resultCode == RESULT_OK) {
                // Get the result list of select image paths
                url = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // do your logic ....
                for (int i = 0; i < url.size(); i++)
                    T.show(Post.this, url.get(i));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case IMAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    select();
                else
                    T.show(Post.this, "你没有允许权限");
                break;
        }
    }
}
