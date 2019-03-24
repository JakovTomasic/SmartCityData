package com.sser.smartcity.smartcitydata.data;

import android.content.Context;
import android.view.View;

import com.sser.smartcity.smartcitydata.networking.JsonHandler;

import java.util.ArrayList;

public class AppData {

    public static final int noCategoryTypeDefaultIndex = 0;
    public static final int meteorologicalStationCategoryTypeIndex = 1;
    public static final int streetLightCategoryTypeIndex = 2;
    public static final int cameraCategoryTypeIndex = 3;
    public static final int parkingCategoryTypeIndex = 4;
    public static final int parkingTicketCategoryTypeIndex = 5;

    public static int lastClickedCategoryTypeIndex = noCategoryTypeDefaultIndex;


    public static final int numberOfThingSpeakChannels = 3;


    private static final String JSON_REQUEST_MET_STATION = "https://api.thingspeak.com/channels/196696/feeds.json?api_key=E5V4C2KMUN5S4BWM";
    private static final String JSON_REQUEST_PARKING = "https://api.thingspeak.com/channels/629316/feeds.json?api_key=YKPQUEH22XA0MPVE";
    private static final String JSON_REQUEST_CAMERA = "https://api.thingspeak.com/channels/736965/feeds.json?api_key=XGPRBTB6ZGWVKOQM";

    public static final String metStationRequestUrl, parkingRequestUrl, cameraRequestUrl;

    static {
        // add query parameters in string requests
        metStationRequestUrl = JsonHandler.makeURL(JSON_REQUEST_MET_STATION, true);
        parkingRequestUrl = JsonHandler.makeURL(JSON_REQUEST_PARKING, true);
        cameraRequestUrl = JsonHandler.makeURL(JSON_REQUEST_CAMERA, false);
    }


    public static ArrayList<MeteorologicalStation> meteorologicalStations = new ArrayList<>();
    public static ArrayList<Camera> cameras = new ArrayList<>();
    public static ArrayList<Parking> parkings = new ArrayList<>();


    public static boolean dataNeedsRefresh = true;

    public static int lastClickedStationListIndex = -1;


    // Read string from resources
    public static String readResource(int id, Context context) {
        if(context == null) {
            return "";
        }
        return context.getResources().getString(id);
    }
}
