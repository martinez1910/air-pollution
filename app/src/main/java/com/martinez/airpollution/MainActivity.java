package com.martinez.airpollution;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.martinez.airpollution.logic.AirStation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<AirStation> airStations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAirStationsData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuHome:
                updateData();
                break;
            case R.id.menuInfo:
                displayExplanationAlertDialog(this);
                break;
            case R.id.menuAbout:
                displayAboutAlertDialog(this);
                break;
        }
        return true;
    }

    public void onImageClick(View w){
        Intent intent;
        switch (w.getId()){
            case R.id.imageViewCircleAvdaConstitucion:
                intent = new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(0));
                intent.putExtra("name", getString(R.string.station_avenida_constitucion));
                intent.putExtra("pictureId", R.drawable.ic_station_avda_constitucion);
                startActivity(intent);
                break;
            case R.id.imageViewCircleAvdaArgentina:
                intent = new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(1));
                intent.putExtra("name", getString(R.string.station_avenida_argentina));
                intent.putExtra("pictureId", R.drawable.ic_station_avda_argentina);
                startActivity(intent);
                break;
            case R.id.imageViewCircleMontevil:
                intent = new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(2));
                intent.putExtra("name", getString(R.string.station_montevil));
                intent.putExtra("pictureId", R.drawable.ic_station_montevil);
                startActivity(intent);
                break;
            case R.id.imageViewCircleHermanosFelgueroso:
                intent =new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(3));
                intent.putExtra("name", getString(R.string.station_hermanos_felgueroso));
                intent.putExtra("pictureId", R.drawable.ic_station_hermanos_felgueroso);
                startActivity(intent);
                break;
            case R.id.imageViewCircleAvdaCastilla:
                intent = new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(4));
                intent.putExtra("name", getString(R.string.station_avenida_castilla));
                intent.putExtra("pictureId", R.drawable.ic_station_avda_castilla);
                startActivity(intent);
                break;
            case R.id.imageViewCircleSantaBarbara:
                intent = new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(5));
                intent.putExtra("name", getString(R.string.station_santa_barbara));
                intent.putExtra("pictureId", R.drawable.ic_station_santa_barbara);
                startActivity(intent);
                break;

        }
    }

    private void getAirStationsData(){
        new Communicator().execute("http://opendata.gijon.es/descargar.php?id=1&tipo=JSON");
    }

    private void removeLoadingView(){
        findViewById(R.id.loadingLayout).setVisibility(View.GONE);
        findViewById(R.id.mainLayout).setVisibility(View.VISIBLE);
    }

    private void paintQualityCircles(){
        ImageView imageView = null;
        for(AirStation airStation : airStations){
            switch(airStation.getEstacion()){
                case 1:
                    imageView = findViewById(R.id.imageViewCircleAvdaConstitucion);
                    break;
                case 2:
                    imageView = findViewById(R.id.imageViewCircleAvdaArgentina);
                    break;
                case 10:
                    imageView = findViewById(R.id.imageViewCircleMontevil);
                    break;
                case 3:
                    imageView = findViewById(R.id.imageViewCircleHermanosFelgueroso);
                    break;
                case 4:
                    imageView = findViewById(R.id.imageViewCircleAvdaCastilla);
                    break;
                case 11:
                    imageView = findViewById(R.id.imageViewCircleSantaBarbara);
                    break;
            }
            switch(airStation.getAverageAirQuality()){
                case UNKNOWN:
                    imageView.setImageResource(R.drawable.ic_circle_unknown);
                    break;
                case VERY_GOOD:
                    imageView.setImageResource(R.drawable.ic_circle_very_good);
                    break;
                case GOOD:
                    imageView.setImageResource(R.drawable.ic_circle_good);
                    break;
                case BAD:
                    imageView.setImageResource(R.drawable.ic_circle_bad);
                    break;
                case VERY_BAD:
                    imageView.setImageResource(R.drawable.ic_circle_very_bad);
                    break;
            }
        }
    }

    private void updateData(){
        setContentView(R.layout.activity_main);
        getAirStationsData();
    }

    protected static void displayExplanationAlertDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.menu_alert_dialog_info_title)
                .setMessage(R.string.menu_alert_dialog_info_message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .create().show();
    }

    protected static void displayAboutAlertDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.menu_alert_dialog_about_title)
                .setMessage(R.string.menu_alert_dialog_about_message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .create().show();
    }


    private class Communicator extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            airStations = new ArrayList<AirStation>();
            String str = "";
            try {
                URLConnection connection = new URL(strings[0]).openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while((line = bufferedReader.readLine()) != null)
                    str += line;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        @Override
        protected void onPostExecute(String str){
            try {
                str = Html.fromHtml(str).toString();
                JSONObject jsonObject = new JSONObject(str);
                JSONArray jsonArray = jsonObject.getJSONObject("calidadairemediatemporales").getJSONArray("calidadairemediatemporal");
                int counter = 0;
                for(int i=0; i<jsonArray.length(); i++){
                    if(counter != 0 && airStations.get(counter-1).getEstacion() == jsonArray.getJSONObject(i).getInt("estacion"))//Just add the latest record of each station.
                        continue;
                    airStations.add(new AirStation(jsonArray.getJSONObject(i)));
                    counter++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            paintQualityCircles();
            removeLoadingView();
        }
    }
}
