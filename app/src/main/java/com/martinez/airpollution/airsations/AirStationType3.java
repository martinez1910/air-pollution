package com.martinez.airpollution.airsations;

import android.content.Context;

import com.martinez.airpollution.R;

public class AirStationType3 extends AirStation{

    private int so2, no, no2, o3, dd, hr, prb, rs, ll;
    private double vv, tmp;

    public AirStationType3(Context context, int estacion, String titulo, double latitud, double longitud, String fechasolar_utc_, String pm10, String pm25, int so2, int no, int no2, int o3, int dd, double vv, double tmp, int hr, int prb, int rs, int ll){
        super(context, estacion, titulo,  latitud, longitud, fechasolar_utc_, pm10, pm25);
        this.so2 = so2;
        this.no = no;
        this.no2 = no2;
        this.o3 = o3;
        this.dd = dd;
        this.hr = hr;
        this.prb = prb;
        this.rs = rs;
        this.ll = ll;
        this.vv = vv;
        this.tmp = tmp;
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
                +context.getString(R.string.tmp) +": " +getTmp() +" " +context.getString(R.string.temperature_unit) +"\n"
                +context.getString(R.string.hr) +": " +getHr() +" " +context.getString(R.string.humidity_unit) +"\n"
                +context.getString(R.string.prb) +": " +getPrb() +" " +context.getString(R.string.pressure_unit)+"\n"
                +"\n"
                +context.getString(R.string.pm10) +": " +getPm10() +" " +context.getString(R.string.unit_micro) +"\n"
                +context.getString(R.string.pm25) +": " +getPm25() +" " +context.getString(R.string.unit_micro) +"\n"
                +"\n"
                +context.getString(R.string.so2) +": " +getSo2() +" " +context.getString(R.string.unit_micro) +"\n"
                +context.getString(R.string.no) +": " +getNo() +" " +context.getString(R.string.unit_micro) +"\n"
                +context.getString(R.string.no2) +": " +getNo2() +" " +context.getString(R.string.unit_micro) +"\n"
                +context.getString(R.string.o3) +": " +getO3() +"\n"
                +context.getString(R.string.dd) +": " +getDd() +"\n"
                +context.getString(R.string.vv) +": " +getVv() +"\n"
                +context.getString(R.string.rs) +": " +getRs() +"\n"
                +context.getString(R.string.ll) +": " +getLl() +"\n";
    }

    public int getSo2() {
        return so2;
    }

    public int getNo() {
        return no;
    }

    public int getNo2() {
        return no2;
    }

    public int getO3() {
        return o3;
    }

    public int getDd() {
        return dd;
    }

    public int getHr() {
        return hr;
    }

    public int getPrb() {
        return prb;
    }

    public int getRs() {
        return rs;
    }

    public int getLl() {
        return ll;
    }

    public double getVv() {
        return vv;
    }

    public double getTmp() {
        return tmp;
    }
}
