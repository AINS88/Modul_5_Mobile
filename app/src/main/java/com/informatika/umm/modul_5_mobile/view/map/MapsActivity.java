package com.informatika.umm.modul_5_mobile.view.map;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.informatika.umm.modul_5_mobile.R;
import com.informatika.umm.modul_5_mobile.model.Restaurant;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private double latitude;
    private double longitude;
    private MapsViewModel viewModel;
    private Button btnRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        bindView();
        setupViewModel();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void bindView() {
        btnRestaurant = findViewById(R.id.btnRestaurant);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        getDeviceLocation();

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null) {
                btnRestaurant.setOnClickListener(v -> {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    viewModel.fetchNearbyRestaurants(latitude, longitude);
                    showMessages("Nearby Restaurants");
                });
            }
        });
    }

    private void showMessages(String message) {
        Toast.makeText(MapsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void moveCamera(LatLng latLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) 15));
    }

    private void getDeviceLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null){
                moveCamera(new LatLng(location.getLatitude(), location.getLongitude()));
                showMessages("Map is Ready");
            }
        });
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MapsViewModel.class);
        viewModel.getRestaurants().observe(this, this::getDetailRestaurants);
    }

    private void getDetailRestaurants(List<Restaurant> restaurant) {
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            for (int i = 0; i < restaurant.size(); i++) {
                LatLng latLng = new LatLng(
                        restaurant.get(i).getGeometry().getLocation().getLat(),
                        restaurant.get(i).getGeometry().getLocation().getLng());
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng)
                        .title(restaurant.get(i).getName())
                        .snippet(restaurant.get(i).getVicinity());
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(markerOptions);
                mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
            }
        });
    }
}
