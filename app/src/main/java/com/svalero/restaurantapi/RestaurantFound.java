package com.svalero.restaurantapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantFound extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_restaurant);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        LatLng zgz = new LatLng(41.674573,-0.889342);
        LatLng mdrd = new LatLng(40.426845,-3.694435);

        mMap.addMarker(new MarkerOptions().position(zgz).title(getString(R.string.restaurante_zgz)));
        mMap.addMarker(new MarkerOptions().position(mdrd).title(getString(R.string.restaurante_madrid)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(zgz));

    }


}