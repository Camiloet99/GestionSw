package com.example.usuario.needinghelp.Actividades;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.usuario.needinghelp.MainUser;
import com.example.usuario.needinghelp.R;
import com.example.usuario.needinghelp.TextosEsp;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

public class PeticionActivity extends AppCompatActivity {

    private EditText desc;
    private Button btnRobo, btnRecursos, btnCasa, btnTragedia, btnOtro, btnTrabajo, btnPeticion;
    private float latitud, longitud;
    private String nombre, descripcion, dir;
    private TextView dirTxt;
    private BaseDeDatosMg bd;
    private List<Address> direccion;
    private int categoria;
    LinearLayout ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peticion);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck==PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
        ln = (LinearLayout) findViewById(R.id.linearL);
        btnRobo = (Button)findViewById(R.id.roboBtn);
        btnRobo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarC("Robo", 5);
                ln.setBackgroundColor(Color.parseColor("#000000"));
            }
        });
        btnRecursos = (Button)findViewById(R.id.recursosBtn);
        btnRecursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarC("Recursos", 1);
                ln.setBackgroundColor(Color.parseColor("#F387DE"));
    }
});
        btnCasa = (Button)findViewById(R.id.casaBtn);
        btnCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarC("Casa", 2);
                ln.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        });
        btnTragedia = (Button)findViewById(R.id.accidenteBtn);
        btnTragedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarC("Tragedia", 4);
                ln.setBackgroundColor(Color.parseColor("#FFFB00"));
            }
        });
        btnOtro = (Button)findViewById(R.id.otrosBtn);
        btnOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarC("Otro", 6);
                ln.setBackgroundColor(Color.parseColor("#FF9E00"));
            }
        });
        btnTrabajo = (Button)findViewById(R.id.trabajoBtn);
        btnTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asignarC("Trabajo", 3);
                ln.setBackgroundColor(Color.parseColor("#00FFFB"));
            }
        });
        desc = (EditText) findViewById(R.id.descTxt);
        dirTxt = (TextView) findViewById(R.id.dirTxt);
        nombre = MainUser.getNombre();
        btnPeticion = (Button) findViewById(R.id.petBtn);
        btnPeticion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarPeticion();
                Intent volver = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(volver);
                finish();
            }
        });
        bd = new BaseDeDatosMg();
    }

    private void asignarC(String cad, int cat){
        categoria = cat;
        Toast.makeText(getApplicationContext(),"Petición tipo " + cad + " elegida",Toast.LENGTH_SHORT).show();
        agregarCoordenadas();
    }

    public void recibirGps(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck==PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }

    public void agregarCoordenadas(){
        LocationManager locationManager = (LocationManager) PeticionActivity.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener(){
            public void onLocationChanged(Location location){
                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    direccion = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    dir = direccion.get(0).getAddressLine(0);
                }catch (IOException e) {}
                dirTxt.setText(dir.toString());
                latitud = (float)location.getLatitude();
                longitud = (float)location.getLongitude();
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider){}
            public void onProviderDisabled(String provider) {}
        };
        int permissionCheck = ContextCompat.checkSelfPermission(PeticionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
    }

    public void agregarPeticion(){
        descripcion = desc.getText().toString();
        Connection connection = bd.conexionBD();
        try {
            PreparedStatement pet = connection.prepareStatement(TextosEsp.InsertPeticiones());
            pet.setString(1,nombre);
            pet.setString(2,descripcion);
            pet.setFloat(3,latitud);
            pet.setFloat(4,longitud);
            pet.setInt(5,categoria);
            pet.setInt(6, MainUser.getId());
            pet.setInt(7,0);
            pet.executeUpdate();
            pet.close();
            connection.close();
            Toast.makeText(getApplicationContext(),TextosEsp.PeticionExitosa(),Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),TextosEsp.ErrorPet(),Toast.LENGTH_SHORT).show();
        }
    }
}
