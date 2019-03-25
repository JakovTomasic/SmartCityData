package com.sser.smartcity.smartcitydata.CategoryListAdapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sser.smartcity.smartcitydata.R;
import com.sser.smartcity.smartcitydata.data.Camera;

import java.util.ArrayList;

// Adapter for list of cameras in the CategoryListActivity
public class CamerasAdapter extends ArrayAdapter<Camera> {

    // Public constructor
    public CamerasAdapter(Activity context, ArrayList<Camera> cameras) {
        super(context, 0, cameras);
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

        // Assign names to evey camera (for now that is just one camera)
        TextView cameraTV = listItemView.findViewById(R.id.station_name_text_view);
        cameraTV.setText(R.string.only_camera_name);

        // Return view
        return listItemView;
    }

}
