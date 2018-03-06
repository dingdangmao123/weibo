package com.gapcoder.weico.Comment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Title.Title;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Utils.Image;
import com.gapcoder.weico.Utils.Time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by suxiaohui on 2018/3/6.
 */



public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.SnapViewHolder> {

    private Context mContext;

    private Reply reply;
    private LinkedList<CommentModel> comment;
    private HashMap<Integer,Comm.UserBean> user;




    public CommentAdapter(Comm data, Context context) {

        comment=data.getComment();
        user=data.getUser();
        this.mContext = context;
        reply=new Reply(context);
        Log.i("tag","title");
    }

    @Override
    public CommentAdapter.SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(mContext).inflate(R.layout.commentitem , parent, false);
        final CommentAdapter.SnapViewHolder h= new CommentAdapter.SnapViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p=h.getAdapterPosition();
                ((Comment)mContext).update(user.get(comment.get(p).getUid()).getName(),comment.get(p).getUid(),comment.get(p).getId());

            }
        });
        h.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,User.class);
                int p=h.getAdapterPosition();
                i.putExtra("uid",comment.get(p).getUid());
                mContext.startActivity(i);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(CommentAdapter.SnapViewHolder h, int position) {
        CommentModel m = comment.get(position);
        String face=user.get(m.getUid()).getFace();
        Log.i("adapter",m.getText());
        Log.i("adapter",user.get(m.getUid()).getName());
        h.name.setText(user.get(m.getUid()).getName());
        h.time.setText(Time.format(m.getTime()));

        if(m.getOid()>0){
            h.text.setText(reply.parse(m.getText(),m.getOid(),user.get(m.getOid()).getName()));
        }else{
            h.text.setText(m.getText());
        }

        if(!face.equals((String)h.face.getTag()))
            h.face.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.face));

        h.face.setTag(face);
       Image.down((Activity)mContext,h.face,face);
    }

    @Override
    public int getItemCount() {
        return comment.size();
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

