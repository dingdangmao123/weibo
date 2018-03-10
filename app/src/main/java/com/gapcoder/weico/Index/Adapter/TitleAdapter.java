package com.gapcoder.weico.Index.Adapter;

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
import android.widget.Toast;

import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.R;
import com.gapcoder.weico.Title.Title;
import com.gapcoder.weico.Utils.Image;
import com.gapcoder.weico.Utils.Time;

import java.util.List;

/**
 * Created by suxiaohui on 2018/3/3.
 */



public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.SnapViewHolder> {

    private Context context;

    private List<TitleModel.inner> data;

    public TitleAdapter(List<TitleModel.inner> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public SnapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.titleitem, parent, false);
        final SnapViewHolder h= new SnapViewHolder(view);
        h.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p=h.getAdapterPosition();
                Intent i=new Intent(context,Title.class);
                i.putExtra("id",data.get(p).getId());
                i.putExtra("title",data.get(p).getTitle());
                context.startActivity(i);
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(SnapViewHolder h, int position) {
        TitleModel.inner m = data.get(position);
        h.t1.setText("#" + m.getTitle() + "#");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SnapViewHolder extends RecyclerView.ViewHolder {

        TextView t1;

        public SnapViewHolder(View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.title);
        }
    }
}


