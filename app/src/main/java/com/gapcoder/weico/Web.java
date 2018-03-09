package com.gapcoder.weico;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.gapcoder.weico.General.Base;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Web extends Base {


    @BindView(R.id.web)
    WebView web;

    @BindView(R.id.url)
    TextView url;

    @BindView(R.id.ok)
    Button ok;

    public void init(){
        url.setText("你将跳往第三方网站: "+getIntent().getStringExtra("url")+" 继续？");
    }

    public void setContentView(){
        setContentView(R.layout.activity_web);
    }

    @OnClick(R.id.ok)
    void web(){
        web.loadUrl(getIntent().getStringExtra("url"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
