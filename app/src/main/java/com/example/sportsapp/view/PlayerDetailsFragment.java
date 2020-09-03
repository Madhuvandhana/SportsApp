package com.example.sportsapp.view;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.sportsapp.R;
import com.example.sportsapp.databinding.PlayerDetailsBinding;
import com.example.sportsapp.response.Formerteam;
import com.example.sportsapp.response.Honor;
import com.example.sportsapp.response.Player;
import com.example.sportsapp.response.PlayerDetailResponse;
import com.example.sportsapp.response.PlayerFormerTeamResponse;
import com.example.sportsapp.response.PlayerHonourResponse;
import com.example.sportsapp.utils.NetworkUtility;
import com.example.sportsapp.utils.StringConstants;
import com.example.sportsapp.utils.customcomponents.NetworkChangeReceiver;
import com.example.sportsapp.viewModel.PlayerDetailsViewModel;

import java.util.List;
import java.util.Objects;

public class PlayerDetailsFragment  extends Fragment implements NetworkChangeReceiver.NetworkStateReceiverListener{
    public NetworkChangeReceiver networkStateChangeReceiver;
    private PlayerDetailsBinding playerDetailsBinding;
    private PlayerDetailsViewModel playerDetailsViewModel;
    private String playerId = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Setting receiver for network state check **/
        networkStateChangeReceiver = NetworkChangeReceiver.getInstance();
        networkStateChangeReceiver.addListener(this);
        Objects.requireNonNull(getActivity()).registerReceiver(networkStateChangeReceiver,
                new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }
    public static PlayerDetailsFragment newInstance(String id) {
        PlayerDetailsFragment fragment = new PlayerDetailsFragment();
        Bundle args = new Bundle();
        args.putString(StringConstants._ID, id);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        playerDetailsBinding = DataBindingUtil.inflate
                (inflater, R.layout.player_details,
                        container, false);
        View playerDetailsView = playerDetailsBinding.getRoot();
        playerDetailsViewModel = new PlayerDetailsViewModel(getActivity());
        playerDetailsBinding.setPlayerDetailsViewModel(playerDetailsViewModel);
        if (getArguments() != null) {
            playerId = getArguments().getString(StringConstants._ID);
        }
        getPlayerDetails();
        return  playerDetailsView;
    }

    /**
     * on detach of fragment listener and event bus and network receiver will get unregister
     */
    @Override
    public void onDetach() {
        super.onDetach();
        if (networkStateChangeReceiver != null) {
            networkStateChangeReceiver.removeListener(this);
            try {
                Objects.requireNonNull(getActivity()).unregisterReceiver(networkStateChangeReceiver);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void networkAvailable() {
        getPlayerDetails();
    }

    private void getPlayerDetails() {
        playerDetailsViewModel.getPlayerDetailsResponseLiveData(playerId).observe(Objects.requireNonNull(getActivity()), new Observer<PlayerDetailResponse>() {
            @Override
            public void onChanged(PlayerDetailResponse response) {
                if (response != null && response.getPlayer() != null && !response.getPlayer().isEmpty()){
                    Player player = response.getPlayer().get(0);
                    playerDetailsBinding.playerName.setText(player.getStrPlayer()!= null ? player.getStrPlayer() : "");
                    if(player.getStrThumb() != null) {
                        Glide.with(Objects.requireNonNull(getActivity()))
                                .load(player.getStrThumb())
                                .into(playerDetailsBinding.dp);
                    }
                    playerDetailsBinding.description.setText(player.getStrDescriptionEN());
                    callLookupApi();
                }
                else
                    playerDetailsBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void callLookupApi(){
        playerDetailsViewModel.getHonours(playerId).observe(Objects.requireNonNull(getActivity()), new Observer<PlayerHonourResponse>() {
            @Override
            public void onChanged(PlayerHonourResponse response) {
                if (response != null && response.getHonors() != null) {
                    List<Honor> honors = response.getHonors();
                    for (Honor honor : honors) {
                        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        TextView tv = new TextView(getActivity());
                        tv.setLayoutParams(lparams);
                        tv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.white));
                        tv.setText("\u2022 "+honor.getStrHonour());
                        playerDetailsBinding.honors.addView(tv);
                    }
                }
                else
                    playerDetailsBinding.progressBar.setVisibility(View.GONE);
            }
        });
        playerDetailsViewModel.getFormerTeam(playerId).observe(getActivity(), new Observer<PlayerFormerTeamResponse>() {
            @Override
            public void onChanged(PlayerFormerTeamResponse response) {
                playerDetailsBinding.progressBar.setVisibility(View.GONE);
                if (response != null  && response.getFormerteams()!= null) {
                    List<Formerteam> teams = response.getFormerteams();
                    for (Formerteam team : teams) {
                        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        TextView tv = new TextView(getActivity());
                        tv.setLayoutParams(lparams);
                        tv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.white));
                        tv.setText("\u2022 "+team.getStrFormerTeam());
                        playerDetailsBinding.formerTeams.addView(tv);
                    }
                }
            }
        });

    }

    @Override
    public void networkUnavailable() {
        NetworkUtility.showInternetDialog(getActivity());
    }
}
