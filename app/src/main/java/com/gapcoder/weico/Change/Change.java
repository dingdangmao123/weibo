package com.gapcoder.weico.Change;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Index.FG.AccountFG;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.Token;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Change extends Base {


    @BindView(R.id.titleTitle)
    TextView titleTitle;
    @BindView(R.id.text)
    EditText text;



    String tag;
    String key;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_change);
    }


    @Override
    public void init() {
        key=getIntent().getStringExtra("key");
        tag=getIntent().getStringExtra("text");
        titleTitle.setText(getIntent().getStringExtra("title"));
        text.setText(tag);
    }

    public void post() {


        final String t=text.getText().toString();
        Pool.run(new Runnable() {
            @Override
            public void run() {

                if(!tag.equals(t)) {
                    tag=text.getText().toString();
                    String url = "change.php";
                    HashMap<String, String> map = new HashMap<>();
                    map.put("token", Token.token);
                    map.put("key", key);
                    map.put("tag", tag);
                    SysMsg t = URLService.post(url, map, SysMsg.class);
                    if (!check(t, null)) {
                        return;
                    }
                }

                UI(new Runnable() {
                    @Override
                    public void run() {

                        Intent i=new Intent(Change.this, AccountFG.class);
                        i.putExtra("text",tag);
                        setResult(RESULT_OK,i);
                        finish();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                back();
                break;
            default:
                onItemSelected(item.getItemId());

        }
        return true;
    }

    public void back() {
        Log.i("tag","back");
        Intent i=new Intent(Change.this, AccountFG.class);
        i.putExtra("text",tag);
        setResult(RESULT_OK,i);
        finish();
    }
}