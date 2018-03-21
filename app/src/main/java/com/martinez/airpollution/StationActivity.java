package com.martinez.airpollution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.martinez.airpollution.logic.AirStation;
import com.martinez.airpollution.logic.Property;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationActivity extends AppCompatActivity {

    AirStation airStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        airStation = (AirStation) getIntent().getSerializableExtra("airStation");
        loadData();
    }


    private void loadData(){
        ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(getIntent().getIntExtra("pictureId", -1));
        ((TextView) findViewById(R.id.textViewStationName)).setText(getIntent().getStringExtra("name"));
        ((TextView) findViewById(R.id.textViewAirQuality)).setText(getString(R.string.air_quality)+": " +getAverageAirQuality());

        List<Property> properties = new ArrayList<Property>();
        properties.add(new Property(getString(R.string.date), formatDate(airStation.getFechasolar_utc_())));
        properties.add(new Property(getString(R.string.localization), getString(R.string.localization_message)));
        if(!Double.isNaN(airStation.getTmp()))
            properties.add(new Property(getString(R.string.tmp), airStation.getTmp().toString() +" " +getString(R.string.temperature_unit)));
        if(!Double.isNaN(airStation.getHr()))
            properties.add(new Property(getString(R.string.hr), airStation.getHr().toString() +" " +getString(R.string.humidity_unit)));
        if(!Double.isNaN(airStation.getPrb()))
            properties.add(new Property(getString(R.string.prb), airStation.getPrb().toString() +" " +getString(R.string.pressure_unit)));
        if(!airStation.getPm10().equals("null") && !airStation.getPm25().isEmpty())
            properties.add(new Property(getString(R.string.pm10), airStation.getPm10() +" " +getString(R.string.unit_micro)));
        if(!airStation.getPm25().equals("null") && !airStation.getPm25().isEmpty())
            properties.add(new Property(getString(R.string.pm25), airStation.getPm25() +" " +getString(R.string.unit_micro)));
        if(!Double.isNaN(airStation.getSo2()))
            properties.add(new Property(getString(R.string.so2), airStation.getSo2().toString() +" " +getString(R.string.unit_micro)));
        if(!Double.isNaN(airStation.getNo()))
            properties.add(new Property(getString(R.string.no), airStation.getNo().toString() +" " +getString(R.string.unit_micro)));
        if(!Double.isNaN(airStation.getNo2()))
            properties.add(new Property(getString(R.string.no2), airStation.getNo2().toString() +" " +getString(R.string.unit_micro)));
        if(!Double.isNaN(airStation.getCo()))
            properties.add(new Property(getString(R.string.co), airStation.getCo().toString() +" " +getString(R.string.unit_milli)));
        if(!Double.isNaN(airStation.getO3()))
            properties.add(new Property(getString(R.string.o3), airStation.getO3().toString() +" " +getString(R.string.unit_micro)));
        if(!Double.isNaN(airStation.getBen()))
            properties.add(new Property(getString(R.string.ben), airStation.getBen().toString() +" " +getString(R.string.unit_micro)));
        if(!Double.isNaN(airStation.getTol()))
            properties.add(new Property(getString(R.string.tol), airStation.getTol().toString() +" " +getString(R.string.unit_micro)));
        if(!Double.isNaN(airStation.getMxil()))
            properties.add(new Property(getString(R.string.mxil), airStation.getMxil().toString() +" " +getString(R.string.unit_micro)));

        /*
        //Unknown meaning of this three properties, check Issue#1 in GitHub.
        if(!Double.isNaN(airStation.getRs()))
            properties.add(new Property(getString(R.string.rs), airStation.getRs().toString()));
        if(!Double.isNaN(airStation.getDd()))
            properties.add(new Property(getString(R.string.dd), airStation.getDd().toString()));
        if(!Double.isNaN(airStation.getVv()))
            properties.add(new Property(getString(R.string.vv), airStation.getVv().toString()));
        if(!Double.isNaN(airStation.getLl()))
            properties.add(new Property(getString(R.string.ll), airStation.getLl().toString()));
        */

        ListView listView =  findViewById(R.id.listView);
        listView.setAdapter(new StationAdapter(properties, this));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, final long id) {
                //startActivity(new Intent(this, CompoundActivity).......
            }
        });
    }


    private String formatDate(String date){
        try{
            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);
            String hour = date.substring(11, 13);
            String minute = date.substring(14, 16);
            String second = date.substring(17, 19);

            return day +"/" +month +"/" +year + " " +getString(R.string.text_before_time) +" " +hour +":" +minute +":" +second;
        }catch(IndexOutOfBoundsException e){
            return date;
        }
    }


    private String getAverageAirQuality(){
        return getString(R.string.air_quality_unknown);
    }


}
