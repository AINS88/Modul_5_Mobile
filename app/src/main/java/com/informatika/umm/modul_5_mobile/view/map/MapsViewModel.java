package com.informatika.umm.modul_5_mobile.view.map;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.informatika.umm.modul_5_mobile.api.Client;
import com.informatika.umm.modul_5_mobile.api.Service;
import com.informatika.umm.modul_5_mobile.model.Restaurant;
import com.informatika.umm.modul_5_mobile.model.RestaurantResponse;
import com.informatika.umm.modul_5_mobile.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * com.informatika.umm.modul_5_mobile
 * Created By robin
 * on 4/29/2020
 */
public class MapsViewModel extends ViewModel {

    private MutableLiveData<List<Restaurant>> listRestaurants = new MutableLiveData<>();

    public void fetchNearbyRestaurants(double latitude, double longitude) {

        Service apiService = Client.getClient().create(Service.class);
        Call<RestaurantResponse> call = apiService.getNearbyPlaces("restaurant", latitude + "," + longitude, 1000, Utils.KEY);
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantResponse> call, @NonNull Response<RestaurantResponse> response) {
                List<Restaurant> restaurants = null;
                if (response.body() != null) {
                    restaurants = response.body().getResults();
                }
                listRestaurants.postValue(restaurants);
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantResponse> call, @NonNull Throwable t) {
                Log.d(t.getMessage(), "onFailure");
            }
        });
    }

    LiveData<List<Restaurant>> getRestaurants() {
        return listRestaurants;
    }

}
