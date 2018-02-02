package com.moducode.forzateams;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Jay on 2018-02-02.
 */

public class ForzaTeamsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
            if(BuildConfig.DEBUG){
                Timber.plant(new Timber.DebugTree());
            }
    }
}
