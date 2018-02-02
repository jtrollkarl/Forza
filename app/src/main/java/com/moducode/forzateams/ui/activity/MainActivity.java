package com.moducode.forzateams.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.moducode.forzateams.R;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Fragment getFragment() {
        // TODO: 2018-02-02 change to real fragment
        return new Fragment();
    }


}
