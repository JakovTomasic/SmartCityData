package com.sser.smartcity.smartcitydata;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sser.smartcity.smartcitydata.data.AppData;
import com.sser.smartcity.smartcitydata.data.Camera;


public class CameraDataAdapter extends ArrayAdapter<String> {

    private Camera camera;

    CameraDataAdapter(Activity context, Camera camera) {
        super(context, 0, camera.getAllPlates());
        this.camera = camera;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // For every item in List<MeteorologicalStation> get existing one
        View listItemView = convertView;
        if(listItemView == null) {
            // If it doesn't exist create new one with list_item_category_data layout
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_camera_data, parent, false);
        }

        TextView carTimeTV = listItemView.findViewById(R.id.car_time_text_view);
        TextView carPlateTV = listItemView.findViewById(R.id.car_plate_text_view);

        carTimeTV.setText(AppData.readResource(R.string.time, getContext()) + ": " + camera.getTime(position));
        carPlateTV.setText(AppData.readResource(R.string.car_plate, getContext()) + ": " + camera.getPlate(position));

        return listItemView;
    }

}
