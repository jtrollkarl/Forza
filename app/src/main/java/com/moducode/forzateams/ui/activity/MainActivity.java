package com.moducode.forzateams.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.moducode.forzateams.R;
import com.moducode.forzateams.ui.fragment.TeamsFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected Fragment getFragment() {
        return new TeamsFragment();
    }


}
