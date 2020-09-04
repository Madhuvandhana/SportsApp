package com.example.sportsapp.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsapp.BR;
import com.example.sportsapp.adapter.EventsListAdapter;
import com.example.sportsapp.adapter.PlayersListAdapter;
import com.example.sportsapp.adapter.TeamListAdapter;
import com.example.sportsapp.repository.SportsRepository;
import com.example.sportsapp.response.EventsResponse;
import com.example.sportsapp.response.PlayersResponse;
import com.example.sportsapp.response.TeamResponse;
import com.google.android.gms.common.api.Response;


public class MainViewModel extends BaseObservable {
    private SportsRepository sportsRepository;
    private LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Context context;
    public MainViewModel(Context context, PlayersListAdapter.IPlayerDetailsInterface playerDetailsInterface) {
        sportsRepository = new SportsRepository(context);
        layoutManager = new LinearLayoutManager(context);
        this.context = context;
    }

    @BindingAdapter({"bindAdapter"})
    public static void setAdapter(RecyclerView recyclerView,
                                        RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
    @BindingAdapter({"linearLayoutManager"})
    public static void setLinearLayoutManager(RecyclerView recyclerView,
                                              LinearLayoutManager linearLayoutManager) {
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }
    public void setAdapter(RecyclerView.Adapter adapterV){
        adapter = adapterV;
        notifyPropertyChanged(BR.adapter);
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return layoutManager;
    }

    public LiveData<PlayersResponse> getPlayersResponseLiveData(String query, PlayersListAdapter.IPlayerDetailsInterface playerDetailsInterface) {
        LiveData<PlayersResponse> playersResponseLiveData = sportsRepository.getPlayers(query);
        setAdapter(new PlayersListAdapter(context, playerDetailsInterface));
        return playersResponseLiveData;
    }
    public LiveData<TeamResponse> getTeamResponseLiveData(String query, TeamListAdapter.ITeamDetailsInterface iTeamDetailsInterface) {
        LiveData<TeamResponse> teamResponseLiveData = sportsRepository.getTeams(query);
        setAdapter(new TeamListAdapter(context, iTeamDetailsInterface));
        return teamResponseLiveData;
    }
    public LiveData<EventsResponse> getEventsResponseLiveData(String query, EventsListAdapter.IEventDetailsInterface eventDetailsInterface) {
        LiveData<EventsResponse> eventsResponseLiveData = sportsRepository.getEvents(query);
        setAdapter(new EventsListAdapter(context, eventDetailsInterface));
        return eventsResponseLiveData;
    }
}
