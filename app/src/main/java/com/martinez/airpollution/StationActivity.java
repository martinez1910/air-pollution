package com.martinez.airpollution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.martinez.airpollution.airsations.AirStation;

public class StationActivity extends AppCompatActivity {

    AirStation airStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        airStation = (AirStation) getIntent().getSerializableExtra("airStation");
        ((TextView) findViewById(R.id.textViewStationActivity)).setText(airStation.toString());
    }
}
