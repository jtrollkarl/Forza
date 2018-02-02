package com.moducode.forzateams.ui.fragment;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.moducode.forzateams.schedulers.BaseSchedulers;
import com.moducode.forzateams.service.RetrofitTeamService;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Jay on 2018-02-02.
 */

public class TeamsFragmentPresenter extends MvpBasePresenter<TeamsFragmentContract.View> implements TeamsFragmentContract.Actions{

    private final RetrofitTeamService teamService;
    private final BaseSchedulers schedulers;
    private final CompositeDisposable compositeDisposable;

    public TeamsFragmentPresenter(RetrofitTeamService teamService, BaseSchedulers schedulers) {
        this.teamService = teamService;
        this.schedulers = schedulers;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void fetchTeams(boolean pullToRefresh) {

    }


}
