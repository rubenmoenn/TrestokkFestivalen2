package com.example.ruben.trestokkfestivalen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static android.provider.UserDictionary.Words.APP_ID;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final String TAG = "Trestokk";
    private static final float DEFAULT_ZOOM = 15f;
    LocationListener mLocationListener;
    LocationManager mLocationManager;

    private GoogleMap mMap;
    int mIndex = 0;


//  Using MultiDex because of the amount of libraries that are used
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button mBackButton = findViewById(R.id.back_button);
        // back_button is pressed
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Back button clicked");
                goBack();
            }
        });

//        Button mToggleButton = findViewById(R.id.night_day_toggle);
//        // night_day_toggle button is pressed
//        mToggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "Night/Day toggle button clicked");
//                toggleNightDay();
//            }
//        });
    }

//    private void getLocationPermission() {
//        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION};
//
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                mLocationPermissionsGranted = true;
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        permissions,
//                        LOCATION_PERMISSION_REQUEST_CODE);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        mLocationPermissionsGranted = false;
//
//        switch (requestCode) {
//            case LOCATION_PERMISSION_REQUEST_CODE: {
//                if (grantResults.length > 0) {
//                    for (int i = 0; i < grantResults.length; i++) {
//                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                            mLocationPermissionsGranted = false;
//                            return;
//                        }
//                    }
//                    mLocationPermissionsGranted = true;
//                    //initialize our map
//                }
//            }
//        }
//    }
//
//    public void getDeviceLocation() {
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
//        try {
//            if (mLocationPermissionsGranted) {
//                Task location = mFusedLocationClient.getLastLocation();
//                location.addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "onComplete: found location");
//                            Location currentLocation = (Location) task.getResult();
//                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);
//                        } else {
//                            Log.d(TAG, "onComplete: current location is null");
//                            Toast.makeText(MapsActivity.this, "Unable to get current location", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//            }
//        } catch (SecurityException e) {
//            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
//        }
//
//
//    }

    // Switches from MapsActivity to MainActivity via the back_button
    public void goBack() {
        Intent intent = new Intent(this, com.example.ruben.trestokkfestivalen.MainActivity.class);
        startActivity(intent);
    }

//    // Toggles the night and day styles of Google Maps via the night_day_toggle button
//    public void toggleNightDay() {
//        // Turning the map into night mode
//        if (mIndex == 0) {
//            boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
//                    .getString(R.string.style_night_json)));
//
//            if (!success) {
//                Log.e(TAG, "Style parsing (night) failed.");
//            }
//            mIndex = 1;
//
//            // Turning the map into day mode
//        } else if (mIndex == 1) {
//            boolean success = mMap.setMapStyle(new MapStyleOptions(getResources()
//                    .getString(R.string.style_day_json)));
//            if (!success) {
//                Log.e(TAG, "Style parsing (day) failed.");
//            }
//            mIndex = 0;
//        }
//    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        if (mLocationPermissionsGranted) {
//            getDeviceLocation();
//
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            mMap.setMyLocationEnabled(true);
//        }

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Add a marker in Haslemoen and move the camera
        LatLng haslemoen = new LatLng(60.650212, 11.868442);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(haslemoen));



        LatLng scene1 = new LatLng(60.650976, 11.875086);
        mMap.addMarker(new MarkerOptions()
                .position(scene1)
                .title("Scene Nummer 1")
                .snippet("Her vil blant annet ..,..,..,.. opptre!")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.icon_scene)));

        LatLng scene2 = new LatLng(60.651326, 11.873739);
        mMap.addMarker(new MarkerOptions()
                .position(scene2)
                .title("Scene Nummer 2")
                .snippet("Her vil blant annet ..,..,..,.. opptre!")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.icon_scene)));

        LatLng texasScene = new LatLng(60.650676, 11.872413);
        mMap.addMarker(new MarkerOptions()
                .position(texasScene)
                .title("Texas Scene")
                .snippet("Her kan du selv opptre med dine egne musikalske ferdigheter!")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.icon_scene)));

        LatLng bar = new LatLng(60.651463, 11.875464);
        mMap.addMarker(new MarkerOptions()
                .position(bar)
                .title("Bar")
                .snippet("Her holder baren til!")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.liquor_icon)));

        LatLng toilet = new LatLng(60.651707, 11.874966);
        mMap.addMarker(new MarkerOptions()
                .position(toilet)
                .title("Toaletter")
                .snippet("Her er det 4 toaletter")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.wc_icon)));

        LatLng food = new LatLng(60.650693, 11.871697);
        mMap.addMarker(new MarkerOptions()
                .position(food)
                .title("Mat Buer")
                .snippet("Her kan du finne all den maten du ville ønske, kom innom og ta en titt da vel!")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.food_icon)));

        LatLng camping = new LatLng(60.651871, 11.865679);
        mMap.addMarker(new MarkerOptions()
                .position(camping)
                .title("Camping og Telt")
                .snippet("Her finner du camping og telt området for de som har kjøpt plass")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.camping_icon)));

        LatLng avslapping = new LatLng(60.650672, 11.872925);
        mMap.addMarker(new MarkerOptions()
                .position(avslapping)
                .title("Avslappings Sone")
                .snippet("Avslapping/Green Zone, hvor du kan ta med deg litt mat og drikke, og bare slappe helt av")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.relax_icon)));

        LatLng sleep = new LatLng(60.650505, 11.874357);
        mMap.addMarker(new MarkerOptions()
                .position(sleep)
                .title("Kasserner")
                .snippet("Her er kassernene for de som har bestilt soveplass")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.sleeping_icon)));

        LatLng sleep2 = new LatLng(60.651220, 11.873165);
        mMap.addMarker(new MarkerOptions()
                .position(sleep2)
                .title("Kasserne 2")
                .snippet("Her er kassernene for de som har bestilt soveplass")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.sleeping_icon)));

        LatLng sleep3 = new LatLng(60.650963, 11.874164);
        mMap.addMarker(new MarkerOptions()
                .position(sleep3)
                .title("Kasserne 3")
                .snippet("Her er kassernene for de som har bestilt soveplass")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.sleeping_icon)));

        LatLng bus = new LatLng(60.648660, 11.865827);
        mMap.addMarker(new MarkerOptions()
                .position(bus)
                .title("Buss Stasjon")
                .snippet("Her kan du finne buss stoppet hvor bussen vil kjøre til og fra festivalen. Busstider kan du finne på nettsiden vår trestokkfestivalen.no")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.bus_icon)));

        // Adding a LatLng zoom that zooms the camera towards the main attraction as the map function is launched
        mMap = googleMap;
        LatLngBounds haslemoenZoom = new LatLngBounds(new LatLng(60.646743, 11.861594), new LatLng(60.654764, 11.878185));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(haslemoenZoom.getCenter(), 15));

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));
    }

}
