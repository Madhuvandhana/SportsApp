package com.example.sportsapp.utils.customcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.sportsapp.utils.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Receiver for network state change is received and all the listeners to this change
 * are notified about the network state change
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    protected List<NetworkStateReceiverListener> listeners;
    private boolean isConnected = true;
    private static NetworkChangeReceiver sInstance;

    /**
     * Get singleton NetworkChangeReceiver instance
     * @return NetworkChangeReceiver instance
     */
    public static NetworkChangeReceiver getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkChangeReceiver();
        }
        return sInstance;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //check network availability before next stuff
            isConnected = NetworkUtility.isInternetConnected(context);
        }
    }

    /**
     * Notify all listener which are in list
     */
    private void notifyStateToAll() {
        for (NetworkStateReceiverListener listener : listeners) {
            notifyState(listener);
        }
    }

    /**
     * Notify the state of network
     * @param listener {@link NetworkStateReceiverListener}
     */
    private void notifyState(NetworkStateReceiverListener listener) {
        if (listener == null) {
            return;
        }

        if (isConnected) {
            listener.networkAvailable();
        } else {
            listener.networkUnavailable();
        }
    }

    /**
     * Adding listener to notify for network fluctuation ,
     *
     * @param l context of the activity or fragment
     */
    public void addListener(NetworkStateReceiverListener l) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(l);
        //notifyState(l);
    }

    /**
     * Removing listener on destroy of activity
     */
    public void removeListener(NetworkStateReceiverListener l) {
        if (listeners != null) {
            listeners.remove(l);
            if (listeners.size() == 0) sInstance = null;
        }
    }

    /**
     * Interface to be implemented for network state change
     */
    public interface NetworkStateReceiverListener {
        void networkAvailable();

        void networkUnavailable();
    }

}
