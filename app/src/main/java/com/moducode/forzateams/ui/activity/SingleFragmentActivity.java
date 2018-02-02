package com.moducode.forzateams.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.moducode.forzateams.R;

/**
 * Created by Jay on 2018-02-02.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment();
    }

    private void setFragment(){
        FragmentManager fm = getSupportFragmentManager();

        Fragment f = fm.findFragmentById(R.id.fragment_container);
        if(f == null){
            fm.beginTransaction()
                    .replace(R.id.fragment_container, getFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    protected abstract Fragment getFragment();
}
