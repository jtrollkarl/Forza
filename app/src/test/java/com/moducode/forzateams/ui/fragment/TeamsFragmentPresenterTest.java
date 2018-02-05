package com.moducode.forzateams.ui.fragment;

import android.util.MalformedJsonException;

import com.moducode.forzateams.ErrorMapperKt;
import com.moducode.forzateams.TestData;
import com.moducode.forzateams.TestSchedulerRule;
import com.moducode.forzateams.data.Team;
import com.moducode.forzateams.schedulers.ImmediateSchedulers;
import com.moducode.forzateams.service.RetrofitTeamService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jay on 2018-02-02.
 */


public class TeamsFragmentPresenterTest {

    @Rule
    public final TestSchedulerRule rule = new TestSchedulerRule();

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
    public void fetchTeams_LIST_SIZE(){
        when(service.getTeams()).thenReturn(Observable.just(TestData.teams));

        service.getTeams().test()
                .assertNoErrors()
                .assertValue(list -> list.size() == 2);
    }

    @Test
    public void fetchTeams_RETRY(){
        ConnectException connectException = new ConnectException("Error");
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("Error");
        MalformedJsonException malformedJsonException = new MalformedJsonException("Error");



        when(service.getTeams())
                .thenReturn(Observable.fromCallable(new Callable<List<Team>>() {
                    private boolean firstItemEmitted;

                    @Override
                    public List<Team> call() throws Exception {
                        if(!firstItemEmitted){
                            firstItemEmitted = true;
                            throw malformedJsonException;
                        }else {
                            return TestData.teams;
                        }
                    }
                }));

        TestObserver<List<Team>> testObserver = service.getTeams().test();


        testObserver.assertError(malformedJsonException);

        TestObserver<List<Team>> testObserver1 = service.getTeams().test();

        rule.getTestScheduler().advanceTimeBy(4, TimeUnit.SECONDS);
        testObserver1.assertValue(TestData.teams);

    }

}