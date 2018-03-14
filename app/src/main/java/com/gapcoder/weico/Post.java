package com.gapcoder.weico;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Utils.Compress;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class Post extends Base {

    @BindView(R.id.text)
    EditText text;

    List<String> url = new ArrayList<>();
    Grid adapter;
    final int IMAGE = 0;

    @BindView(R.id.container)
    GridView container;
    @BindView(R.id.count)
    TextView count;

    @OnClick(R.id.select)
    void selectCheck() {
        if (ContextCompat.checkSelfPermission(Post.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Post.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            select();
        }
    }

    @OnTextChanged(R.id.text)
    void input(CharSequence s, int start, int before, int c) {
        int n = 200 - s.length();
        count.setText("" + n);
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

        adapter = new Grid(url, this);
        container.setAdapter(adapter);
        container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                url.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    void post() {

        if (url.size() > 9) {
            T.show(Post.this, "你已经超过九张图片!");
            return;
        }
        final String s = text.getText().toString();
        if (s.length() > 200) {
            T.show(Post.this, "超过长度限制");
            return;
        }
        Pool.run(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> map = new HashMap<>();
                map.put("token", Token.token);
                map.put("text", s);
                final SysMsg r = URLService.upload("upload.php", map, url, SysMsg.class);
                if (!check(r, null)) {
                    return;
                }
                UI(new Runnable() {
                    @Override
                    public void run() {
                        T.show(Post.this, r.getMsg());
                        text.setText("");
                        url.clear();
                        adapter.notifyDataSetChanged();
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
        if (requestCode == IMAGE) {
            if (resultCode == RESULT_OK) {
                List<String> t = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if (t.size() == 0)
                    return;
                url.clear();
                url.addAll(t);
                adapter.notifyDataSetChanged();
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

    static class Grid extends BaseAdapter {
        private List<String> url;
        private Context context;

        public Grid(List<String> url, Context context) {
            super();
            this.url = url;
            this.context = context;
        }

        @Override
        public int getCount() {
            return url.size();
        }

        @Override
        public Object getItem(int position) {
            return url.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int p, View v, ViewGroup parent) {
            if (v == null)
                v = LayoutInflater.from(context).inflate(R.layout.griditem, null);
            ImageView iv = (ImageView) v.findViewById(R.id.iv);
            iv.setImageBitmap(Compress.decodeFile(url.get(p), 150, 150));
            return v;
        }

    }
}
