package com.example.idenuncia.idenuncia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initMap();
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ponto1 = new LatLng(-16.6033508, -49.266545);

//        Location location = new Location("");
//        Location location2 = new Location("");
//        float distance = location.distanceTo(location2);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ponto1, 15));

        mMap.addMarker(new MarkerOptions()
                .position(ponto1)
                .title("INF")
                .snippet("Meu lugar preferido")
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE)));

        mMap.setOnInfoWindowClickListener(this);

        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if (
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED
                        &&
                        ActivityCompat.checkSelfPermission(
                                this,
                                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            mMap.setMyLocationEnabled(true);
        }
        mMap.setTrafficEnabled(true);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //Intent intent = new Intent(this, SecondActivity.class);
        //startActivity(intent);
    }
}
