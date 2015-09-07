package br.org.carona.caronasolidaria.controller;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

import br.org.carona.caronasolidaria.BuildConfig;
import br.org.carona.caronasolidaria.R;
import br.org.carona.caronasolidaria.rest.model.CaronaModel;

public class MapaPrincipalActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private LatLng mLatLng;
    private LatLng destinoLL;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_principal);
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Find our drawer view
        DrawerLayout mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = setupDrawerToggle(toolbar,mDrawer);
        mDrawer.setDrawerListener(new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.drawer_open, R.string.drawer_close));


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Find our drawer view
        NavigationView naviDrawer = (NavigationView) findViewById(R.id.navigation_view);
        naviDrawer.setNavigationItemSelectedListener(this);
        restGet();

    }

    private ActionBarDrawerToggle setupDrawerToggle(Toolbar toolbar, DrawerLayout mDrawer) {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);

    }

    private void restGet() {
    Ion.with(this).load(BuildConfig.BASE_URL+"/caronas/index.json").as(new TypeToken< List< CaronaModel>>(){})
    .setCallback(new FutureCallback<List<CaronaModel>>() {
        @Override
        public void onCompleted(Exception e, List<CaronaModel> result) {
            if(e== null)
                for(CaronaModel model: result){
                    CaronaModel.CaronaEntity carona = model.getCarona();
                    LatLng latLng = new LatLng(carona.getIncialLatitude(), carona.getIncialLongitude());
                    mMap.addMarker(
                            new MarkerOptions()
                                    .position(latLng)
                                    .snippet(String.format("Partida: %s \nSaida: %s", carona.getHorarioDePartida(), carona.getHorarioDeSaida())));
                }
        }
    });
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
//        if(mMap !=null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            mLatLng = new LatLng(latitude, longitude);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(mLatLng).zoom(18).build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        }
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
