package com.example.usuario.needinghelp.Actividades;

import android.os.StrictMode;
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

public class PruebaActivity extends AppCompatActivity {

    private Button btn;
    private TextView textView;
    private ArrayList<Peticion> list;
    private BaseDeDatosMg bd;
    private String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        btn = (Button) findViewById(R.id.opBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto ="";
                try {
                    list = bd.getCoordenadas();
                    for(int i = 0; i<list.size();i++){
                        texto += list.get(i).getLatitud()+", "+list.get(i).getLongitud()+"\n";
                        textView.setText(texto);
                    }
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(),TextosEsp.ErrorImp(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView = (TextView) findViewById(R.id.mTxt);
        bd = new BaseDeDatosMg();
    }
}
