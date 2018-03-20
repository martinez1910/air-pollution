package com.martinez.airpollution;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

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
                    //AirStation airStation = createAirStation(jsonArray.getJSONObject(i));//REMOVE
                    airStations.add(new AirStation(jsonArray.getJSONObject(i)));
                    counter++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
