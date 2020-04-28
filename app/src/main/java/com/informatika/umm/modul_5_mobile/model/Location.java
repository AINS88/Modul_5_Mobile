package com.informatika.umm.modul_5_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * com.informatika.umm.modul_5_mobile.model
 * Created By robin
 * on 4/29/2020
 */public class Location {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
