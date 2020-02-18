package com.example.usuario.needinghelp.Actividades;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.needinghelp.Actividades.BaseDeDatosMg;
import com.example.usuario.needinghelp.R;
import com.example.usuario.needinghelp.TextosEsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContinuarActivity extends AppCompatActivity {

    private EditText contra, usuario, nombre, correo;
    private String contS, usuS, nombS, corrS;
    private Button reg, inicio;
    private BaseDeDatosMg bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg = (Button) findViewById(R.id.regButt);
        inicio = (Button) findViewById(R.id.initsBttn);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InicioSesionActivity.class);
                startActivity(intent);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrarse();
            }
        });
        bd = new BaseDeDatosMg();
        contra = (EditText) findViewById(R.id.contraTxt);
        usuario = (EditText) findViewById(R.id.usuTxt);
        nombre = (EditText) findViewById(R.id.nombreTxt);
        correo = (EditText) findViewById(R.id.emailTxt);

    }

    public void Registrarse(){
        try {
            contS = contra.getText().toString();
            usuS = usuario.getText().toString();
            nombS = nombre.getText().toString();
            corrS = correo.getText().toString();
            Connection connection = bd.conexionBD();
            try {
                PreparedStatement pet2 = connection.prepareStatement(TextosEsp.InsertUsuarios());
                pet2.setString(1,nombS);
                pet2.setString(2,contS);
                pet2.setString(3,corrS);
                pet2.setString(4,usuS);
                pet2.executeUpdate();
                Toast.makeText(getApplicationContext(),TextosEsp.RegistroE(),Toast.LENGTH_SHORT).show();
                pet2.close();
                connection.close();
                Intent intent = new Intent(getApplicationContext(), InicioSesionActivity.class);
                startActivity(intent);
                finish();
            }catch (SQLException e){
                Toast.makeText(getApplicationContext(),TextosEsp.ErrorReg(),Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),TextosEsp.ErrorDatos(),Toast.LENGTH_SHORT).show();
        }

    }


}
