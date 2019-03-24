package com.sser.smartcity.smartcitydata.networking;

import android.app.Activity;
import android.util.Log;

import com.sser.smartcity.smartcitydata.ActivityDataUpdater;
import com.sser.smartcity.smartcitydata.data.AppData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateDataHandler {

    private static final String LOG_TAG = UpdateDataHandler.class.getName();


    private static Thread updateThread;
    private static boolean isUpdaterLooping = false;

    public static void updateData(final Activity activity) {
        for(int channel = 1; channel <= AppData.numberOfThingSpeakChannels; channel++) {
            // Create URL object
            URL url = null;

            try {
                switch (channel) {
                    case 1:
                        url = new URL(AppData.metStationRequestUrl);
                        break;
                    case 2:
                        url = new URL(AppData.parkingRequestUrl);
                        break;
                    case 3:
                        url = new URL(AppData.cameraRequestUrl);
                        break;
                    default:
                        Log.e(LOG_TAG, "Channel could not be handled");
                }
            }
            catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Problem building the URL ", e);
            }

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = null;

            try {
                jsonResponse = JsonHandler.makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }

            // "convert" JSON to data and save it
            JsonHandler.extractFeatureFromJson(jsonResponse, channel);
        }

        AppData.dataNeedsRefresh = false;

        try {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ActivityDataUpdater.setNoDataWarning(activity);
                    try {
                        ActivityDataUpdater.updateActivityData(activity, AppData.lastClickedStationListIndex);
                    } catch (Exception ignored) {}
                }
            });
        } catch (Exception ignored) {}

    }

    public static void startUpdatingData(final Activity activity) {
        if(isUpdaterLooping) {
            return;
        }

        try {
            updateThread.interrupt();
            updateThread = null;
        } catch (Exception ignored) {}

        updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    isUpdaterLooping = true;
                    while(true) {
                        updateData(activity);

                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            isUpdaterLooping = false;
                            return;
                        }
                    }
                } catch (Exception ignored) {}
                isUpdaterLooping = false;
            }
        });
        updateThread.start();
    }



}
