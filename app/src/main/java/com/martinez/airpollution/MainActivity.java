package com.martinez.airpollution;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.martinez.airpollution.airsations.AirStation;
import com.martinez.airpollution.airsations.AirStationType1;
import com.martinez.airpollution.airsations.AirStationType2;
import com.martinez.airpollution.airsations.AirStationType2Doubles;
import com.martinez.airpollution.airsations.AirStationType3;
import com.martinez.airpollution.airsations.AirStationType4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<AirStation> airStations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addListeners();REMOVE

        getAirStationsData();
    }

    /*REMOVE
    private void addListeners(){
        ((ListView) findViewById(R.id.listView)).setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
    }*/

    /*REMOVE
    public void onButtonClick(View w){
        Toast.makeText(this.getApplicationContext(), getString(R.string.toast_updating), Toast.LENGTH_LONG).show();
        new Communicator().execute("http://opendata.gijon.es/descargar.php?id=1&tipo=JSON");
    }*/

    //Remove onButtonClick() when this is implemented
    public void onImageClick(View w){
        //if info already parsed show data, wait (and show loading gif) otherwise.
//        while(airStations.size() == 0 || airStations.get(airStations.size()-1) == null) //avoids race condition but may loop forever(?)[Check if that's right]
//            Toast.makeText(this, R.string.toast_loading, Toast.LENGTH_LONG).show(); //Too much toast in my jam

        switch (w.getId()){
            case R.id.imageViewCircleAvdaConstitucion:
                startActivity(new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(0)));
                break;
            case R.id.imageViewCircleAvdaArgentina:
                startActivity(new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(1)));
                break;
            case R.id.imageViewCircleMontevil:
                startActivity(new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(2)));
                break;
            case R.id.imageViewCircleHermanosFelgueroso:
                startActivity(new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(3)));
                break;
            case R.id.imageViewCircleAvdaCastilla:
                startActivity(new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(4)));
                break;
            case R.id.imageViewCircleSantaBarbara:
                startActivity(new Intent(this, StationActivity.class).putExtra("airStation", airStations.get(5)));
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
                    AirStation airStation = createAirStation(jsonArray.getJSONObject(i));
                    airStations.add(airStation);
                    counter++;
                }
                loadData(); //REMOVE
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private AirStation createAirStation(JSONObject j){
            try {
                Context context = getApplicationContext();
                int estacion = j.getInt("estacion");
                String titulo = j.getString("titulo"), fechasolar_utc_ = j.getString("fechasolar_utc_"), pm10 = j.getString("pm10"), pm25 = j.getString("pm25");
                double latitud = j.getDouble("latitud"), longitud = j.getDouble("longitud");
                switch(estacion){
                    case 1:
                        return new AirStationType1(context, estacion, titulo, latitud, longitud, fechasolar_utc_, pm10, pm25,  j.getInt("so2"), j.getInt("no"), j.getInt("no2"), j.getDouble("co"), j.getInt("o3"), j.getInt("dd"), j.getDouble("vv"), j.getDouble("tmp"), j.getInt("hr"), j.getInt("prb"), j.getInt("rs"), j.getInt("ll"), j.getDouble("ben"), j.getDouble("tol"), j.getDouble("mxil"));
                    case 2:
                    case 3:
                        return new AirStationType2(context, estacion, titulo, latitud, longitud, fechasolar_utc_, pm10, pm25, j.getInt("so2"), j.getInt("no"), j.getInt("no2"), j.getDouble("co"), j.getInt("o3"));
                    case 10:
                        return new AirStationType3(context, estacion, titulo, latitud, longitud, fechasolar_utc_, pm10, pm25, j.getInt("so2"), j.getInt("no"), j.getInt("no2"), j.getInt("o3"), j.getInt("dd"), j.getDouble("vv"), j.getDouble("tmp"), j.getInt("hr"), j.getInt("prb"), j.getInt("rs"), j.getInt("ll"));
                    case 4:
                        return new AirStationType2Doubles(context, estacion, titulo, latitud, longitud, fechasolar_utc_, pm10, pm25, j.getDouble("so2"), j.getDouble("no"), j.getDouble("no2"), j.getDouble("co"), j.getDouble("o3"));
                    case 11:
                        return new AirStationType4(context, estacion, titulo, latitud, longitud, fechasolar_utc_, pm10, pm25, j.getInt("no"), j.getInt("no2"), j.getInt("co"));
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        //REMOVE
        private void loadData(){
            AirStation[] data = new AirStation[airStations.size()];
            for (int i = 0; i < data.length; i++)
                data[i] = airStations.get(i);

            ((ListView) findViewById(R.id.listView)).setAdapter(new ArrayAdapter<AirStation>(MainActivity.this, android.R.layout.simple_list_item_1, data));
        }
    }
}
