package com.moducode.forzateams.ui.fragment;

import com.moducode.forzateams.schedulers.ImmediateSchedulers;
import com.moducode.forzateams.service.RetrofitTeamService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by Jay on 2018-02-02.
 */
public class TeamsFragmentPresenterTest {

    @Mock
    TeamsFragmentContract.View view;

    @Mock
    RetrofitTeamService service;

    private TeamsFragmentPresenter subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new TeamsFragmentPresenter(service, new ImmediateSchedulers());
        subject.attachView(view);

    }

    @Test
    public void fetchTeams_SUCCESS() throws Exception {
    }

}