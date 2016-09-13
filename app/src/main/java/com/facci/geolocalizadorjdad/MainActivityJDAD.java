package com.facci.geolocalizadorjdad;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivityJDAD extends AppCompatActivity {

    LocationManager locManager;
    private double latitudJA;
    private double longitudJA;
    private double altitudJA;
    private float velocidadJA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_jdad);

        locManager=(LocationManager)getSystemService(LOCATION_SERVICE);

        List<String> listaProviders=locManager.getAllProviders();

        LocationProvider provider=locManager.getProvider(listaProviders.get(0));
    }
    public void ActualizarLatLongClick(View v){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
        )!= PackageManager.PERMISSION_GRANTED){
        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);
    }
    private final LocationListener locationListenerGPS =new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitudJA = location.getLongitude();
            latitudJA = location.getLatitude();
            altitudJA = location.getAltitude();
            velocidadJA =location.getSpeed();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud = (EditText)findViewById(R.id.txtLongitud);
                    EditText txtLatitud = (EditText)findViewById(R.id.txtLatitud);
                    EditText txtAltitud = (EditText)findViewById(R.id.txtAltitud);
                    EditText txtVelocidad = (EditText)findViewById(R.id.txtVelocidad);
                    txtLatitud.setText(latitudJA+"");
                    txtLongitud.setText(String.valueOf(longitudJA));
                    txtAltitud.setText(String.valueOf(altitudJA));
                    txtVelocidad.setText(String.valueOf(velocidadJA));
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
