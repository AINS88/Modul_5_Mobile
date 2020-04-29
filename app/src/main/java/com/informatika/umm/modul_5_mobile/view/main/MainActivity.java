package com.informatika.umm.modul_5_mobile.view.main;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.informatika.umm.modul_5_mobile.R;
import com.informatika.umm.modul_5_mobile.view.map.MapsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCheckPermission;
    private Button btnMapIntent;
    private int LOCATION_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        init();
    }

    private void bindView() {
        btnCheckPermission = findViewById(R.id.btn_check_permission);
        btnMapIntent = findViewById(R.id.btn_intent_map);
    }

    private void init() {
        btnCheckPermission.setOnClickListener(requestLocationClickListener());
        btnMapIntent.setOnClickListener(mapIntentClickListener());
    }

    private void showMessages(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener mapIntentClickListener() {
        return v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        };
    }

    private View.OnClickListener requestLocationClickListener() {
        return v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                showMessages("You have already granted this permission!");
            } else {
                requestLocationPermission();
            }
        };
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("In order to access the map you have to allow the permission")
                    .setPositiveButton("Ok", isGrantedClickListener())
                    .setNegativeButton("Cancel", isDeniedClickListener())
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
        }
    }

    private Dialog.OnClickListener isGrantedClickListener() {
        return (dialog, which) -> ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
    }

    private Dialog.OnClickListener isDeniedClickListener() {
        return (dialog, which) -> dialog.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    showMessages("Permission Granted");
                } else {
                    showMessages("Permission Denied");
                }
            }
        }
    }

}
