package com.informatika.umm.modul_5_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * com.informatika.umm.modul_5_mobile.model
 * Created By robin
 * on 4/29/2020
 */
public class Restaurant {

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    public Geometry getGeometry() {
        return geometry;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }
}
