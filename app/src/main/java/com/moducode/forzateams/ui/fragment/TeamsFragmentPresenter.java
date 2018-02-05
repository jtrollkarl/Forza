package com.moducode.forzateams.ui.fragment;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.moducode.forzateams.ErrorHelperKt;

import com.moducode.forzateams.schedulers.BaseSchedulers;
import com.moducode.forzateams.service.RetrofitTeamService;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by Jay on 2018-02-02.
 */

public class TeamsFragmentPresenter extends MvpBasePresenter<TeamsFragmentContract.View> implements TeamsFragmentContract.Actions {

    private final RetrofitTeamService teamService;
    private final BaseSchedulers schedulers;

    public TeamsFragmentPresenter(RetrofitTeamService teamService, BaseSchedulers schedulers) {
        this.teamService = teamService;
        this.schedulers = schedulers;
    }

    @Override
    public void fetchTeams(boolean pullToRefresh) {
        ifViewAttached(v -> v.showLoading(pullToRefresh));

        teamService.getTeams()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .retryWhen(throwableObservable -> throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
                    if(ErrorHelperKt.retryOnError(throwable)){
                        Timber.e(throwable, "Retrying in 10 seconds");
                        return Observable.timer(10, TimeUnit.SECONDS);
                    }
                    return Observable.error(throwable);
                }))
                .subscribe(
                        teamList -> ifViewAttached(v -> v.setData(teamList)),
                        error -> {
                            Timber.e(error, "Error fetching teams list");
                            ifViewAttached(v -> v.showError(error, pullToRefresh));
                        },
                        () -> ifViewAttached(MvpLceView::showContent));
    }


}
