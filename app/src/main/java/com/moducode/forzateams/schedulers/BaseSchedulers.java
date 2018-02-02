package com.moducode.forzateams.schedulers;

import io.reactivex.Scheduler;

/**
 * Created by Jay on 2018-02-02.
 */

public interface BaseSchedulers {

    Scheduler io();

    Scheduler computation();

    Scheduler ui();

}
