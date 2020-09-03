package com.example.sportsapp.view;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.sportsapp.R;
import com.example.sportsapp.databinding.TeamDetailsBinding;
import com.example.sportsapp.response.Event;
import com.example.sportsapp.response.EventDetailResponse;
import com.example.sportsapp.response.EventResultResponse;
import com.example.sportsapp.response.EventsResponse;
import com.example.sportsapp.response.PlayersResponse;
import com.example.sportsapp.response.Team;
import com.example.sportsapp.response.TeamResponse;
import com.example.sportsapp.utils.NetworkUtility;
import com.example.sportsapp.utils.StringConstants;
import com.example.sportsapp.utils.customcomponents.NetworkChangeReceiver;
import com.example.sportsapp.viewModel.TeamDetailsViewModel;

import java.util.List;
import java.util.Objects;

public class TeamDetailsFragment extends Fragment implements NetworkChangeReceiver.NetworkStateReceiverListener{
    public NetworkChangeReceiver networkStateChangeReceiver;
    private TeamDetailsBinding teamDetailsBinding;
    private TeamDetailsViewModel teamDetailsViewModel;
    private String teamId = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Setting receiver for network state check **/
        networkStateChangeReceiver = NetworkChangeReceiver.getInstance();
        networkStateChangeReceiver.addListener(this);
        Objects.requireNonNull(getActivity()).registerReceiver(networkStateChangeReceiver,
                new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }
    public static TeamDetailsFragment newInstance(String id) {
        TeamDetailsFragment fragment = new TeamDetailsFragment();
        Bundle args = new Bundle();
        args.putString(StringConstants._ID, id);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        teamDetailsBinding = DataBindingUtil.inflate
                (inflater, R.layout.team_details,
                        container, false);
        View teamDetailsView = teamDetailsBinding.getRoot();
        teamDetailsViewModel = new TeamDetailsViewModel(getActivity());
        teamDetailsBinding.setTeamDetailsViewModel(teamDetailsViewModel);
        if (getArguments() != null) {
            teamId = getArguments().getString(StringConstants._ID);
        }
        getTeamDetails();
        return  teamDetailsView;
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
        getTeamDetails();
    }
    private void getTeamDetails() {
        teamDetailsViewModel.getTeamDetailsResponseLiveData(teamId).observe(Objects.requireNonNull(getActivity()), new Observer<TeamResponse>() {
            @Override
            public void onChanged(TeamResponse response) {
                if (response != null && response.getTeams() != null && !response.getTeams().isEmpty() ){
                    Team team = response.getTeams().get(0);
                    teamDetailsBinding.team.setText(team.getStrTeam());
                    if(team.getStrTeamBadge() != null) {
                        Glide.with(Objects.requireNonNull(getActivity()))
                                .load(team.getStrTeamBadge())
                                .into(teamDetailsBinding.dp);
                    }
                    if(team.getStrYoutube() != null) {
                        initHighlightVideo("https://" + team.getStrYoutube());
                    }
                    teamDetailsBinding.description.setText(team.getStrDescriptionEN());
                    callSchedulesApi();
                }
                else
                    teamDetailsBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void callSchedulesApi(){
        teamDetailsViewModel.getNextEvents(teamId).observe(Objects.requireNonNull(getActivity()), new Observer<EventDetailResponse>() {
            @Override
            public void onChanged(EventDetailResponse eventDetailResponse) {
                if (eventDetailResponse != null && eventDetailResponse.getEvent() != null) {
                    List<Event> events = eventDetailResponse.getEvent();
                    for (Event event : events) {
                        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        TextView tv = new TextView(getActivity());
                        tv.setLayoutParams(lparams);
                        tv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.white));
                        tv.setText("\u2022 "+event.getStrFilename());
                        teamDetailsBinding.upcomingEvent.addView(tv);
                    }
                }
            }
        });
        teamDetailsViewModel.getLastEvents(teamId).observe(getActivity(), new Observer<EventResultResponse>() {
            @Override
            public void onChanged(EventResultResponse eventDetailResponse) {
                teamDetailsBinding.progressBar.setVisibility(View.GONE);
                if (eventDetailResponse != null && eventDetailResponse.getEvent() != null) {
                    List<Event> events = eventDetailResponse.getEvent();
                    for (Event event : events) {
                        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        TextView tv = new TextView(getActivity());
                        tv.setLayoutParams(lparams);
                        tv.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.white));
                        tv.setText("\u2022 "+event.getStrFilename());
                        teamDetailsBinding.latestEvent.addView(tv);
                    }
                }
            }
        });

    }
    private void initHighlightVideo(String videoUrl){
            teamDetailsBinding.hightlightVideo.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

            WebSettings webSettings = teamDetailsBinding.hightlightVideo.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            teamDetailsBinding.hightlightVideo.loadUrl(videoUrl);
    }

    @Override
    public void networkUnavailable() {
        NetworkUtility.showInternetDialog(getActivity());
    }
}
