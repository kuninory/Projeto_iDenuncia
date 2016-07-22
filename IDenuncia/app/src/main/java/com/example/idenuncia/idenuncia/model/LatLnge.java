package com.example.idenuncia.idenuncia.model;

import java.io.Serializable;

public class LatLnge implements Serializable {
    private int lat;
    private int log;

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }
}
