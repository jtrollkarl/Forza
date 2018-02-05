package com.moducode.forzateams.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.delegate.FragmentMvpDelegate;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState;
import com.moducode.forzateams.ErrorHelper;
import com.moducode.forzateams.R;
import com.moducode.forzateams.data.Team;
import com.moducode.forzateams.schedulers.ImplSchedulers;
import com.moducode.forzateams.service.RetrofitFactory;
import com.moducode.forzateams.service.RetrofitTeamService;
import com.moducode.forzateams.ui.adapter.TeamsRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class TeamsFragment extends MvpLceViewStateFragment<SwipeRefreshLayout, List<Team>, TeamsFragmentContract.View, TeamsFragmentContract.Actions>
        implements TeamsFragmentContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_teams)
    RecyclerView recyclerTeams;

    @BindView(R.id.contentView)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.loadingView)
    ProgressBar progressBar;

    Unbinder unbinder;

    private TeamsRecyclerAdapter teamsRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);

        teamsRecyclerAdapter = new TeamsRecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTeams.setLayoutManager(linearLayoutManager);
        recyclerTeams.addItemDecoration(new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation()));
        recyclerTeams.setAdapter(teamsRecyclerAdapter);
    }

    @NonNull
    @Override
    public LceViewState<List<Team>, TeamsFragmentContract.View> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public TeamsFragmentContract.Actions createPresenter() {
        return new TeamsFragmentPresenter(RetrofitFactory.create(RetrofitTeamService.class), new ImplSchedulers());
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        int stringId = ErrorHelper.INSTANCE.getMessage(e);
        return getString(stringId);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public List<Team> getData() {
        return teamsRecyclerAdapter == null ? null : teamsRecyclerAdapter.getData();
    }

    @Override
    public void showContent() {
        super.showContent();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setData(List<Team> data) {
        teamsRecyclerAdapter.setData(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.fetchTeams(pullToRefresh);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        Timber.d("Refreshing list");
        presenter.fetchTeams(true);
    }

}
