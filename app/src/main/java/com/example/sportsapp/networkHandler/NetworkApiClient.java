package com.example.sportsapp.networkHandler;

import com.example.sportsapp.response.EventDetailResponse;
import com.example.sportsapp.response.EventResultResponse;
import com.example.sportsapp.response.EventsResponse;
import com.example.sportsapp.response.PlayerDetailResponse;
import com.example.sportsapp.response.PlayerFormerTeamResponse;
import com.example.sportsapp.response.PlayerHonourResponse;
import com.example.sportsapp.response.PlayersResponse;
import com.example.sportsapp.response.TeamResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetworkApiClient {
    private static final Integer TIMEOUT = 2;
    private static NetworkApiClient instance;
    private  Retrofit retrofit;
    public static NetworkApiClient getInstance() {
        if (instance == null) {
            instance = new NetworkApiClient();
        }
        return instance;
    }

    private NetworkApiClient() {
    }


    public interface APIService {
        @GET("1/searchplayers.php")
        Call<PlayersResponse> getPlayers(
                @Query("p") String name
        );
        @GET("1/searchevents.php")
        Call<EventsResponse> getEvents(
                @Query("e") String name
        );
        @GET("1/searchplayers.php")
        Call<PlayersResponse> getPlayersAndTeam(
                @Query("t") String team,
                @Query("p") String name
        );

        @GET("1/searchteams.php")
        Call<TeamResponse> getTeams(
                @Query("t") String team
        );
        @GET("1/lookupteam.php")
        Call<TeamResponse> getTeamDetails(
                @Query("id") String id
        );
        @GET("1/lookupplayer.php")
        Call<PlayerDetailResponse> getPlayerDetails(
                @Query("id") String id
        );
        @GET("1/lookupevent.php")
        Call<EventDetailResponse> getEventDetails(
                @Query("id") String id
        );
        @GET("1/eventsnext.php")
        Call<EventDetailResponse> getNextEvents(
                @Query("id") String id
        );
        @GET("1/eventslast.php")
        Call<EventResultResponse> getLastEvents(
                @Query("id") String id
        );
        @GET("1/lookuphonors.php")
        Call<PlayerHonourResponse> getHonours(
                @Query("id") String id
        );
        @GET("1/lookupformerteams.php")
        Call<PlayerFormerTeamResponse> getFormerTeams(
                @Query("id") String id
        );

    }


    public APIService getAPIServiceEndPoint() {
        setHttpClientAndBuildRetrofit();
        return retrofit.create(APIService.class);
    }


    private void setHttpClientAndBuildRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(TIMEOUT, TimeUnit.MINUTES);
        httpClient.readTimeout(TIMEOUT, TimeUnit.MINUTES);

        httpClient.addInterceptor(logging);
        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
//                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

}