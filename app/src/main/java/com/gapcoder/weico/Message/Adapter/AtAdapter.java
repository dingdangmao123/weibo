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

import com.gapcoder.weico.Comment.Comm;
import com.gapcoder.weico.Comment.Comment;
import com.gapcoder.weico.Comment.CommentModel;
import com.gapcoder.weico.Message.Model.AtModel;
import com.gapcoder.weico.Message.Model.FollowModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Utils.Image;
import com.gapcoder.weico.Utils.Time;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/7.
 */


public class AtAdapter  extends RecyclerView.Adapter<AtAdapter.SnapViewHolder>{

    private Context context;



    private LinkedList<AtModel.InnerAtModel> inner;
    private HashMap<Integer,AtModel.UserBean> user;

    public AtAdapter(AtModel data, Context context) {
        inner=data.getInner();
        user=data.getUser();
        this.context = context;
    }

    @Override
    public AtAdapter.SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.atitem, parent, false);
        final AtAdapter.SnapViewHolder h= new AtAdapter.SnapViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Comment.class);
                int p=h.getAdapterPosition();
                i.putExtra("wid",inner.get(p).getWid());
                context.startActivity(i);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(AtAdapter.SnapViewHolder h, int position) {
        AtModel.InnerAtModel m=inner.get(position);
        int id=m.getHid();
        String face=user.get(m.getHid()).getFace();
        h.t1.setText(user.get(id).getName());
        h.t2.setText("@了你·"+ Time.format(m.getTime()));


        if(!face.equals((String)h.face.getTag()))
            h.face.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.face));

        h.face.setTag(face);
        Image.down((Activity)context,h.face,face);
    }

    @Override
    public int getItemCount() {
        return inner.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {

        ImageView face;
        TextView t1;
        TextView t2;


        public SnapViewHolder(View itemView) {
            super(itemView);
            face = (ImageView) itemView.findViewById(R.id.face);
            t1 = (TextView) itemView.findViewById(R.id.name);
            t2 = (TextView) itemView.findViewById(R.id.text);

        }
    }
}
