package com.example.usuario.needinghelp.Fragmentos;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usuario.needinghelp.Actividades.BaseDeDatosMg;
import com.example.usuario.needinghelp.Actividades.Peticion;
import com.example.usuario.needinghelp.R;
import com.example.usuario.needinghelp.TextosEsp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private View mView;
    private ArrayList<Peticion> list;
    private BaseDeDatosMg bd;

    public MapsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        bd = new BaseDeDatosMg();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_maps, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        LatLng inicial = new LatLng(6.227728692059392, -75.59103790899076);
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
        CameraPosition l = CameraPosition.builder().target(inicial).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(l));
        try {
            list = bd.getCoordenadas();
            for(int i = 0; i<list.size();i++){
                if(list.get(i).getCategoria()==5) {
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud())).title(list.get(i).getNombre()).snippet(list.get(i).getDescripcion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.robo));
                    googleMap.addMarker(markerOptions);
                }
                else if(list.get(i).getCategoria()==3) {
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud())).title(list.get(i).getNombre()).snippet(list.get(i).getDescripcion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.trabajo));
                    googleMap.addMarker(markerOptions);
                }
                else if(list.get(i).getCategoria()==4) {
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud())).title(list.get(i).getNombre()).snippet(list.get(i).getDescripcion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.tragedia));
                    googleMap.addMarker(markerOptions);
                }
                else if(list.get(i).getCategoria()==2) {
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud())).title(list.get(i).getNombre()).snippet(list.get(i).getDescripcion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.casa));
                    googleMap.addMarker(markerOptions);
                }
                else if(list.get(i).getCategoria()==6) {
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud())).title(list.get(i).getNombre()).snippet(list.get(i).getDescripcion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.otros));
                    googleMap.addMarker(markerOptions);
                }
                else if(list.get(i).getCategoria()==1) {
                    MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(list.get(i).getLatitud(), list.get(i).getLongitud())).title(list.get(i).getNombre()).snippet(list.get(i).getDescripcion()).icon(BitmapDescriptorFactory.fromResource(R.drawable.recursos));
                    googleMap.addMarker(markerOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
