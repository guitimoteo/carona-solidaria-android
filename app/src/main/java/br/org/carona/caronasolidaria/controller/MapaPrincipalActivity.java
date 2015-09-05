package br.org.carona.caronasolidaria.controller;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.org.carona.caronasolidaria.R;

public class MapaPrincipalActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private LatLng mLatLng;
    private LatLng destinoLL;
    private NavigationView naviDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_principal);
        // Set a Toolbar to replace the ActionBar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Find our drawer view
        naviDrawer = (NavigationView) findViewById(R.id.navigation_view);
        naviDrawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mapa_principal_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
        // Adiciona um marcador de destino (Fatec)
        destinoLL = new LatLng(-23.637678, -46.578817);
        mMap.addMarker(new MarkerOptions().position(destinoLL).title("Destino"));
    }

    @Override
    public void onLocationChanged(Location location) {
        if(mLatLng ==null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            mLatLng = new LatLng(latitude, longitude);
            if(mMap !=null)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));

        }
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

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        final int id = menuItem.getItemId();
        switch (id){
            case R.id.nav_carona:

                break;
            case R.id.nav_veiculo:

                break;

            case  R.id.nav_listar_pedidos:

                break;
        }

        return false;
    }
}
