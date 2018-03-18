package com.martinez.airpollution.airsations;

import android.content.Context;

import com.martinez.airpollution.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public abstract class AirStation {
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

//    private int estacion, so2, no, no2, o3, dd, hr, prb, rs, ll;
//    private String titulo, fechasolar_utc_, pm10, pm25;
//    private double latitud, longitud, co, vv, tmp, ben, tol, mxil;
//
//    public AirStation(JSONObject j) throws JSONException{
//        //this(j.getInt("estacion"), j.getString("titulo"), j.getDouble("latitud"), j.getDouble("longitud"), j.getString("fechasolar_utc_"), j.getInt("so2"), j.getInt("no"), j.getInt("no2"), j.getDouble("co"), j.getString("pm10"), j.getInt("o3"), j.getInt("dd"), j.getDouble("vv"), j.getDouble("tmp"), j.getInt("hr"), j.getInt("prb"), j.getInt("rs"), j.getInt("ll"), j.getDouble("ben"), j.getDouble("tol"), j.getDouble("mxil"), j.getString("pm25"));
//        //this(j.getInt("estacion"), j.getString("titulo"), j.getDouble("latitud"), j.getDouble("longitud"), j.getString("fechasolar_utc_"));
//    }
//
//    public AirStation(AirStationBuilder airStationBuilder) {
//        this.estacion = airStationBuilder.estacion;
//        this.so2 = airStationBuilder.so2;
//        this.no = airStationBuilder.no;
//        this.no2 = airStationBuilder.no2;
//        this.o3 = airStationBuilder.o3;
//        this.dd = airStationBuilder.dd;
//        this.hr = airStationBuilder.hr;
//        this.prb = airStationBuilder.prb;
//        this.rs = airStationBuilder.rs;
//        this.ll = airStationBuilder.ll;
//        this.titulo = airStationBuilder.titulo;
//        this.fechasolar_utc_ = airStationBuilder.fechasolar_utc_;
//        this.pm10 = airStationBuilder.pm10;
//        this.pm25 = airStationBuilder.pm25;
//        this.latitud = airStationBuilder.latitud;
//        this.longitud = airStationBuilder.longitud;
//        this.co = airStationBuilder.co;
//        this.vv = airStationBuilder.vv;
//        this.tmp = airStationBuilder.tmp;
//        this.ben = airStationBuilder.ben;
//        this.tol = airStationBuilder.tol;
//        this.mxil = airStationBuilder.mxil;
//    }
//
//    /*
//    private AirStation(int estacion, String titulo, double latitud, double longitud, String fechasolar_utc_, int so2, int no, int no2, double co, String pm10, int o3, int dd, double vv, double tmp, int hr, int prb, int rs, int ll, double ben, double tol, double mxil, String pm25) {
//        this.estacion = estacion;
//        this.so2 = so2;
//        this.no = no;
//        this.no2 = no2;
//        this.pm10 = pm10;
//        this.o3 = o3;
//        this.dd = dd;
//        this.hr = hr;
//        this.prb = prb;
//        this.rs = rs;
//        this.ll = ll;
//        this.pm25 = pm25;
//        this.titulo = titulo;
//        this.fechasolar_utc_ = fechasolar_utc_;
//        this.latitud = latitud;
//        this.longitud = longitud;
//        this.co = co;
//        this.vv = vv;
//        this.tmp = tmp;
//        this.ben = ben;
//        this.tol = tol;
//        this.mxil = mxil;
//    }
//
//    private AirStation(int estacion, String titulo, double latitud, double longitud, String fechasolar_utc_){
//        this.estacion = estacion;
//        this.titulo = titulo;
//        this.latitud = latitud;
//        this.longitud = longitud;
//        this.fechasolar_utc_ = fechasolar_utc_;
//    }*/
//
//    public int getEstacion() {
//        return estacion;
//    }
//
//    public int getSo2() {
//        return so2;
//    }
//
//    public int getNo() {
//        return no;
//    }
//
//    public int getNo2() {
//        return no2;
//    }
//
//    public String getPm10() {
//        return pm10;
//    }
//
//    public int getO3() {
//        return o3;
//    }
//
//    public int getDd() {
//        return dd;
//    }
//
//    public int getHr() {
//        return hr;
//    }
//
//    public int getPrb() {
//        return prb;
//    }
//
//    public int getRs() {
//        return rs;
//    }
//
//    public int getLl() {
//        return ll;
//    }
//
//    public String getPm25() {
//        return pm25;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public String getFechasolar_utc_() {
//        return fechasolar_utc_;
//    }
//
//    public double getLatitud() {
//        return latitud;
//    }
//
//    public double getLongitud() {
//        return longitud;
//    }
//
//    public double getCo() {
//        return co;
//    }
//
//    public double getVv() {
//        return vv;
//    }
//
//    public double getTmp() {
//        return tmp;
//    }
//
//    public double getBen() {
//        return ben;
//    }
//
//    public double getTol() {
//        return tol;
//    }
//
//    public double getMxil() {
//        return mxil;
//    }
//
//
//
//    public static class AirStationBuilder{
//        private final String titulo, fechasolar_utc_;
//        private final int estacion;
//        private final double latitud, longitud;
//        private int so2, no, no2, o3, dd, hr, prb, rs, ll;
//        private String pm10, pm25;
//        private double co, vv, tmp, ben, tol, mxil;
//
//        public AirStationBuilder(int estacion, String titulo, String fechasolar_utc_, double latitud, double longitud){
//            this.estacion = estacion;
//            this.titulo = titulo;
//            this.fechasolar_utc_ = fechasolar_utc_;
//            this.latitud = latitud;
//            this.longitud = longitud;
//        }
//
//        public AirStationBuilder so2(int so2){
//            this.so2 = so2;
//            return this;
//        }
//
//        public AirStationBuilder no(int no){
//            this.no = no;
//            return this;
//        }
//
//        public AirStationBuilder no2(int no2){
//            this.no2 = no2;
//            return this;
//        }
//
//        public AirStationBuilder o3(int o3){
//            this.o3 = o3;
//            return this;
//        }
//
//        public AirStationBuilder dd(int dd){
//            this.dd = dd;
//            return this;
//        }
//
//        public AirStationBuilder hr(int hr){
//            this.hr = hr;
//            return this;
//        }
//
//        public AirStationBuilder prb(int prb){
//            this.prb = prb;
//            return this;
//        }
//
//        public AirStationBuilder rs(int rs){
//            this.rs = rs;
//            return this;
//        }
//
//        public AirStationBuilder ll(int ll){
//            this.ll = ll;
//            return this;
//        }
//
//        public AirStationBuilder pm10(String pm10){
//            this.pm10 = pm10;
//            return this;
//        }
//
//        public AirStationBuilder pm25(String pm25){
//            this.pm25 = pm25;
//            return this;
//        }
//
//        public AirStationBuilder co(double co){
//            this.co = co;
//            return this;
//        }
//
//        public AirStationBuilder vv(double vv){
//            this.vv = vv;
//            return this;
//        }
//
//        public AirStationBuilder tmp(double tmp){
//            this.tmp = tmp;
//            return this;
//        }
//
//        public AirStationBuilder ben(double ben){
//            this.ben = ben;
//            return this;
//        }
//
//        public AirStationBuilder tol(double tol){
//            this.tol = tol;
//            return this;
//        }
//
//        public AirStationBuilder mxil(double mxil){
//            this.mxil = mxil;
//            return this;
//        }
//    }
}
