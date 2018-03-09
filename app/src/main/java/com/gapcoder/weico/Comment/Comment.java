package com.gapcoder.weico.Comment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.General.WeicoService;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Utils.Curl;
import com.gapcoder.weico.Utils.LinkText;
import com.gapcoder.weico.Utils.Pool;
import com.gapcoder.weico.Utils.T;
import com.gapcoder.weico.Utils.Time;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Comment extends Base {

    Handler mh = new Handler();
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
    RecyclerView timeline;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.edit)
    EditText edit;

    @BindView(R.id.send)
    Button send;
    @BindView(R.id.likebtn)
    Button likebtn;

    private Comm data = new Comm();
    private CommentAdapter adapter;
    private int wid;
    private LinkText parse = new LinkText(this);
    WeicoModel m;
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

    @OnClick(R.id.send)
    void send() {

        Pool.run(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient cli = new OkHttpClient();
                    RequestBody rb = new FormBody.Builder().add("uid", "1").add("text", edit.getText().toString()).
                            add("cuid", "" + cuid).add("cid", "" + cid).add("wid", "" + wid).add("hid", "" + m.getUid()).build();

                    Request req = new Request.Builder().url("http://10.0.2.2/weico/reply.php").post(rb).build();
                    Response res = cli.newCall(req).execute();
                    final String ok = res.body().string();
                    mh.post(new Runnable() {
                        @Override
                        public void run() {
                            T.show(Comment.this, ok);
                            edit.setHint("输入评论");
                            edit.setText("");
                            hintKeyboard();
                        }
                    });
                } catch (Exception e) {
                    // Toast.makeText(Post.this,e.toString(),Toast.LENGTH_SHORT).show();
                    T.show(Comment.this, e.toString());
                }
                cid = 0;
                cuid = 0;

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


    @OnClick(R.id.likebtn)
    void like(){



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

        if ((wid = getIntent().getIntExtra("wid", 0)) != 0)
            Refresh(1);

        adapter = new CommentAdapter(data, this);
        timeline.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        timeline.setAdapter(adapter);
        timeline.setNestedScrollingEnabled(false);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                Refresh(1);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                Refresh(0);
            }
        });
        getWeico();
    }


    void getWeico() {

        Pool.run(new Runnable() {
            @Override
            public void run() {
                m = WeicoService.getWeico(wid);
                Log.i("tag", m.toString());
                final Bitmap bit = Curl.getImage(m.getFace());
                mh.post(new Runnable() {
                    @Override
                    public void run() {

                        face.setImageBitmap(bit);
                        name.setText(m.getName());
                        time.setText(Time.format(m.getTime()));

                        text.setMovementMethod(LinkMovementMethod.getInstance());
                        text.setText(parse.parse(m.getText()));
                        comment.setText(String.valueOf(m.getComment()) + "评论");
                        like.setText(String.valueOf(m.getLike() + "赞"));
                    }
                });
            }
        });
    }

    void Refresh(final int flag) {

        final LinkedList<CommentModel> list = data.getComment();
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

                Comm tmp = CommentService.getList(wid, id, flag);
                if (tmp == null)
                    return;
                LinkedList<CommentModel> c = tmp.getComment();
                data.getUser().putAll(tmp.getUser());

                if (tmp.getComment().size() == 0)
                    return;

                if (flag == 1) {
                    for (int i = 0; i < c.size(); i++)
                        list.addFirst(c.get(c.size() - i - 1));
                    int n = list.size() - cache;
                    for (int i = 0; i < n; i++) {
                        list.removeLast();
                    }
                } else {
                    list.addAll(c);
                    int n = list.size() - cache;
                    for (int i = 0; i < n; i++) {
                        list.removeFirst();
                    }
                }
                Log.i("tag", "---------");
                for (int i = 0; i < data.getComment().size(); i++)
                    Log.i("tag", data.getComment().get(i).toString());

                Log.i("tag", data.getUser().toString());
                Log.i("tag", "---------");
                mh.post(new Runnable() {
                    @Override
                    public void run() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
