package com.sser.smartcity.smartcitydata.CategoryListAdapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sser.smartcity.smartcitydata.R;
import com.sser.smartcity.smartcitydata.data.MeteorologicalStation;

import java.util.ArrayList;
import java.util.List;

public class MeteorologicalStationAdapter extends ArrayAdapter<MeteorologicalStation> {

    public MeteorologicalStationAdapter(Activity context, ArrayList<MeteorologicalStation> station) {
        super(context, 0, station);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // For every item in List<MeteorologicalStation> get existing one
        View listItemView = convertView;
        if(listItemView == null) {
            // If it doesn't exist create new one with list_item_category_data layout
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_category_data, parent, false);
        }

        TextView textViewStanica = listItemView.findViewById(R.id.station_name_text_view);
        textViewStanica.setText(R.string.only_meteorological_station_name);

        return listItemView;
    }

}
