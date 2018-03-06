package com.gapcoder.weico.Index.FG;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gapcoder.weico.Index.Adapter.WeicoAdapter;
import com.gapcoder.weico.Index.Model.WeicoModel;
import com.gapcoder.weico.Index.Service.WeicoService;
import com.gapcoder.weico.R;

import java.util.LinkedList;
import java.util.List;


public class AccountFG extends BaseFG {


    public AccountFG() {
        // Required empty public constructor
    }


    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return   inflater.inflate(R.layout.fragment_account_fg, container, false);
    }
    @Override
    public void CreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState,View v) {


    }

}
