package com.moducode.forzateams.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moducode.forzateams.R;
import com.moducode.forzateams.data.Team;

import java.util.List;

/**
 * Created by Jay on 2018-02-02.
 */

public class TeamsRecycler extends RecyclerView.Adapter<TeamsRecycler.TeamsViewHolder>  {

    private List<Team> teamList;

    public void setData(List<Team> teamList){
        this.teamList = teamList;
    }

    @Override
    public TeamsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View teamView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_teams, parent, false);
        return new TeamsViewHolder(teamView);
    }

    @Override
    public void onBindViewHolder(TeamsViewHolder holder, int position) {
        final Team team = teamList.get(position);

        holder.countryName.setText(team.getCountryName());
        holder.teamName.setText(team.getName());

        if (team.getNational()){
            holder.national.setText(R.string.tv_teams_national_true);
        }else
            holder.national.setText(R.string.tv_teams_national_false);

    }

    @Override
    public int getItemCount() {
        if (teamList == null){
            return 0;
        }else return teamList.size();
    }

    class TeamsViewHolder extends RecyclerView.ViewHolder{

        TextView teamName;
        TextView national;
        TextView countryName;

        TeamsViewHolder(View itemView) {
            super(itemView);

            teamName = itemView.findViewById(R.id.tv_team_name);
            national = itemView.findViewById(R.id.tv_team_national);
            countryName = itemView.findViewById(R.id.tv_team_country);
        }


    }

}
