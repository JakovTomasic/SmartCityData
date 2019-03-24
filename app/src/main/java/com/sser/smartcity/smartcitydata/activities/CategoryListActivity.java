package com.sser.smartcity.smartcitydata.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sser.smartcity.smartcitydata.ActivityDataUpdater;
import com.sser.smartcity.smartcitydata.CategoryListAdapters.CamerasAdapter;
import com.sser.smartcity.smartcitydata.CategoryListAdapters.MeteorologicalStationAdapter;
import com.sser.smartcity.smartcitydata.CategoryListAdapters.ParkingAdapter;
import com.sser.smartcity.smartcitydata.R;
import com.sser.smartcity.smartcitydata.data.AppData;
import com.sser.smartcity.smartcitydata.networking.UpdateDataHandler;

public class CategoryListActivity extends AppCompatActivity {

    private static final String LOG_TAG = CategoryListActivity.class.getName();

    ListView categoryDataListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryDataListView = findViewById(R.id.category_list_view);

        switch (AppData.lastClickedCategoryTypeIndex) {
            case AppData.meteorologicalStationCategoryTypeIndex:
                setTitle(R.string.meteorological_station);

                categoryDataListView.setAdapter(new MeteorologicalStationAdapter(this, AppData.meteorologicalStations));

                categoryDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        AppData.lastClickedStationListIndex = i;

                        Intent intent = new Intent(CategoryListActivity.this, MeteorologicalStationActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case AppData.streetLightCategoryTypeIndex:
                setTitle(R.string.street_light);
                break;
            case AppData.cameraCategoryTypeIndex:
                setTitle(R.string.camera);

                categoryDataListView.setAdapter(new CamerasAdapter(this, AppData.cameras));

                categoryDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        AppData.lastClickedStationListIndex = i;

                        Intent intent = new Intent(CategoryListActivity.this, CameraActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case AppData.parkingCategoryTypeIndex:
                setTitle(R.string.parking);

                categoryDataListView.setAdapter(new ParkingAdapter(this, AppData.parkings));

                categoryDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        AppData.lastClickedStationListIndex = i;

                        Intent intent = new Intent(CategoryListActivity.this, ParkingActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case AppData.parkingTicketCategoryTypeIndex:
                setTitle(R.string.parking_ticket);
                break;
            default:
                Log.e(LOG_TAG, "Data list category not valid");
        }

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception ignored) {}

    }

    @Override
    protected void onResume() {
        super.onResume();


        ActivityDataUpdater.setNetworkStateAndLoadingProgressBar(this);

        try {
            ActivityDataUpdater.updateActivityData(this, -1);
        } catch (Exception ignored) {}

        UpdateDataHandler.startUpdatingData(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create menu base on main_menu layout
        getMenuInflater().inflate(R.menu.refresh_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_option:
                View loadingProgressBar = findViewById(R.id.progress);
                loadingProgressBar.setVisibility(View.VISIBLE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UpdateDataHandler.updateData(CategoryListActivity.this);
                    }
                }).start();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
