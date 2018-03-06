package com.gapcoder.weico.Index.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Title.Title;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Utils.Image;
import com.gapcoder.weico.Utils.LinkText;
import com.gapcoder.weico.Utils.Time;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by suxiaohui on 2018/3/2.
 */


public class WeicoAdapter extends RecyclerView.Adapter<WeicoAdapter.SnapViewHolder> {

    private Context mContext;

    private List<WeicoModel> data;

    LinkText parse;

    public WeicoAdapter(List<WeicoModel> data, Context context) {
        this.data = data;
        this.mContext = context;
        parse=new LinkText(context);
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(mContext).inflate(R.layout.weicoitem, parent, false);
        final SnapViewHolder h= new SnapViewHolder(view);
  /*      h.t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,Title.class);
                int p=h.getAdapterPosition();
                i.putExtra("id",data.get(p).getId());
                i.putExtra("title",data.get(p).getTitle());
                mContext.startActivity(i);

            }
        });*/
        h.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,User.class);
                int p=h.getAdapterPosition();
                i.putExtra("uid",data.get(p).getUid());
                mContext.startActivity(i);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(SnapViewHolder h, int position) {

        WeicoModel m=data.get(position);
        h.t1.setText(m.getName());



        h.t2.setText(Time.format(m.getTime()));
        //h.t3.setText("#"+m.getTitle()+"#");

        h.t4.setMovementMethod(LinkMovementMethod.getInstance());
        h.t4.setText(parse.parse(m.getText()));
        h.t5.setText(String.valueOf(m.getComment())+"评论");
        h.t6.setText(String.valueOf(m.getLike()+"赞"));

        if(!m.getFace().equals((String)h.face.getTag()))
            h.face.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.face));



        h.face.setTag(m.getFace());
        Image.down((Activity)mContext,h.face,m.getFace());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {


        ImageView face;
        TextView t1;
        TextView t2;
        TextView t3;
        TextView t4;
        TextView t5;
        TextView t6;
        public SnapViewHolder(View itemView) {
            super(itemView);

            //left.setBackgroundResource(MyColor.get());
            face = (ImageView) itemView.findViewById(R.id.face);
            t1 = (TextView) itemView.findViewById(R.id.name);
            t2 = (TextView) itemView.findViewById(R.id.time);
            t3 = (TextView) itemView.findViewById(R.id.title);
            t4 = (TextView) itemView.findViewById(R.id.text);
            t5 = (TextView) itemView.findViewById(R.id.comment);
            t6 = (TextView) itemView.findViewById(R.id.like);

        }
    }


}


