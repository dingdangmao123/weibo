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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gapcoder.weico.Comment.Comment;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Title.Title;
import com.gapcoder.weico.User.User;
import com.gapcoder.weico.Utils.Image;
import com.gapcoder.weico.Utils.LinkText;
import com.gapcoder.weico.Utils.Time;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.LinkedList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by suxiaohui on 2018/3/2.
 */


public class WeicoAdapter extends RecyclerView.Adapter<WeicoAdapter.SnapViewHolder> {

    private Context mContext;

    private List<WeicoModel.InnerBean> data;

    LinkText parse;

    public WeicoAdapter(List<WeicoModel.InnerBean> data, Context context) {
        this.data = data;
        this.mContext = context;
        parse=new LinkText(context);
        url.add("http://img3.imgtn.bdimg.com/it/u=776553116,300482899&fm=200&gp=0.jpg");
        url.add("http://img0.imgtn.bdimg.com/it/u=3167476013,1399350923&fm=200&gp=0.jpg");
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(mContext).inflate(R.layout.weicoitem, parent, false);
        final SnapViewHolder h= new SnapViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,Comment.class);
                int p=h.getAdapterPosition();
                i.putExtra("wid",data.get(p).getId());
                Log.i("tag",String.valueOf(data.get(p).getId()));
                mContext.startActivity(i);

            }
        });
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

        WeicoModel.InnerBean m=data.get(position);
        h.t1.setText(m.getName());
        h.t2.setText(Time.format(m.getTime()));
        h.t4.setMovementMethod(LinkMovementMethod.getInstance());
        h.t4.setText(parse.parse(m.getText()));
        h.t5.setText(String.valueOf(m.getComment())+"评论");
        h.t6.setText(String.valueOf(m.getLove()+"赞"));

        if(!m.getFace().equals((String)h.face.getTag()))
            h.face.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.drawable.face));

        h.Grid.setAdapter(mAdapter);

        h.Grid.setImagesData(url);

        h.face.setTag(m.getFace());
        Image.down((Activity)mContext,h.face,m.getFace());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {


        NineGridImageView Grid;
        ImageView face;
        TextView t1;
        TextView t2;
        TextView t4;
        TextView t5;
        TextView t6;

        public SnapViewHolder(View itemView) {
            super(itemView);

            Grid=(NineGridImageView)itemView.findViewById(R.id.NineGrid);
            face = (ImageView) itemView.findViewById(R.id.face);
            t1 = (TextView) itemView.findViewById(R.id.name);
            t2 = (TextView) itemView.findViewById(R.id.time);
            t4 = (TextView) itemView.findViewById(R.id.text);
            t5 = (TextView) itemView.findViewById(R.id.comment);
            t6 = (TextView) itemView.findViewById(R.id.like);

        }
    }

    NineGridImageViewAdapter<String> mAdapter=new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView iv, String photo) {
            iv.setTag(photo);
            Log.i("tag",photo);
            Image.down((Activity)mContext,iv,photo);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

     /*   @Override
        protected void onItemImageClick(Context context, int index, List<String> photoList) {
            //showBigPicture(context, photoList.get(index));
        }*/
    };

    List<String> url=new LinkedList<>();

}


