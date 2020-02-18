package com.example.usuario.needinghelp.Actividades;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.needinghelp.R;
import com.example.usuario.needinghelp.TextosEsp;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriasActivity extends AppCompatActivity {

    private Button hogar, trabajo, tragedia, robo, otro, recursos;
    private TextView texto;
    static int cadena = 0;
    private ArrayList<Peticion> list;
    BaseDeDatosMg bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        hogar = (Button) findViewById(R.id.catHogarBtn);
        hogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadena = 2;
                Busqueda();
            }
        });
        trabajo = (Button) findViewById(R.id.catTrabajoBtn);
        trabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadena = 3;
                Busqueda();
            }
        });
        tragedia = (Button) findViewById(R.id.catTragediaBtn);
        tragedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadena = 4;
                Busqueda();
            }
        });
        robo = (Button) findViewById(R.id.catRoboBtn);
        robo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadena = 5;
                Busqueda();
            }
        });
        otro = (Button) findViewById(R.id.catOtrosBtn);
        otro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadena = 6;
                Busqueda();
            }
        });
        recursos = (Button) findViewById(R.id.catRecursosBtn);
        recursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadena = 1;
                Busqueda();
            }
        });
        texto = (TextView) findViewById(R.id.txtCat);
        texto.setText(TextosEsp.CatTxt());
    }

    public void Busqueda(){


    }

    public int getCadena(){
        return cadena;
    }
}
