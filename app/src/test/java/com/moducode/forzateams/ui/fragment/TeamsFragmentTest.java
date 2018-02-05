package com.moducode.forzateams.ui.fragment;

import android.util.MalformedJsonException;

import com.moducode.forzateams.BuildConfig;
import com.moducode.forzateams.R;
import com.moducode.forzateams.TestData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.net.ConnectException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by Jay on 2018-02-03.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TeamsFragmentTest {

    private TeamsFragment view;

    @Mock
    private TeamsFragmentPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        view = new TeamsFragment();
        startFragment(view);
        view.setPresenter(presenter);
    }

    @Test
    public void assertFragmentNotNull() throws Exception{
        assertNotNull(view);
    }

    @Test
    public void loadData_FALSE() throws Exception{
        view.loadData(false);
        verify(presenter).fetchTeams(false);
    }

    @Test
    public void loadData_TRUE() throws Exception{
        view.loadData(true);
        verify(presenter).fetchTeams(true);
    }

    @Test
    public void onRefresh() throws Exception{
        view.onRefresh();
        verify(presenter).fetchTeams(true);
    }

    @Test
    public void showContent() throws Exception{
        view.showContent();
        assertFalse(view.swipeRefreshLayout.isRefreshing());
    }

    @Test
    public void setData() throws Exception{
        view.setData(TestData.teams);
        assertEquals(2, view.recyclerTeams.getAdapter().getItemCount());
    }

    @Test
    public void showError() throws Exception{
        view.showError(new Exception("error"), false);
        assertFalse(view.swipeRefreshLayout.isRefreshing());
    }

    @Test
    public void getErrorMessage() throws Exception{
        assertEquals(view.getContext().getString(R.string.error_fetching_teams), view.getErrorMessage(new Exception("Error"), false)) ;
    }

    @Test
    public void getErrorMessage_MALFORMED_JSON() throws Exception{
        assertEquals(view.getContext().getString(R.string.error_parse_json), view.getErrorMessage(new MalformedJsonException("Error"), false)) ;
    }

    @Test
    public void getErrorMessage_CONNECT_EXCEPTION() throws Exception{
        assertEquals(view.getContext().getString(R.string.error_connection), view.getErrorMessage(new ConnectException("Error"), false)) ;
    }

}