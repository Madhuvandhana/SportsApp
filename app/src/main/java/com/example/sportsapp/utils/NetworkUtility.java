package com.example.sportsapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import androidx.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.sportsapp.R;

public class NetworkUtility {
    private static MaterialDialog sInternetAlertDialog;
    /**
     * Checks for internet connection
     *
     * @return True if internet exists otherwise false
     */
    public static boolean isInternetConnected(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = null;
            if (cm != null) netInfo = cm.getAllNetworkInfo();

            if (netInfo != null) {
                for (NetworkInfo networkInfo : netInfo) {

                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        if (networkInfo.isConnected()) {
                            return true;
                        }
                    }

                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIMAX) // WIMAX//
                    // means//
                    // 4G
                    {
                        if (networkInfo.isConnected()) {
                            return true;
                        }
                    }

                    if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        if (networkInfo.isConnected()) {
                            return true;
                        }
                    }
                    if ( networkInfo.isAvailable() && networkInfo.isConnected() ) {
                        return true;
                    }

                }
            }
        }

        return false;

    }


    /**
     *
     * @param context Current context
     */
    public static void showInternetDialog(final Context context) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            final int internetSettingRequestCode = 1;
            if (sInternetAlertDialog != null && sInternetAlertDialog.isShowing()) {
                sInternetAlertDialog.cancel();
            }
            if (sInternetAlertDialog == null || !sInternetAlertDialog.isShowing()) {
                if (!((Activity) context).isFinishing()) {
                    sInternetAlertDialog = new MaterialDialog.Builder(context)
                            .title("No internet Available")
                            .content(context.getString(R.string.internet_error_message))
                            .positiveText("Settings")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog,
                                                    @NonNull DialogAction which) {
                                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                    ((Activity) context).startActivityForResult(intent,
                                            internetSettingRequestCode);
                                }
                            })
                            .negativeText("Cancel")
                            .cancelable(false)
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog materialDialog,
                                                    @NonNull DialogAction dialogAction) {
                                    materialDialog.cancel();
                                }
                            }).show();
                }
            }
        }
    }


    public static void cancelInternetDialog(Context context) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            if (sInternetAlertDialog != null && sInternetAlertDialog.isShowing()) {
                sInternetAlertDialog.cancel();
            }
        }
    }
}
