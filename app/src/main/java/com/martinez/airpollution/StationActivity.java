package com.martinez.airpollution;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.martinez.airpollution.logic.AirStation;
import com.martinez.airpollution.logic.Property;

import java.util.ArrayList;
import java.util.List;

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

        final List<Property> properties = new ArrayList<Property>();
        properties.add(new Property(getString(R.string.date), formatDate(airStation.getFechasolar_utc_())));
        properties.add(new Property(getString(R.string.location), getString(R.string.location_message)));
        if(!Double.isNaN(airStation.getTmp()))
            properties.add(new Property(getString(R.string.tmp), airStation.getTmp().toString() +" " +getString(R.string.temperature_unit)));
        if(!Double.isNaN(airStation.getLl()))
            properties.add(new Property(getString(R.string.ll), airStation.getLl().toString()+" " +getString(R.string.precipitation_unit)));
        if(!Double.isNaN(airStation.getDd()))
            properties.add(new Property(getString(R.string.dd), airStation.getDd().toString()+" " +getString(R.string.wind_direction_unit) +" (" +formatWindDirection(airStation.getDd()) +")"));
        if(!Double.isNaN(airStation.getVv()))
            properties.add(new Property(getString(R.string.vv), airStation.getVv().toString()+" " +getString(R.string.wind_speed_unit)));
        if(!Double.isNaN(airStation.getRs()))
            properties.add(new Property(getString(R.string.rs), airStation.getRs().toString()+" " +getString(R.string.solar_radiation_unit)));
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


        final ListView listView =  findViewById(R.id.listView);
        listView.setAdapter(new StationAdapter(properties, this));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, final long id) {
                Property property = (Property) listView.getItemAtPosition(position);

                if(property.getId().equals(getString(R.string.location))){
                    String str = "geo:" +airStation.getLatitud() +", " +airStation.getLongitud()
                            +"?q=" +airStation.getLatitud() +", " +airStation.getLongitud() +"(" +airStation.getTitulo() +")";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str));
                    view.getContext().startActivity(intent);
                }
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

            return day +"/" +month +"/" +year + " " +getString(R.string.text_before_time) +" " +hour +":" +minute;
        }catch(IndexOutOfBoundsException e){
            return date;
        }
    }

    private String formatWindDirection(Double degrees){
        if(degrees >= 360 || degrees < 0) return "";

        if(degrees >= 338 || degrees <= 22) return getString(R.string.north);
        if(degrees >= 23 && degrees <= 67) return getString(R.string.north_east);
        if(degrees >= 68 && degrees <= 112) return getString(R.string.east);
        if(degrees >= 113 && degrees <= 157) return getString(R.string.south_east);
        if(degrees >= 158 && degrees <= 202) return getString(R.string.south);
        if(degrees >= 203 && degrees <= 247) return getString(R.string.south_west);
        if(degrees >= 248 && degrees <= 292) return getString(R.string.west);
        if(degrees >= 293 && degrees <= 337) return getString(R.string.north_west);

        return "";
    }


    private String getAverageAirQuality(){
        return getString(R.string.air_quality_unknown);
    }


}
