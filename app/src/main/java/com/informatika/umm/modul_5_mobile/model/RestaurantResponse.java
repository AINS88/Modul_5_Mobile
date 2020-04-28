package com.informatika.umm.modul_5_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/*
 * com.informatika.umm.modul_5_mobile.model
 * Created By robin
 * on 4/29/2020
 */public class RestaurantResponse {

    @SerializedName("results")
    @Expose
    private List<Restaurant> results = new ArrayList<>();

    public List<Restaurant> getResults() {
        return results;
    }
}
