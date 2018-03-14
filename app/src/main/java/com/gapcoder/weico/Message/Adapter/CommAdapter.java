package com.gapcoder.weico.Message.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.weico.Comment.Comment;
import com.gapcoder.weico.Config;
import com.gapcoder.weico.Message.Model.AtModel;
import com.gapcoder.weico.Message.Model.CommModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Utils.Image;
import com.gapcoder.weico.Utils.Time;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class CommAdapter  extends   RecyclerView.Adapter<CommAdapter.SnapViewHolder>{

    private Context context;
    private LinkedList<CommModel.InnerBean> data;

    public CommAdapter(LinkedList<CommModel.InnerBean> data, Context context) {
        this.data=data;
        this.context = context;
    }

    @Override
    public CommAdapter.SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.commitem, parent, false);
        final CommAdapter.SnapViewHolder h= new CommAdapter.SnapViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Comment.class);
                int p=h.getAdapterPosition();
                i.putExtra("wid",data.get(p).getWid());
                context.startActivity(i);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(CommAdapter.SnapViewHolder h, int position) {

        CommModel.InnerBean m=data.get(position);
        h.name.setText(m.getName());
        h.time.setText(Time.format(m.getTime()));
        h.text.setText(m.getText());
        String face=Config.face+m.getFace();
        if(!face.equals((String)h.face.getTag()))
            h.face.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.face));

        h.face.setTag(face);
        Image.down((Activity)context,h.face, face);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {

        ImageView face;
        TextView name;
        TextView time;
        TextView text;
        public SnapViewHolder(View itemView) {
            super(itemView);
            face=(ImageView) itemView.findViewById(R.id.iv);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
