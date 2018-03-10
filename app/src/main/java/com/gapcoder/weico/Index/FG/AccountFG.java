package com.gapcoder.weico.Index.FG;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gapcoder.weico.R;


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
