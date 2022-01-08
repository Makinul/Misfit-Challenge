package tech.misfit.challenge.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import tech.misfit.challenge.base.MyApplication;

public class NetworkConnection {
    public static final String TAG = "NetworkConnection";

    private static final NetworkConnection instance = new NetworkConnection();

    private NetworkConnection() {
    }

    public static NetworkConnection getInstance() {
        return instance;
    }

    private NetworkInfo getNetworkInfo() {
        final ConnectivityManager connMgr = (ConnectivityManager) MyApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connMgr.getActiveNetworkInfo();
    }

    public boolean isConnected() {
        final NetworkInfo info = getNetworkInfo();
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }

    public boolean isWifiDataAvailable() {
        final NetworkInfo info = getNetworkInfo();
        if (info != null && info.isConnected()) {
            return info.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    public boolean isMobileDataAvailable() {
        final NetworkInfo info = getNetworkInfo();
        if (info != null && info.isConnected()) {
            return info.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    public boolean isAvailable() {
        String command = "ping -c 1 google.com";
        try {
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
