package com.example.sportsapp.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sportsapp.networkHandler.NetworkApiClient;
import com.example.sportsapp.response.EventDetailResponse;
import com.example.sportsapp.response.EventResultResponse;
import com.example.sportsapp.response.EventsResponse;
import com.example.sportsapp.response.PlayerDetailResponse;
import com.example.sportsapp.response.PlayerFormerTeamResponse;
import com.example.sportsapp.response.PlayerHonourResponse;
import com.example.sportsapp.response.PlayersResponse;
import com.example.sportsapp.response.TeamResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsRepository {
    private static final String TAG = SportsRepository.class.getSimpleName();
    private NetworkApiClient.APIService apiService;
    private Context context;

    public SportsRepository(Context context) {
        apiService = NetworkApiClient.getInstance().getAPIServiceEndPoint();
        this.context = context;
    }
    public LiveData<PlayersResponse> getPlayers(final String value) {
        final MutableLiveData<PlayersResponse> data = new MutableLiveData<>();
            apiService.getPlayers(value)
                    .enqueue(new Callback<PlayersResponse>() {
                        @Override
                        public void onResponse(Call<PlayersResponse> call, Response<PlayersResponse> response) {
                            Log.d(TAG, "onResponse response:: " + response);
                            if (response.body() != null) {
                                data.setValue(response.body());
                            }
                            else{
                                data.setValue(null);
                            }
                        }
                        @Override
                        public void onFailure(Call<PlayersResponse> call, Throwable t) {
                            data.setValue(null);
                            Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                        }
                    });
            return data;
    }
    public LiveData<TeamResponse> getTeams(final String value) {
        final MutableLiveData<TeamResponse> data = new MutableLiveData<>();
        apiService.getTeams(value)
                .enqueue(new Callback<TeamResponse>() {
                    @Override
                    public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                        else{
                            data.setValue(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<TeamResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }
    public LiveData<EventsResponse> getEvents(String value) {
        final MutableLiveData<EventsResponse> data = new MutableLiveData<>();
        apiService.getEvents(value)
                .enqueue(new Callback<EventsResponse>() {
                    @Override
                    public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<EventsResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }
    public LiveData<TeamResponse> getTeamDetails(String id) {
        final MutableLiveData<TeamResponse> data = new MutableLiveData<>();
        apiService.getTeamDetails(id)
                .enqueue(new Callback<TeamResponse>() {
                    @Override
                    public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<TeamResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }

    public LiveData<PlayerDetailResponse> getPlayerDetails(String id) {
        final MutableLiveData<PlayerDetailResponse> data = new MutableLiveData<>();
        apiService.getPlayerDetails(id)
                .enqueue(new Callback<PlayerDetailResponse>() {
                    @Override
                    public void onResponse(Call<PlayerDetailResponse> call, Response<PlayerDetailResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<PlayerDetailResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }

    public LiveData<EventDetailResponse> getEventDetails(String id) {
        final MutableLiveData<EventDetailResponse> data = new MutableLiveData<>();
        apiService.getEventDetails(id)
                .enqueue(new Callback<EventDetailResponse>() {
                    @Override
                    public void onResponse(Call<EventDetailResponse> call, Response<EventDetailResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<EventDetailResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }
    public LiveData<EventDetailResponse> getNextEvents(String id) {
        final MutableLiveData<EventDetailResponse> data = new MutableLiveData<>();
        apiService.getNextEvents(id)
                .enqueue(new Callback<EventDetailResponse>() {
                    @Override
                    public void onResponse(Call<EventDetailResponse> call, Response<EventDetailResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<EventDetailResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }
    public LiveData<EventResultResponse> getLastEvents(String id) {
        final MutableLiveData<EventResultResponse> data = new MutableLiveData<>();
        apiService.getLastEvents(id)
                .enqueue(new Callback<EventResultResponse>() {
                    @Override
                    public void onResponse(Call<EventResultResponse> call, Response<EventResultResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<EventResultResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }
    public LiveData<PlayerHonourResponse> getHonours(String id) {
        final MutableLiveData<PlayerHonourResponse> data = new MutableLiveData<>();
        apiService.getHonours(id)
                .enqueue(new Callback<PlayerHonourResponse>() {
                    @Override
                    public void onResponse(Call<PlayerHonourResponse> call, Response<PlayerHonourResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<PlayerHonourResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }
    public LiveData<PlayerFormerTeamResponse> getFormerTeam(String id) {
        final MutableLiveData<PlayerFormerTeamResponse> data = new MutableLiveData<>();
        apiService.getFormerTeams(id)
                .enqueue(new Callback<PlayerFormerTeamResponse>() {
                    @Override
                    public void onResponse(Call<PlayerFormerTeamResponse> call, Response<PlayerFormerTeamResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<PlayerFormerTeamResponse> call, Throwable t) {
                        data.setValue(null);
                        Toast.makeText(context,"Error Occured", Toast.LENGTH_LONG).show();
                    }
                });
        return data;
    }

}
