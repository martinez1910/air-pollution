package com.martinez.airpollution.airsations;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.martinez.airpollution.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class AirStation implements Serializable{
    private Integer estacion;
    private String titulo, fechasolar_utc_, pm10, pm25;
    private Double latitud, longitud, so2, no, no2, co, o3, dd, vv, tmp, hr, prb, rs, ll, ben, tol, mxil;

    public AirStation(JSONObject jsonObject){
        try{
            this.estacion = jsonObject.getInt("estacion");
            this.titulo = jsonObject.getString("titulo");
            this.latitud = jsonObject.getDouble("latitud");
            this.longitud = jsonObject.getDouble("longitud");
            this.fechasolar_utc_ = jsonObject.getString("fechasolar_utc_");
            this.longitud = jsonObject.getDouble("longitud");
            this.so2 = jsonObject.getDouble("so2");
            this.no = jsonObject.getDouble("no");
            this.no2 = jsonObject.getDouble("no2");
            this.co = jsonObject.getDouble("co");
            this.pm10 = jsonObject.getString("pm10");
            this.o3 = jsonObject.getDouble("o3");
            this.dd = jsonObject.getDouble("dd");
            this.vv = jsonObject.getDouble("vv");
            this.tmp = jsonObject.getDouble("tmp");
            this.hr = jsonObject.getDouble("hr");
            this.prb = jsonObject.getDouble("prb");
            this.rs = jsonObject.getDouble("rs");
            this.ll = jsonObject.getDouble("ll");
            this.ben = jsonObject.getDouble("ben");
            this.tol = jsonObject.getDouble("tol");
            this.mxil = jsonObject.getDouble("mxil");
            this.pm25 = jsonObject.getString("pm25");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    /*Move this method to StationActivity
    private String formatDate(String date){
        try{
            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);
            String hour = date.substring(11, 13);
            String minute = date.substring(14, 16);
            String second = date.substring(17, 19);

            return day +"/" +month +"/" +year + " " +context.getString(R.string.text_before_time) +" " +hour +":" +minute +":" +second;
        }catch(IndexOutOfBoundsException e){
            return date;
        }
    }*/

    public Integer getEstacion() {        return estacion;
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

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public Double getSo2() {
        return so2;
    }

    public Double getNo() {
        return no;
    }

    public Double getNo2() {
        return no2;
    }

    public Double getCo() {
        return co;
    }

    public Double getO3() {
        return o3;
    }

    public Double getDd() {
        return dd;
    }

    public Double getVv() {
        return vv;
    }

    public Double getTmp() {
        return tmp;
    }

    public Double getHr() {
        return hr;
    }

    public Double getPrb() {
        return prb;
    }

    public Double getRs() {
        return rs;
    }

    public Double getLl() {
        return ll;
    }

    public Double getBen() {
        return ben;
    }

    public Double getTol() {
        return tol;
    }

    public Double getMxil() {
        return mxil;
    }
}
