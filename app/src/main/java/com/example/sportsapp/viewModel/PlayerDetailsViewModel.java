package com.example.sportsapp.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;

import com.example.sportsapp.repository.SportsRepository;
import com.example.sportsapp.response.PlayerDetailResponse;
import com.example.sportsapp.response.PlayerFormerTeamResponse;
import com.example.sportsapp.response.PlayerHonourResponse;

public class PlayerDetailsViewModel extends BaseObservable {
    private Context context;
    private SportsRepository sportsRepository;

    public PlayerDetailsViewModel(Context context) {
        this.context = context;
        sportsRepository = new SportsRepository(context);
    }

    public LiveData<PlayerDetailResponse> getPlayerDetailsResponseLiveData(String query) {
        LiveData<PlayerDetailResponse> playersResponseLiveData = sportsRepository.getPlayerDetails(query);
        return playersResponseLiveData;
    }
    public LiveData<PlayerHonourResponse> getHonours(String query) {
        LiveData<PlayerHonourResponse> playerHonourResponseLiveData = sportsRepository.getHonours(query);
        return playerHonourResponseLiveData;
    }
    public LiveData<PlayerFormerTeamResponse> getFormerTeam(String query) {
        LiveData<PlayerFormerTeamResponse> playerFormerTeamResponseLiveData = sportsRepository.getFormerTeam(query);
        return playerFormerTeamResponseLiveData;
    }
}
