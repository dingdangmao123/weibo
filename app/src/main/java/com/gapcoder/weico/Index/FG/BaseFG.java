package com.gapcoder.weico.Index.FG;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gapcoder.weico.Post;
import com.gapcoder.weico.R;

/**
 * Created by suxiaohui on 2018/3/2.
 */

public class BaseFG extends Fragment{


    public BaseFG() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=init(inflater,container,savedInstanceState);
        Toolbar toolbar=(Toolbar)v.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar bar=((AppCompatActivity) getActivity()).getSupportActionBar();
        if(bar!=null){
            setLeftIcon(bar);
        }
        setHasOptionsMenu(true);
        CreateView(inflater,container,savedInstanceState,v);
        return v;
    }

  void CreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState,View v){

  }
    View init(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return null;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.photo:
                Intent i=new Intent(getActivity(),Post.class);
                startActivity(i);
                break;
            case android.R.id.home:
                leftSelected();
                break;
        }
        return false;
    }
    public void leftSelected(){

    }
    public void setLeftIcon(android.support.v7.app.ActionBar bar){

    }

    void UI(Runnable r){
        getActivity().runOnUiThread(r);
    }

}
