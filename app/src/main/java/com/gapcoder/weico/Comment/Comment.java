package com.gapcoder.weico.Comment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.weico.Config;
import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.SysMsg;
import com.gapcoder.weico.General.URLService;
import com.gapcoder.weico.Index.Adapter.GridAdapter;
import com.gapcoder.weico.R;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.LinkText;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Time;
import com.gapcoder.weico.Utils.Token;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Comment extends Base {


    @BindView(R.id.face)
    ImageView face;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.like)
    TextView like;
    @BindView(R.id.timeline)
    RecyclerView tl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout rf;

    @BindView(R.id.edit)
    EditText edit;

    @BindView(R.id.send)
    Button send;
    @BindView(R.id.likebtn)
    Button likebtn;
    @BindView(R.id.NineGrid)
    NineGridImageView NineGrid;

    private Comm.InnerBean data = new Comm.InnerBean();
    private CommentAdapter adapter;
    private LinkText parse = new LinkText(this);
    CommWeicoModel.InnerBean m;
    int wid;
    int cache = 10;
    int id = 0;

    int cid = 0;
    int cuid = 0;

    public void update(String str, int cuid, int cid) {
        edit.setHint("回复" + str);
        this.cid = cid;
        this.cuid = cuid;
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_comment);
    }

    @OnClick(R.id.likebtn)
    void like() {
        Pool.run(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> map = new HashMap<>();
                map.put("wid", "" + wid);
                map.put("token", Token.token);
                map.put("hid", "" + m.getUid());
                final SysMsg r = URLService.post("praise.php", map, SysMsg.class);
                if (!check(r, rf)) {
                    return;
                }

                UI(new Runnable() {
                    @Override
                    public void run() {
                        T.show(Comment.this, r.getMsg());
                    }
                });

            }
        });
    }

    @OnClick(R.id.send)
    void send() {

        Pool.run(new Runnable() {
            @Override
            public void run() {

                HashMap<String, String> map = new HashMap<>();
                map.put("wid", "" + wid);
                map.put("token", Token.token);
                map.put("hid", "" + m.getUid());
                map.put("cid", "" + cid);
                map.put("cuid", "" + cuid);
                map.put("text", edit.getText().toString());

                final SysMsg r = URLService.post("reply.php", map, SysMsg.class);
                if (!check(r, rf)) {
                    return;
                }

                cid = 0;
                cuid = 0;

                UI(new Runnable() {
                    @Override
                    public void run() {
                        edit.setHint("输入评论");
                        edit.setText("");
                        hintKeyboard();
                        T.show(Comment.this, r.getMsg());
                    }
                });
            }
        });
    }

    private void hintKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    @OnClick(R.id.name)
    void jump() {
        if (m == null)
            return;
        Intent i = new Intent(this, User.class);
        i.putExtra("uid", m.getUid());
        startActivity(i);
    }

    @Override
    public void init() {

        if ((wid = getIntent().getIntExtra("wid", 0)) == 0) {
            T.show(this, "参数错误");
            return;
        }

        adapter = new CommentAdapter(data, this);
        tl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tl.setAdapter(adapter);
        tl.setNestedScrollingEnabled(false);

        rf.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getWeico();
                Refresh(1);
            }
        });
        rf.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Refresh(0);
            }
        });
        getWeico();
        Refresh(1);
    }


    void getWeico() {

        Pool.run(new Runnable() {
            @Override
            public void run() {

                String url = "get.php?wid=" + wid;
                final SysMsg t = URLService.get(url, CommWeicoModel.class);
                if (!check(t, rf)) {
                    return;
                }
                m = ((CommWeicoModel) t).getInner();
                final Bitmap bit = Curl.getImage(Config.face+m.getFace());
                UI(new Runnable() {
                    @Override
                    public void run() {
                        face.setImageBitmap(bit);
                        name.setText(m.getName());
                        time.setText(Time.format(m.getTime()));
                        text.setMovementMethod(LinkMovementMethod.getInstance());
                        text.setText(parse.parse(m.getText()));
                        comment.setText(String.valueOf(m.getComment()) + "评论");
                        like.setText(String.valueOf(m.getLove() + "赞"));
                        if(m.getPhoto().length()==0)
                            NineGrid.setVisibility(View.GONE);
                        else{
                            NineGrid.setAdapter(new GridAdapter(Comment.this));
                            NineGrid.setImagesData(new ArrayList(Arrays.asList(m.getPhoto().split(","))));
                        }
                    }
                });
            }
        });
    }

    void Refresh(final int flag) {

        final LinkedList<Comm.InnerBean.CommentBean> list = data.getComment();
        if (flag == 1) {
            if (list.size() != 0) {
                id = list.get(0).getId();
            }
        } else {
            id = data.getComment().get(list.size() - 1).getId();
        }

        Pool.run(new Runnable() {
            @Override
            public void run() {

                String url = "comment.php?wid=" + wid + "&flag=" + flag + "&id=" + id;
                final SysMsg t = URLService.get(url, Comm.class);
                if (!check(t, rf)) {
                    return;
                }
                Comm.InnerBean tmp = ((Comm) t).getInner();
                data.getUser().putAll(tmp.getUser());
                LinkedList<Comm.InnerBean.CommentBean> c = tmp.getComment();
                if (flag == 1) {
                    for (int i = 0; i < c.size(); i++)
                        list.addFirst(c.get(c.size() - i - 1));
                    int n = list.size() - cache;
                    for (int i = 0; i < n; i++) {
                        list.removeLast();
                    }
                } else if (tmp.getComment().size() > 0) {
                    list.addAll(c);
                    int n = list.size() - cache;
                    for (int i = 0; i < n; i++) {
                        list.removeFirst();
                    }
                }

                UI(new Runnable() {
                    @Override
                    public void run() {
                        SmartRefresh(rf);
                        adapter.notifyDataSetChanged();
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
