package com.martinez.airpollution.logic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class AirStation implements Serializable{
    public enum Quality{UNKNOWN, VERY_GOOD, GOOD, BAD, VERY_BAD}
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
            this.so2 = jsonObject.optDouble("so2");
            this.no = jsonObject.optDouble("no");
            this.no2 = jsonObject.optDouble("no2");
            this.co = jsonObject.optDouble("co");
            this.pm10 = jsonObject.optString("pm10");
            this.o3 = jsonObject.optDouble("o3");
            this.dd = jsonObject.optDouble("dd");
            this.vv = jsonObject.optDouble("vv");
            this.tmp = jsonObject.optDouble("tmp");
            this.hr = jsonObject.optDouble("hr");
            this.prb = jsonObject.optDouble("prb");
            this.rs = jsonObject.optDouble("rs");
            this.ll = jsonObject.optDouble("ll");
            this.ben = jsonObject.optDouble("ben");
            this.tol = jsonObject.optDouble("tol");
            this.mxil = jsonObject.optDouble("mxil");
            this.pm25 = jsonObject.optString("pm25");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public Quality getPm10Quality(){
        if(pm10.equals("null") || pm10.equals("")) return Quality.UNKNOWN;
        Double pm10Double = Double.parseDouble(pm10);
        if(pm10Double < 26) return Quality.VERY_GOOD;
        if(pm10Double < 51) return Quality.GOOD;
        if(pm10Double < 76) return Quality.BAD;
        return Quality.VERY_BAD;
    }

    public Quality getSo2Quality(){
        if(so2 == null || so2.isNaN()) return Quality.UNKNOWN;
        if(so2 < 64) return Quality.VERY_GOOD;
        if(so2 < 126) return Quality.GOOD;
        if(so2 < 189) return Quality.BAD;
        return Quality.VERY_BAD;
    }

    public Quality getNo2Quality(){
        if(no2 == null || no2.isNaN()) return Quality.UNKNOWN;
        if(no2 < 101) return Quality.VERY_GOOD;
        if(no2 < 201) return Quality.GOOD;
        if(no2 < 301) return Quality.BAD;
        return Quality.VERY_BAD;
    }

    public Quality getCoQuality(){
        if(co == null || co.isNaN()) return Quality.UNKNOWN;
        if(co < 6) return Quality.VERY_GOOD;
        if(co < 11) return Quality.GOOD;
        if(co < 16) return Quality.BAD;
        return Quality.VERY_BAD;
    }

    public Quality getO3Quality(){
        if(o3 == null || o3.isNaN()) return Quality.UNKNOWN;
        if(o3 < 61) return Quality.VERY_GOOD;
        if(o3 < 121) return Quality.GOOD;
        if(o3 < 181) return Quality.BAD;
        return Quality.VERY_BAD;
    }

    public Quality getAverageAirQuality(){
        Quality max;
        Quality[] qualities = new Quality[5];
        qualities[0] = getPm10Quality();
        qualities[1] = getSo2Quality();
        qualities[2] = getNo2Quality();
        qualities[3] = getCoQuality();
        qualities[4] = getO3Quality();
        max = qualities[0];
        for(int i = 1; i < qualities.length; i++)
            if(qualities[i].ordinal() > max.ordinal())
                max = qualities[i];
        return max;
    }


    public Integer getEstacion() {
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
