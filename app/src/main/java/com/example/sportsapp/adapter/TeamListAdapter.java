package com.example.sportsapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.sportsapp.R;
import com.example.sportsapp.response.Team;
import java.util.ArrayList;
import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder> {

    private Context context;
    List<Team> teamList = new ArrayList<>();
    private ITeamDetailsInterface iTeamDetailsInterface;

    public TeamListAdapter(Context context, ITeamDetailsInterface iTeamDetailsInterface) {
        this.context = context;
        this.iTeamDetailsInterface = iTeamDetailsInterface;
    }

    @NonNull
    @Override
    public TeamListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.team_item,viewGroup,false);
        return new TeamListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamListAdapter.ViewHolder viewHolder, int i) {
        final Team team= teamList.get(i);
        if(team != null) {
            viewHolder.tvTeam.setText(" -" + team.getStrTeam());
            viewHolder.tvDescription.setText(team.getStrDescriptionEN() != null ? team.getStrDescriptionEN() :"");
            if(team.getStrTeamBadge() != null) {
                Glide.with(context)
                        .load(team.getStrTeamBadge())
                        .into(viewHolder.profilePIC);
            }
            viewHolder.lContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iTeamDetailsInterface.onTeamItemClick(team.getIdTeam());
                }
            });

        }
    }

    public void addTeamList(List<Team> teams){
        Log.d("TAG", "teamAdapter");
        this.teamList = teams;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  teamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView profilePIC;
        private final TextView tvTeam;
        private final TextView tvDescription;
        private final RelativeLayout lContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePIC = itemView.findViewById(R.id.dp);
            tvTeam = itemView.findViewById(R.id.team);
            tvDescription= itemView.findViewById(R.id.description);
            lContainer = itemView.findViewById(R.id.container);
        }
    }
    public interface ITeamDetailsInterface {
        void onTeamItemClick(String id);
    }
}
