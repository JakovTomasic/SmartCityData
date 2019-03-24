package com.sser.smartcity.smartcitydata.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sser.smartcity.smartcitydata.ActivityDataUpdater;
import com.sser.smartcity.smartcitydata.R;
import com.sser.smartcity.smartcitydata.data.AppData;
import com.sser.smartcity.smartcitydata.networking.UpdateDataHandler;

public class ParkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        setTitle(R.string.only_parking_name);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception ignored) {}
    }

    @Override
    protected void onResume() {
        super.onResume();


        try {
            ActivityDataUpdater.updateActivityData(this, AppData.lastClickedStationListIndex);
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
                        UpdateDataHandler.updateData(ParkingActivity.this);
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
