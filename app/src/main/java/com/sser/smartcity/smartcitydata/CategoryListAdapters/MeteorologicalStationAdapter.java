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

// Adapter for list of meteorological stations in the CategoryListActivity
public class MeteorologicalStationAdapter extends ArrayAdapter<MeteorologicalStation> {

    // Public constructor
    public MeteorologicalStationAdapter(Activity context, ArrayList<MeteorologicalStation> station) {
        super(context, 0, station);
    }

    // This is called on creation of every list item (old list item can be reused in this process)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Try to recycle old view
        View listItemView = convertView;
        if(listItemView == null) {
            // If it doesn't exist create new one with list_item_category_data layout
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_category_data, parent, false);
        }

        // Assign names to evey met station (for now there is only one name for it, assign it to all)
        TextView metStationTV = listItemView.findViewById(R.id.station_name_text_view);
        metStationTV.setText(R.string.only_meteorological_station_name);

        // Return view
        return listItemView;
    }

}
