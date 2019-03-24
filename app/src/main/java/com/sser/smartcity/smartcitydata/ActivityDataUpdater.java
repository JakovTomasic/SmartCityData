package com.sser.smartcity.smartcitydata;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sser.smartcity.smartcitydata.activities.CameraActivity;
import com.sser.smartcity.smartcitydata.activities.MeteorologicalStationActivity;
import com.sser.smartcity.smartcitydata.activities.ParkingActivity;
import com.sser.smartcity.smartcitydata.data.AppData;
import com.sser.smartcity.smartcitydata.data.MeteorologicalStation;
import com.sser.smartcity.smartcitydata.networking.NetworkManager;

import java.util.ArrayList;

public class ActivityDataUpdater {

    // TODO: divide this to two methods
    public static void setNetworkStateAndLoadingProgressBar(Activity activity) {
        try {
            ProgressBar loadingDataProgressBar = activity.findViewById(R.id.progress);
            View noInternetConnectionTV = activity.findViewById(R.id.no_internet_connection_TV);

            loadingDataProgressBar.setVisibility(View.GONE);
            noInternetConnectionTV.setVisibility(View.GONE);

            if(NetworkManager.checkNetworkState(activity)) {
                if(AppData.dataNeedsRefresh) loadingDataProgressBar.setVisibility(View.VISIBLE);
            } else { // If not, display error watermark, do not start loading
                noInternetConnectionTV.setVisibility(View.VISIBLE);
            }
        } catch (Exception ignored) {}
    }

    public static void updateActivityData(Activity activity, int stationListIndex) throws Exception {
        setNetworkStateAndLoadingProgressBar(activity);
        setNoDataWarning(activity);

        if(activity instanceof MeteorologicalStationActivity) {
            updateMeteorologicalStationActivityData(activity, stationListIndex);
        } else if(activity instanceof ParkingActivity) {
            updateParkingActivityData(activity, stationListIndex);
        } else if(activity instanceof CameraActivity) {
            updateCameraActivityData(activity, stationListIndex);
        }
    }

    @SuppressLint("SetTextI18n")
    private static void updateMeteorologicalStationActivityData(Activity activity, int stationListIndex) throws Exception {
        TextView airTemperatureTV = activity.findViewById(R.id.air_temperature);
        TextView airHumidityTV = activity.findViewById(R.id.air_humidity);
        TextView airQualityTV = activity.findViewById(R.id.air_quality);
        TextView co2LevelTV = activity.findViewById(R.id.co2_level);
        TextView coLevelTV = activity.findViewById(R.id.co_level);
        TextView dangerousGasesTV = activity.findViewById(R.id.dangerous_gases);

        MeteorologicalStation station = AppData.meteorologicalStations.get(stationListIndex);

        airTemperatureTV.setText(AppData.readResource(R.string.air_temperature, activity) + ": " + station.getAirTemperature() + " °C");
        airHumidityTV.setText(AppData.readResource(R.string.air_humidity, activity) + ": " + station.getAirHumidity());
        airQualityTV.setText(AppData.readResource(R.string.air_quality, activity) + ": " + station.getAirQuality());
        co2LevelTV.setText(AppData.readResource(R.string.co2_level, activity) + ": " + station.getCo2Level());
        coLevelTV.setText(AppData.readResource(R.string.co_level, activity) + ": " + station.getCoLevel());
        dangerousGasesTV.setText(station.getDangerousGases() ? AppData.readResource(R.string.dangerous_gases_detected, activity) :
                AppData.readResource(R.string.dangerous_gases_not_detected, activity));

    }

    private static void updateParkingActivityData(Activity activity, int stationListIndex) throws Exception {
        ArrayList<ImageView> imageViews = new ArrayList<>();

        imageViews.add((ImageView) activity.findViewById(R.id.parking_spot_1));
        imageViews.add((ImageView) activity.findViewById(R.id.parking_spot_2));
        imageViews.add((ImageView) activity.findViewById(R.id.parking_spot_3));

        for(int i = 0; i < imageViews.size(); i++) {
            imageViews.get(i).setBackgroundResource(AppData.parkings.get(stationListIndex).getParkingSpot(i).isAvailable() ?
                    R.drawable.ic_parking_available : R.drawable.ic_parking_not_available);
        }

    }

    private static void updateCameraActivityData(Activity activity, int stationListIndex) throws Exception {
        ListView cameraDataListView = activity.findViewById(R.id.camera_data_list_view);

        cameraDataListView.setAdapter(new CameraDataAdapter(activity, AppData.cameras.get(stationListIndex)));
    }

    public static void setNoDataWarning(Activity activity) {
        try {
            View noDataWarningView = activity.findViewById(R.id.no_data_indicator_layout);

            boolean showWarning = false;

            switch (AppData.lastClickedCategoryTypeIndex) {
                case AppData.meteorologicalStationCategoryTypeIndex:
                    if(AppData.meteorologicalStations.isEmpty()) {
                        showWarning = true;
                    }
                    break;
                case AppData.parkingCategoryTypeIndex:
                    if(AppData.parkings.isEmpty()) {
                        showWarning = true;
                    }
                    break;
                case AppData.cameraCategoryTypeIndex:
                    if(AppData.cameras.isEmpty()) {
                        showWarning = true;
                    }
                    break;
                case AppData.noCategoryTypeDefaultIndex:
                    break;
                default:
                    showWarning = true;
            }

            noDataWarningView.setVisibility(showWarning ? View.VISIBLE : View.GONE);
        } catch (Exception ignored) {}
    }


}
