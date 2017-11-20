package com.myricseptember.weatherapp.ui.weather;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.myricseptember.weatherapp.R;
import com.myricseptember.weatherapp.util.LocationStatusHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int MY_LOCATION_PERMISSION = 1;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_loading_location)
    ConstraintLayout loadingLocationLayout;

    GoogleApiClient mGoogleApiClient;

    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_app_bar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        startGettingLocation();
    }

    private void startGettingLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            initializeGoogleApiClient();
        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_PERMISSION);
        }
    }

    void initializeGoogleApiClient() {

        if (mGoogleApiClient == null) {

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        if (mGoogleApiClient.isConnected() == false) {
            mGoogleApiClient.connect();
        }
    }

    private void requestLocationUpdate() {

        if (mLocationRequest == null) {

            checkIfLocationIsOff();
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            try {
                LocationServices.FusedLocationApi.requestLocationUpdates(
                        mGoogleApiClient, mLocationRequest, this);

            } catch (Exception e) {

            }
        }
    }

    private void checkIfLocationIsOff() {
        boolean value = LocationStatusHelper.checkIfLocationIsEnabled(this);
        if (value == false) {

            new AlertDialog.Builder(this).setTitle(R.string.location_services_off)
                    .setMessage(R.string.error_location_is_off)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent viewIntent = new Intent((Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            startActivity(viewIntent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

        }

    }

    private void removeLocationUpdates() {

        try {
            if (mGoogleApiClient != null) {
                if (mGoogleApiClient.isConnected() == true) {

                    LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
                    mGoogleApiClient.disconnect();
                }
            }
        } catch (Exception e) {

            Log.e("", e.getMessage());
        }
    }

    private void initializeFragments() {
        //plannig on add more fragments with more fuctionality and will initialize it here
        addCurrentWeatherFragment();
    }

    private void addCurrentWeatherFragment() {

        if (getSupportFragmentManager().findFragmentByTag(WeatherFragment.TAG) == null) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WeatherFragment.newInstance(Double.toString(mCurrentLocation.getLatitude()),
                            Double.toString(mCurrentLocation.getLongitude())), WeatherFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        removeLocationUpdates();
        mCurrentLocation = location;
        loadingLocationLayout.setVisibility(View.GONE);
        initializeFragments();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            requestLocationUpdate();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_LOCATION_PERMISSION) {
            if (grantResults.length > 0) {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    try {
                        startGettingLocation();
                    } catch (Exception e) {
                        Toast.makeText(WeatherActivity.this, R.string.location_permission_denied, Toast.LENGTH_LONG).show();
                    }
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeLocationUpdates();
    }
}
