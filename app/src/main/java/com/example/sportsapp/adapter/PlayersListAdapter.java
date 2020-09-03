package com.example.sportsapp.adapter;

import android.content.Context;
import android.text.TextUtils;
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
import com.example.sportsapp.response.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.ViewHolder> {

    private Context context;
    List<Player> playerList = new ArrayList<>();
    private IPlayerDetailsInterface iPlayerDetailsInterface;

    public PlayersListAdapter(Context context, IPlayerDetailsInterface iPlayerDetailsInterface) {
        this.context = context;
        this.iPlayerDetailsInterface = iPlayerDetailsInterface;
    }

    @NonNull
    @Override
    public PlayersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.player_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersListAdapter.ViewHolder viewHolder, int i) {
        final Player player= playerList.get(i);
        if(player != null){
        viewHolder.tvPlayerName.setText(player.getStrPlayer()!= null ? player.getStrPlayer() : "");
        String team1=player.getStrTeam() != null ? player.getStrTeam():"" ;
        String team2= !TextUtils.isEmpty(player.getStrTeam2()) ? " | "+player.getStrTeam2():"" ;
        viewHolder.tvTeam.setText(" - Team/Teams: "+team1 + team2);
        viewHolder.tvDescription.setText(player.getStrDescriptionEN() != null ? player.getStrDescriptionEN():"");
        if(player.getStrThumb() != null) {
            Glide.with(context)
                    .load(player.getStrThumb())
                    .into(viewHolder.profilePIC);
            }
            viewHolder.lContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iPlayerDetailsInterface.onPlayerItemClick(player.getIdPlayer());
                }
            });
        }
    }

    public void addPlayerList(List<Player> playerList){
        Log.d("TAG", "playersAdapter");
        this.playerList = playerList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  playerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView profilePIC;
        private final TextView tvPlayerName;
        private final TextView tvTeam;
        private final TextView tvDescription;
        private final RelativeLayout lContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePIC = itemView.findViewById(R.id.dp);
            tvPlayerName = itemView.findViewById(R.id.player_name);
            tvTeam = itemView.findViewById(R.id.team);
            tvDescription= itemView.findViewById(R.id.description);
            lContainer = itemView.findViewById(R.id.container);
        }
    }
    public interface IPlayerDetailsInterface {
        void onPlayerItemClick(String id);
    }
}
