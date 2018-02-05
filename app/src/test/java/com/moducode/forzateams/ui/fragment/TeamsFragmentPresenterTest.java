package com.moducode.forzateams.ui.fragment;

import com.moducode.forzateams.TestData;
import com.moducode.forzateams.schedulers.ImmediateSchedulers;
import com.moducode.forzateams.service.RetrofitTeamService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jay on 2018-02-02.
 */


public class TeamsFragmentPresenterTest {

    @Mock
    private TeamsFragmentContract.View view;

    @Mock
    private RetrofitTeamService service;

    private TeamsFragmentPresenter subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new TeamsFragmentPresenter(service, new ImmediateSchedulers());
        subject.attachView(view);
    }

    @Test
    public void fetchTeams_SUCCESS_PTR() throws Exception {
        when(service.getTeams()).thenReturn(Observable.just(TestData.teams));

        subject.fetchTeams(true);

        verify(view).showLoading(true);
        verify(view).setData(TestData.teams);
        verify(view).showContent();
    }

    @Test
    public void fetchTeams_SUCCESS_NO_PTR(){
        when(service.getTeams()).thenReturn(Observable.just(TestData.teams));

        subject.fetchTeams(false);
        verify(view).showLoading(false);
        verify(view).setData(TestData.teams);
        verify(view).showContent();
    }

    @Test
    public void fetchTeams_ERROR(){
        Throwable e = new Throwable("Error");
        when(service.getTeams()).thenReturn(Observable.error(e));

        subject.fetchTeams(true);
        verify(view).showLoading(true);
        verify(view).showError(e, true);
    }

    @Test
    public void fetchTeams_RETRY_TIMEOUT_EXCEPTION() throws Exception{
        SocketTimeoutException timeoutException = new SocketTimeoutException("Error");
        when(service.getTeams()).thenReturn(Observable.error(timeoutException));

        subject.fetchTeams(true);
        verify(view).showLoading(true);
        verify(view, never()).showError(any(Throwable.class), anyBoolean());
    }

    @Test
    public void fetchTeams_RETRY_CONNECTION_EXCEPTION() throws Exception{
        ConnectException connectException = new ConnectException("Error");
        when(service.getTeams()).thenReturn(Observable.error(connectException));

        subject.fetchTeams(true);
        verify(view).showLoading(true);
        verify(view, never()).showError(any(Throwable.class), anyBoolean());
    }

    @Test
    public void fetchTeams_RETRY_UNKNOWNHOST() throws Exception{
        UnknownHostException unknownHostException = new UnknownHostException("Error");
        when(service.getTeams()).thenReturn(Observable.error(unknownHostException));

        subject.fetchTeams(true);
        verify(view).showLoading(true);
        verify(view, never()).showError(any(Throwable.class), anyBoolean());
    }

    @Test
    public void fetchTeams_LIST_SIZE(){
        when(service.getTeams()).thenReturn(Observable.just(TestData.teams));

        service.getTeams().test()
                .assertNoErrors()
                .assertValue(list -> list.size() == 2);
    }



}