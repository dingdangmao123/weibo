package com.gapcoder.weico.Message.FG;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gapcoder.weico.Index.Adapter.TitleAdapter;
import com.gapcoder.weico.Index.Model.TitleModel;
import com.gapcoder.weico.R;

import java.util.LinkedList;

/**
 * Created by suxiaohui on 2018/3/7.
 */

public class AtFG extends Fragment {

    LinkedList<TitleModel> data=new LinkedList<>();
    LinkedList<TitleModel> tmp=new LinkedList<>();
    TitleAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_at_fg, container, false);


        return v;
    }
}
