package com.informatika.umm.modul_5_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * com.informatika.umm.modul_5_mobile.model
 * Created By robin
 * on 4/29/2020
 */public class Geometry {

    @SerializedName("location")
    @Expose
    private Location location;

    public Location getLocation() {
        return location;
    }
}
