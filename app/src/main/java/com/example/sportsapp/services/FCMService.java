package com.example.sportsapp.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.sportsapp.R;
import com.example.sportsapp.utils.StringConstants;
import com.example.sportsapp.view.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;
import java.util.Map;
import java.util.logging.Logger;

public class FCMService extends FirebaseMessagingService {
    private static final String ADMIN_CHANNEL_ID = "turvochannelId";
    private NotificationManager notificationManager;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("NEW_TOKEN",s);
    }


    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("notification", remoteMessage.getNotification().getBody());

        String title = "";
        String message = "";
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        if(notification != null){
            title = notification.getTitle();
            message = notification.getBody();
            sendNotification(message, title);
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Map<String,String> data = remoteMessage.getData();
            if (message != null && message.isEmpty())
                message = data.get(StringConstants.GCM_MESSAGE);
            if(message != null && message.isEmpty())
                message = data.get(StringConstants.GCM_NOTIFICATIONBODY);
            if (title != null && title.isEmpty())
                title = data.get(StringConstants.GCM_TITLE);

//            if (!AppUtils.isAppIsInBackground()) {
//
//            }
            sendNotification(message, title);

        }

    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message, String title) {


        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction("android.intent.action.VIEW");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        long when = Calendar.getInstance().getTimeInMillis();
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //Setting up Notification channels for android O and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels();
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setWhen(when)
                .setLargeIcon(bm)
                .setContentTitle(title).setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
//        if (!AppUtils.isAppIsInBackground()) {
//            notificationBuilder.setPriority(Notification.PRIORITY_HIGH)
//                    .setFullScreenIntent(pendingIntent, true);
//        }

        if (notificationManager != null)
            notificationManager.notify((int) when, notificationBuilder.build());
    }


    /**
     * Set up notification channel for Push notification
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels() {
        CharSequence adminChannelName = "TurvoPushNotification";
        String adminChannelDescription = "Foreground push notification channel";
        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }

    }
}
