package com.martinez.airpollution.airsations;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.martinez.airpollution.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public abstract class AirStation implements Serializable{
    protected Context context;
    private int estacion;
    private String titulo, fechasolar_utc_, pm10, pm25;
    private double latitud, longitud;

    public AirStation(Context context, int estacion, String titulo,  double latitud, double longitud, String fechasolar_utc_, String pm10, String pm25) {
        this.context = context;
        this.estacion = estacion;
        this.titulo = titulo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechasolar_utc_ = fechasolar_utc_;
        this.pm10 = pm10;
        this.pm25 = pm25;
    }


    protected String formatDate(String date){
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        String hour = date.substring(11, 13);
        String minute = date.substring(14, 16);
        String second = date.substring(17, 19);

        return day +"/" +month +"/" +year + " " +context.getString(R.string.text_before_time) +" " +hour +":" +minute +":" +second;
    }

    public int getEstacion() {
        return estacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFechasolar_utc_() {
        return fechasolar_utc_;
    }

    public String getPm10() {
        return pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
