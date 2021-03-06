package com.martinez.airpollution;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.martinez.airpollution.logic.AirStation;
import com.martinez.airpollution.logic.Property;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class StationActivity extends AppCompatActivity {

    AirStation airStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        airStation = (AirStation) getIntent().getSerializableExtra("airStation");
        loadData();
    }

    /*@Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_station, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menuHome:
                finish();
                break;
            case R.id.menuInfo:
                MainActivity.displayExplanationAlertDialog(this);
                break;
            case R.id.menuAbout:
                MainActivity.displayAboutAlertDialog(this);
                break;
        }
        return true;
    }


    private void loadData(){
        loadQuality();

        final List<Property> properties = new ArrayList<Property>();
        properties.add(new Property(getString(R.string.date), formatDate(airStation.getFechasolar_utc_())));
        properties.add(new Property(getString(R.string.location), getString(R.string.location_message)));
        if(!Double.isNaN(airStation.getTmp()))
            properties.add(new Property(getString(R.string.tmp), airStation.getTmp().toString() +" " +getString(R.string.temperature_unit)));
        if(!Double.isNaN(airStation.getLl()))
            properties.add(new Property(getString(R.string.ll), airStation.getLl().toString() +" " +getString(R.string.precipitation_unit)));
        if(!Double.isNaN(airStation.getDd()))
            properties.add(new Property(getString(R.string.dd), airStation.getDd().toString( )+" " +getString(R.string.wind_direction_unit) +" (" +formatWindDirection(airStation.getDd()) +")"));
        if(!Double.isNaN(airStation.getVv()))
            properties.add(new Property(getString(R.string.vv), (airStation.getVv().doubleValue()*3.6) +" " +getString(R.string.wind_speed_unit)));
        if(!Double.isNaN(airStation.getRs()))
            properties.add(new Property(getString(R.string.rs), airStation.getRs().toString() +" " +getString(R.string.solar_radiation_unit)));
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

    private void loadQuality(){
        AirStation.Quality avgQuality = airStation.getAverageAirQuality();
        ((ImageView) findViewById(R.id.imageViewCircle)).setImageResource(findCircleId(avgQuality));
        ((ImageView) findViewById(R.id.imageViewPicture)).setImageResource(getIntent().getIntExtra("pictureId", -1));
        ((TextView) findViewById(R.id.textViewStationName)).setText(getIntent().getStringExtra("name"));

        String str = getString(R.string.air_quality)+": " +formatQuality(avgQuality) +"\n";
        AirStation.Quality[] qualities = new AirStation.Quality[5];
        qualities[0] = airStation.getPm10Quality();
        qualities[1] = airStation.getSo2Quality();
        qualities[2] = airStation.getNo2Quality();
        qualities[3] = airStation.getCoQuality();
        qualities[4] = airStation.getO3Quality();
        String[] qualitiesNames = new String[5];
        qualitiesNames[0] = getString(R.string.pm10);
        qualitiesNames[1] = getString(R.string.so2);
        qualitiesNames[2] = getString(R.string.no2);
        qualitiesNames[3] = getString(R.string.co);
        qualitiesNames[4] = getString(R.string.o3);

        for(int i = 0; i < qualities.length; i++)
            if(!qualities[i].equals(AirStation.Quality.UNKNOWN))
                str += "\n" +qualitiesNames[i] +": " +formatQuality(qualities[i]);


        ((TextView) findViewById(R.id.textViewAirQuality)).setText(str);
    }


    private String formatDate(String date){
        try{
            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);
            String hour = date.substring(11, 13);
            String minute = date.substring(14, 16);

            Date dateObject = new Date(Integer.parseInt(year)-1900, Integer.parseInt(month)-1, Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(minute));
            dateObject = new Date(dateObject.getTime() - TimeZone.getTimeZone("Europe/Madrid").getRawOffset()); //Date has GMT+0 TimeZone
            if(TimeZone.getTimeZone("Europe/Madrid").inDaylightTime(dateObject)){
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
                calendar.setTime(dateObject);
                calendar.add(Calendar.HOUR_OF_DAY, 1);
                dateObject = calendar.getTime();
            }
            dateObject = new Date(dateObject.getTime() + TimeZone.getTimeZone("Europe/Madrid").getRawOffset());

            return (dateObject.getDate() < 10 ? "0"+dateObject.getDate() : dateObject.getDate()) +"/"
                    +((dateObject.getMonth()+1) < 10 ? "0"+(dateObject.getMonth()+1) : (dateObject.getMonth()+1)) +"/"
                    +(dateObject.getYear()+1900) + " " +getString(R.string.text_before_time) +" "
                    +(dateObject.getHours() < 10 ? "0"+dateObject.getHours() : dateObject.getHours()) +":"
                    +(dateObject.getMinutes() < 10 ? "0"+dateObject.getMinutes() : dateObject.getMinutes());
        }catch(IndexOutOfBoundsException | NumberFormatException e){
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

    private String formatQuality(AirStation.Quality quality){
        switch (quality){
            case VERY_GOOD:
                return getString(R.string.air_quality_very_good);
            case GOOD:
                return getString(R.string.air_quality_good);
            case BAD:
                return getString(R.string.air_quality_bad);
            case VERY_BAD:
                return getString(R.string.air_quality_very_bad);
            default:
                return getString(R.string.air_quality_unknown);
        }
    }

    private int findCircleId(AirStation.Quality quality){
        switch (quality){
            case VERY_GOOD:
                return R.drawable.ic_circle_very_good;
            case GOOD:
                return R.drawable.ic_circle_good;
            case BAD:
                return R.drawable.ic_circle_bad;
            case VERY_BAD:
                return R.drawable.ic_circle_very_bad;
            default:
                return R.drawable.ic_circle_unknown;
        }
    }
}
