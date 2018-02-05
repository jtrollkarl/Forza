package com.moducode.forzateams.service;

import com.moducode.forzateams.data.Team;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Jay on 2018-02-02.
 */

public interface RetrofitTeamService {

    @GET("teams.json")
    Observable<List<Team>> getTeams();

}
