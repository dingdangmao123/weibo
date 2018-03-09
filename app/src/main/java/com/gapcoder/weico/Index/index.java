package com.gapcoder.weico.Index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gapcoder.weico.General.Base;
import com.gapcoder.weico.Index.FG.AccountFG;
import com.gapcoder.weico.Index.FG.TitleFG;
import com.gapcoder.weico.Index.FG.WeicoFG;
import com.gapcoder.weico.Post;
import com.gapcoder.weico.R;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class index extends AppCompatActivity {

    FragmentManager fm = getSupportFragmentManager();

    HashMap<Integer, Fragment> map = new HashMap<>();
    HashSet<Integer> flag = new HashSet<>();

    @BindView(R.id.tab)
    BottomNavigationView tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        tab.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran = fm.beginTransaction();
                hideFragments(tran);
                int id = item.getItemId();
                if (!flag.contains(id)) {
                    tran.add(R.id.container, map.get(id));
                    flag.add(id);
                }
                tran.show(map.get(id));
                tran.commit();
                return true;
            }
        });

        FragmentTransaction tran = fm.beginTransaction();
        hideFragments(tran);
        Fragment fg = new WeicoFG();
        map.put(R.id.weico, fg);
        map.put(R.id.title, new TitleFG());
        map.put(R.id.account, new AccountFG());
        tran.add(R.id.container, fg);
        flag.add(R.id.weico);
        tran.commit();

    }

    private void hideFragments(FragmentTransaction transaction) {

        if (map.size() == 0)
            return;
        Iterator<Fragment> it = map.values().iterator();
        while (it.hasNext()) {
            transaction.hide(it.next());
        }

    }
}
