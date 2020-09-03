package com.example.sportsapp.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;

import com.example.sportsapp.repository.SportsRepository;
import com.example.sportsapp.response.EventDetailResponse;
import com.example.sportsapp.response.EventResultResponse;
import com.example.sportsapp.response.TeamResponse;


public class TeamDetailsViewModel extends BaseObservable {
    private Context context;
    private SportsRepository sportsRepository;
    public TeamDetailsViewModel(Context context) {
        this.context = context;
        sportsRepository = new SportsRepository(context);
    }
    public LiveData<TeamResponse> getTeamDetailsResponseLiveData(String query) {
        LiveData<TeamResponse> teamResponseLiveData = sportsRepository.getTeamDetails(query);
        return teamResponseLiveData;
    }
    public LiveData<EventDetailResponse> getNextEvents(String query) {
        LiveData<EventDetailResponse> eventDetailResponseLiveData = sportsRepository.getNextEvents(query);
        return eventDetailResponseLiveData;
    }
    public LiveData<EventResultResponse> getLastEvents(String query) {
        LiveData<EventResultResponse> eventDetailResponseLiveData = sportsRepository.getLastEvents(query);
        return eventDetailResponseLiveData;
    }
}
