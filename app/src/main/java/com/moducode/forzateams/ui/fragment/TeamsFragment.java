package com.moducode.forzateams.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;
import com.moducode.forzateams.R;
import com.moducode.forzateams.data.Team;

import java.util.List;

public class TeamsFragment extends MvpLceFragment<SwipeRefreshLayout, List<Team>, TeamsFragmentContract.View, TeamsFragmentContract.Actions>
    implements TeamsFragmentContract.View{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public TeamsFragmentContract.Actions createPresenter() {
        return null;
    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public void setData(List<Team> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
