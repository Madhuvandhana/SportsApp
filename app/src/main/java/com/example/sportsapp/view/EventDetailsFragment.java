package com.example.sportsapp.view;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.sportsapp.R;
import com.example.sportsapp.databinding.EventDetailsBinding;
import com.example.sportsapp.response.Event;
import com.example.sportsapp.response.EventDetailResponse;
import com.example.sportsapp.utils.NetworkUtility;
import com.example.sportsapp.utils.StringConstants;
import com.example.sportsapp.utils.customcomponents.NetworkChangeReceiver;
import com.example.sportsapp.viewModel.EventDetailsViewModel;

import java.util.Objects;

public class EventDetailsFragment extends Fragment implements NetworkChangeReceiver.NetworkStateReceiverListener {
        public NetworkChangeReceiver networkStateChangeReceiver;
        private EventDetailsBinding eventDetailsBinding;
        private EventDetailsViewModel eventDetailsViewModel;
        private String eventId = "";
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            /*Setting receiver for network state check **/
            networkStateChangeReceiver = NetworkChangeReceiver.getInstance();
            networkStateChangeReceiver.addListener(this);
            Objects.requireNonNull(getActivity()).registerReceiver(networkStateChangeReceiver,
                    new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
        }
        public static EventDetailsFragment newInstance(String id) {
            EventDetailsFragment fragment = new EventDetailsFragment();
            Bundle args = new Bundle();
            args.putString(StringConstants._ID, id);
            fragment.setArguments(args);
            return fragment;
        }
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            eventDetailsBinding = DataBindingUtil.inflate
                    (inflater, R.layout.event_details,
                            container, false);
            View eventDetailsView = eventDetailsBinding.getRoot();
            eventDetailsViewModel = new EventDetailsViewModel(getActivity());
            eventDetailsBinding.setEventDetailsViewModel(eventDetailsViewModel);
            if (getArguments() != null) {
                eventId = getArguments().getString(StringConstants._ID);
            }
            getEventDetails();
            return  eventDetailsView;
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
            getEventDetails();
        }
        private void getEventDetails() {
            eventDetailsViewModel.getEventDetailsResponseLiveData(eventId).observe(Objects.requireNonNull(getActivity()), new Observer<EventDetailResponse>() {
                @Override
                public void onChanged(EventDetailResponse response) {
                    if (response != null && response.getEvent() != null && !response.getEvent().isEmpty() ){
                        Event event = response.getEvent().get(0);
                        eventDetailsBinding.progressBar.setVisibility(View.GONE);
                        eventDetailsBinding.event.setText(event.getStrFilename());
                        if(event.getStrThumb() != null) {
                            Glide.with(Objects.requireNonNull(getActivity()))
                                    .load(event.getStrThumb())
                                    .into(eventDetailsBinding.dp);
                        }
                        if(event.getStrVideo() != null) {
                            initHighlightVideo("https://" + event.getStrVideo());
                        }
                        eventDetailsBinding.description.setText(event.getStrDescriptionEN());


                    }
                    else
                        eventDetailsBinding.progressBar.setVisibility(View.GONE);
                }
            });
        }
        private void initHighlightVideo(String videoUrl){
                eventDetailsBinding.hightlightVideo.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });

                WebSettings webSettings = eventDetailsBinding.hightlightVideo.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setUseWideViewPort(true);
                eventDetailsBinding.hightlightVideo.loadUrl(videoUrl);
        }

        @Override
        public void networkUnavailable() {
            NetworkUtility.showInternetDialog(getActivity());
        }
}
