package com.moducode.forzateams.ui.fragment;

import android.support.annotation.StringRes;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.moducode.forzateams.R;
import com.moducode.forzateams.data.Team;

import java.util.List;

/**
 * Created by Jay on 2018-02-02.
 */

public interface TeamsFragmentContract {

    interface View extends MvpLceView<List<Team>>{
        void showMessage(@StringRes int resId);

    }

    interface Actions extends MvpPresenter<View>{
        void fetchTeams(boolean pullToRefresh);
    }


}
