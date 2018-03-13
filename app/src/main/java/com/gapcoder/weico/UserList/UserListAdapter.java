package com.gapcoder.weico.UserList;

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

import com.gapcoder.weico.Account.Account;
import com.gapcoder.weico.Config;
import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Title.Title;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Utils.Image;

import java.util.List;

/**
 * Created by suxiaohui on 2018/3/5.
 */




public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.SnapViewHolder> {

    private Context mContext;

    private List<UserListModel.InnerBean> data;

    public UserListAdapter(List<UserListModel.InnerBean> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public UserListAdapter.SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(mContext).inflate(R.layout.userlistitem, parent, false);
        final UserListAdapter.SnapViewHolder h= new UserListAdapter.SnapViewHolder(view);
        h.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p=h.getAdapterPosition();
                Intent i=new Intent(mContext,User.class);
                i.putExtra("uid",data.get(p).getId());
                mContext.startActivity(i);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(UserListAdapter.SnapViewHolder h, int position) {

        UserListModel.InnerBean m = data.get(position);
        h.name.setText(m.getName());

        if(!m.getFace().equals((String)h.face.getTag()))
            h.face.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.face));

        h.face.setTag(m.getFace());
        Image.down((Activity)mContext,h.face, Config.face+m.getFace());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {

        View v;
        ImageView face;
        TextView name;

        public SnapViewHolder(View itemView) {
            super(itemView);
            v=itemView;
            face=(ImageView)itemView.findViewById(R.id.face);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}

