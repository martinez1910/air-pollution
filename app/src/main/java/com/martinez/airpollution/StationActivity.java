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
        /*switch(airStation.getEstacion()){
            case 1:
                ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.ic_station_avda_constitucion);
                ((TextView) findViewById(R.id.textViewStationName)).setText(getString(R.string.station_avenida_constitucion));
                break;
            case 2:
                ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.ic_station_avda_argentina);
                ((TextView) findViewById(R.id.textViewStationName)).setText(getString(R.string.station_avenida_constitucion));
                break;
            case 10:
                ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.ic_station_montevil);
                ((TextView) findViewById(R.id.textViewStationName)).setText(getString(R.string.station_avenida_constitucion));
                break;
            case 3:
                ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.ic_station_hermanos_felgueroso);
                ((TextView) findViewById(R.id.textViewStationName)).setText(getString(R.string.station_avenida_constitucion));
                break;
            case 4:
                ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.ic_station_avda_castilla);
                ((TextView) findViewById(R.id.textViewStationName)).setText(getString(R.string.station_avenida_constitucion));
                break;
            case 11:
                ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(R.drawable.ic_station_santa_barbara);
                ((TextView) findViewById(R.id.textViewStationName)).setText(getString(R.string.station_avenida_constitucion));
                break;
        }*/
        ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(getIntent().getIntExtra("pictureId", -1));
        ((TextView) findViewById(R.id.textViewStationName)).setText(getIntent().getStringExtra("name"));
        ((TextView) findViewById(R.id.textViewAirQuality)).setText(getString(R.string.air_quality)+": " +getAverageAirQuality());

        List<Property> properties = new ArrayList<Property>();
        properties.add(new Property(getString(R.string.date), formatDate(airStation.getFechasolar_utc_())));
        properties.add(new Property(getString(R.string.localization), getString(R.string.localization_message)));
        if(airStation.getTmp() != null)
            properties.add(new Property(getString(R.string.tmp), airStation.getTmp().toString()));
        if(airStation.getHr() != null)
            properties.add(new Property(getString(R.string.hr), airStation.getHr().toString()));
        if(airStation.getPrb() != null)
            properties.add(new Property(getString(R.string.prb), airStation.getPrb().toString()));
        if(airStation.getPm10() != null)
            properties.add(new Property(getString(R.string.pm10), airStation.getPm10()));
        if(airStation.getPm25() != null)
            properties.add(new Property(getString(R.string.pm25), airStation.getPm25()));
        if(airStation.getSo2() != null)
            properties.add(new Property(getString(R.string.so2), airStation.getSo2().toString()));
        if(airStation.getNo() != null)
            properties.add(new Property(getString(R.string.no), airStation.getNo().toString()));
        if(airStation.getNo2() != null)
            properties.add(new Property(getString(R.string.no2), airStation.getNo2().toString()));
        if(airStation.getCo() != null)
            properties.add(new Property(getString(R.string.co), airStation.getCo().toString()));
        if(airStation.getO3() != null)
            properties.add(new Property(getString(R.string.o3), airStation.getO3().toString()));
        if(airStation.getRs() != null)
            properties.add(new Property(getString(R.string.rs), airStation.getRs().toString()));
        if(airStation.getBen() != null)
            properties.add(new Property(getString(R.string.ben), airStation.getBen().toString()));
        if(airStation.getTol() != null)
            properties.add(new Property(getString(R.string.tol), airStation.getTol().toString()));
        if(airStation.getMxil() != null)
            properties.add(new Property(getString(R.string.mxil), airStation.getMxil().toString()));

        /*Unknown meaning of this three properties, check Issue#1 in GitHub.
        properties.add(new Property(getString(R.string.dd), airStation.getDd().toString()));
        properties.add(new Property(getString(R.string.vv), airStation.getVv().toString()));
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
