package com.example.usuario.needinghelp.Actividades;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.needinghelp.MainUser;
import com.example.usuario.needinghelp.R;
import com.example.usuario.needinghelp.TextosEsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InicioSesionActivity extends AppCompatActivity {

    private EditText usuario, contra;
    private Button iniciar, inicios;
    private String usuarioS, contraS, peticion;
    private BaseDeDatosMg bd;
    private boolean existe;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        iniciar = (Button) findViewById(R.id.inisBtn);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        inicios = (Button) findViewById(R.id.button3);
        inicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContinuarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bd = new BaseDeDatosMg();
        usuario = (EditText) findViewById(R.id.userTxt);
        contra = (EditText) findViewById(R.id.contraTxt);
    }

    public void iniciarSesion() {
        user = new User();
        usuarioS = usuario.getText().toString();
        contraS = contra.getText().toString();
        if(!usuarioS.equals("")&&!contraS.equals("")) {
            try {
                Connection connection= bd.conexionBD();
                peticion = TextosEsp.SelectUsuario()+usuarioS+"';";
                PreparedStatement consulta1 = connection.prepareStatement(peticion);
                ResultSet result1 = consulta1.executeQuery();
                result1.next();
                user.setUsuario(result1.getString("NUsuario"));
                user.setCorreo(result1.getString("Correo"));
                user.setContrasena(result1.getString("Contrasena"));
                user.setId(result1.getInt("Id"));
                user.setNombre(result1.getString("Nombre"));
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), TextosEsp.ErrorDatos(), Toast.LENGTH_SHORT).show();
            }
            if (usuarioS.equals(user.getUsuario())&&contraS.equals(user.getContrasena())){
                MainUser.setContrasena(user.getContrasena());
                MainUser.setCorreo(user.getCorreo());
                MainUser.setId(user.getId());
                MainUser.setNombre(user.getNombre());
                MainUser.setUsuario(user.getUsuario());
                Intent intent = new Intent(getApplicationContext(),NavigationActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), TextosEsp.ErrorUserCont(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), TextosEsp.ErrorDatos(), Toast.LENGTH_SHORT).show();
        }
    }



}
