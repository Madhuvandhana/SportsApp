package com.example.sportsapp.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.example.sportsapp.R;
import com.example.sportsapp.adapter.EventsListAdapter;
import com.example.sportsapp.adapter.PlayersListAdapter;
import com.example.sportsapp.adapter.TeamListAdapter;
import com.example.sportsapp.databinding.ActivityMainBinding;
import com.example.sportsapp.response.Event;
import com.example.sportsapp.response.EventsResponse;
import com.example.sportsapp.response.Player;
import com.example.sportsapp.response.PlayersResponse;
import com.example.sportsapp.response.Team;
import com.example.sportsapp.response.TeamResponse;
import com.example.sportsapp.utils.NetworkUtility;
import com.example.sportsapp.utils.customcomponents.NetworkChangeReceiver;
import com.example.sportsapp.viewModel.MainViewModel;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener, PurchasesUpdatedListener, NetworkChangeReceiver.NetworkStateReceiverListener, TeamListAdapter.ITeamDetailsInterface,
        EventsListAdapter.IEventDetailsInterface, PlayersListAdapter.IPlayerDetailsInterface {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mBinding;
    MainViewModel mainViewModel;
    private RewardedVideoAd mRewardedVideoAd;
    private BillingClient billingClient;
    private List<String> skuList  = Arrays.asList("test_product_one", "test_product_two");
    private NetworkChangeReceiver mNetworkStateChangeReceiver;
    private String searchQuery = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this, this);
        mBinding.setMainViewModel(mainViewModel);
        mBinding.toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        mBinding.progressBar.setVisibility(View.GONE);
        /*
         * attaching listener for network state change
         */
        mNetworkStateChangeReceiver = NetworkChangeReceiver.getInstance();
        mNetworkStateChangeReceiver.addListener(this);
        registerReceiver(mNetworkStateChangeReceiver,
                new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
       initialiseAds();
        initialization();

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        setupBillingClient();
    }
    private void  setupBillingClient() {
        billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener(this)
                .build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                Log.d(TAG,"Setup Billing Done");
                loadAllSKUs();
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }
    private void loadAllSKUs() {
        if (billingClient.isReady()) {
            SkuDetailsParams.Builder builder = SkuDetailsParams
                    .newBuilder()
                    .setSkusList(skuList)
                    .setType(BillingClient.SkuType.INAPP);
            billingClient.querySkuDetailsAsync(builder.build(),
                    new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(BillingResult result, List<SkuDetails> skuDetailsList) {
                            if (result.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                for (SkuDetails skuDetail : skuDetailsList) {
                                    Log.d(TAG, "IAB SKU detail=" + skuDetail);
                                    if (skuDetail.getSku() == "test_product_two") {
                                        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                                .setSkuDetails(skuDetail)
                                                .build();
                                        billingClient.launchBillingFlow(MainActivity.this, billingFlowParams);
                                    } else
                                        Log.d(TAG, result + "IAB query SKUs");
                                }
                            }
                        }});

        } else {
            Log.d(TAG, "Billing Client not ready");
        }
    }
    private void initialiseAds(){
        MobileAds.initialize(this, "ca-app-pub-6763255045172727~9163033443");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        final Bundle extras = new Bundle();
        ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        String[] publisherIds = {"pub-6763255045172727"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
                if(ConsentInformation.getInstance(MainActivity.this).isRequestLocationInEeaOrUnknown()){
                    if(consentStatus == ConsentStatus.PERSONALIZED){

                    }
                    else if(consentStatus == ConsentStatus.NON_PERSONALIZED){
                        extras.putString("npa", "1");
                    }
                    else{

                    }

                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
        AdRequest adRequest = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
        mBinding.adView.loadAd(adRequest);
        mBinding.adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());
    }

    private void initialization() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            public void onBackStackChanged() {
                Log.i(TAG, "back stack changed ");
                int backCount = getSupportFragmentManager().getBackStackEntryCount();
                if (backCount == 0){
                    mBinding.toolbar.setVisibility(View.VISIBLE);
                }
            }
        });

        mBinding.searchList.setHasFixedSize(true);
        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(NetworkUtility.isInternetConnected(MainActivity.this)) {
                    mBinding.searchList.setVisibility(View.GONE);
                    mBinding.progressBar.setVisibility(View.VISIBLE);
                    searchQuery = query;
                    getSearchList(query);
                    mBinding.searchView.clearFocus();
                }
                else
                    NetworkUtility.showInternetDialog(MainActivity.this);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    private void getSearchList(final String query) {
        mainViewModel.getPlayersResponseLiveData(query, MainActivity.this).observe(this, new Observer<PlayersResponse>() {
            @Override
            public void onChanged(PlayersResponse response) {
                if (response != null && response.getPlayer()!= null) {
                    Log.d(TAG, "response: "+ response.getPlayer());
                    initPlayerList(response);
                }
                else{
                    mainViewModel.getTeamResponseLiveData(query, MainActivity.this).observe(MainActivity.this, new Observer<TeamResponse>() {
                        @Override
                        public void onChanged(TeamResponse response) {
                            if (response != null && response.getTeams() != null) {
                                Log.d(TAG, "response: " + response.getTeams());
                                initTeamList(response);
                            }
                            else{
                                mainViewModel.getEventsResponseLiveData(query, MainActivity.this).observe(MainActivity.this, new Observer<EventsResponse>() {
                                    @Override
                                    public void onChanged(EventsResponse eventsResponse) {
                                        if (eventsResponse != null && eventsResponse.getEvent() != null) {
                                            Log.d(TAG, "response: " + eventsResponse.getEvent());
                                            initEventList(eventsResponse);
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    private void initPlayerList(PlayersResponse response){
        mBinding.progressBar.setVisibility(View.GONE);
        List<Player> players = response.getPlayer();
        PlayersListAdapter adapter = (PlayersListAdapter) mainViewModel.getAdapter();
        adapter.addPlayerList(players);
        mBinding.invalidateAll();
        mBinding.searchList.setVisibility(View.VISIBLE);
    }

    private void initTeamList(TeamResponse response){
        mBinding.progressBar.setVisibility(View.GONE);
        List<Team> teams = response.getTeams();
        TeamListAdapter adapter = (TeamListAdapter) mainViewModel.getAdapter();
        adapter.addTeamList(teams);
        mBinding.invalidateAll();
        mBinding.searchList.setVisibility(View.VISIBLE);
    }
    private void initEventList(EventsResponse response){
        mBinding.progressBar.setVisibility(View.GONE);
        List<Event> events = response.getEvent();
        EventsListAdapter adapter = (EventsListAdapter) mainViewModel.getAdapter();
        adapter.addEventsList(events);
        mBinding.invalidateAll();
        mBinding.searchList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
//        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdOpened() {
//        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
//        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
//        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
//        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
//        Toast.makeText(this, "onRewarded! currency: " + rewardItem.getType() + "  amount: " +
//                rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
//        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
//                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
//        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
//        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        try {
            mNetworkStateChangeReceiver.removeListener(this);
            unregisterReceiver(mNetworkStateChangeReceiver);
        } catch (IllegalArgumentException e) {
            Log.d("unregisterReceiver", e.getMessage());
        }
        super.onDestroy();
    }

    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
            Log.i(TAG, "Purchases updated.");
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user canceling the purchase flow.
            Log.i(TAG, "Purchase canceled.");
        } else {
            // Handle any other error codes.
            Log.i(TAG, "Unknown error. Response code: " + billingResult.getResponseCode());
        }
    }
    void handlePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            // Acknowledge the purchase if it hasn't already been acknowledged.
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
                    @Override
                    public void onAcknowledgePurchaseResponse(BillingResult billingResult) {

                    }
                });
            }
        }
    }

    @Override
    public void networkAvailable() {
        getSearchList(searchQuery);
    }

    @Override
    public void networkUnavailable() {
        NetworkUtility.showInternetDialog(this);
    }

    private void openTeamDetails(String id){
        mBinding.toolbar.setVisibility(View.GONE);
        TeamDetailsFragment teamDetailsFragment = TeamDetailsFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, teamDetailsFragment).addToBackStack(
                null).commit();
    }
    private void openEventDetails(String id){
        mBinding.toolbar.setVisibility(View.GONE);
        EventDetailsFragment eventDetailsFragment = EventDetailsFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, eventDetailsFragment).addToBackStack(
                null).commit();
    }
    private void openPlayerDetails(String id){
        mBinding.toolbar.setVisibility(View.GONE);
        PlayerDetailsFragment playerDetailsFragment = PlayerDetailsFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, playerDetailsFragment).addToBackStack(
                null).commit();
    }

    @Override
    public void onTeamItemClick(String id) {
        openTeamDetails(id);
    }

    @Override
    public void onEventItemClick(String id) {
        openEventDetails(id);
    }

    @Override
    public void onPlayerItemClick(String id) {
        openPlayerDetails(id);
    }
}
