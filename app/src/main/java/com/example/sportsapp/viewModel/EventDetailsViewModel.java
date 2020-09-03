package com.example.sportsapp.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;

import com.example.sportsapp.repository.SportsRepository;
import com.example.sportsapp.response.EventDetailResponse;

public class EventDetailsViewModel  extends BaseObservable {
    private Context context;
    private SportsRepository sportsRepository;

    public EventDetailsViewModel(Context context) {
        this.context = context;
        sportsRepository = new SportsRepository(context);
    }

    public LiveData<EventDetailResponse> getEventDetailsResponseLiveData(String query) {
        LiveData<EventDetailResponse> eventsResponseLiveData = sportsRepository.getEventDetails(query);
        return eventsResponseLiveData;
    }
}
