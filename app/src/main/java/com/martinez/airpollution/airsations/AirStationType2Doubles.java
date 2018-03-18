package com.martinez.airpollution.airsations;

import android.content.Context;

import com.martinez.airpollution.R;

public class AirStationType2Doubles extends AirStation{

    private double so2, no, no2, o3; //This version of the station has decimal precision in this values.
    private double co;

    public AirStationType2Doubles(Context context, int estacion, String titulo, double latitud, double longitud, String fechasolar_utc_, String pm10, String pm25, double so2, double no, double no2, double co, double o3){
        super(context, estacion, titulo,  latitud, longitud, fechasolar_utc_, pm10, pm25);
        this.so2 = so2;
        this.no = no;
        this.no2 = no2;
        this.co = co;
        this.o3 = o3;
    }

    @Override
    public String toString(){
        return context.getString(R.string.station) +": " +getEstacion() +"\n"
                +context.getString(R.string.title )+": " +getTitulo() +"\n"
                +context.getString(R.string.latitude) +": " +getLatitud() +"\n"
                +context.getString(R.string.longitude) +": " +getLongitud() +"\n"
                +"\n"
                +context.getString(R.string.date) +": " +formatDate(getFechasolar_utc_()) +"\n"
                +"\n"
                +context.getString(R.string.pm10) +": " +(getPm10().isEmpty() || getPm10().equals("null") ? context.getString(R.string.no_data) : (getPm10() +" " +context.getString(R.string.unit_micro))) +"\n"
                +context.getString(R.string.pm25) +": " +(getPm25().isEmpty() || getPm25().equals("null") ? context.getString(R.string.no_data) : (getPm25() +" " +context.getString(R.string.unit_micro))) +"\n"
                +"\n"
                +context.getString(R.string.so2) +": " +getSo2() +" " +context.getString(R.string.unit_micro) +"\n"
                +context.getString(R.string.no) +": " +getNo() +" " +context.getString(R.string.unit_micro) +"\n"
                +context.getString(R.string.no2) +": " +getNo2() +" " +context.getString(R.string.unit_micro) +"\n"
                +context.getString(R.string.co) +": " +getCo() +" " +context.getString(R.string.unit_milli) +"\n"
                +context.getString(R.string.o3) +": " +getO3() +"\n";
    }

    public double getSo2() {
        return so2;
    }

    public double getNo() {
        return no;
    }

    public double getNo2() {
        return no2;
    }

    public double getO3() {
        return o3;
    }

    public double getCo() {
        return co;
    }
}
