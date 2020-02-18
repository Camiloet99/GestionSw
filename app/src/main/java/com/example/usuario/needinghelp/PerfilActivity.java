package com.example.usuario.needinghelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.needinghelp.Actividades.BaseDeDatosMg;
import com.example.usuario.needinghelp.Actividades.Peticion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfilActivity extends AppCompatActivity {

    String nombre, usuario, correo, idperfil, petcreadas, pettomadas;
    TextView nom, usu, corr, idp, petc, pett;
    BaseDeDatosMg bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new BaseDeDatosMg();
        setContentView(R.layout.activity_perfil);
        nom = (TextView) findViewById(R.id.nombreetxt);
        nom.setText(MainUser.getNombre());
        usu = (TextView) findViewById(R.id.usertxt);
        usu.setText(MainUser.getUsuario());
        corr = (TextView) findViewById(R.id.correootxt);
        corr.setText(MainUser.getCorreo());
        idp = (TextView) findViewById(R.id.idcuentatxt);
        idp.setText(Integer.toString(MainUser.getId()));
        petc = (TextView) findViewById(R.id.petctxt);
        try {
            petc.setText(Integer.toString(PetCreadas()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pett = (TextView) findViewById(R.id.pettomtxt);
        try {
            pett.setText(Integer.toString(PetTomadas()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int PetCreadas() throws SQLException{
        Connection connection = bd.conexionBD();
        try {
            PreparedStatement consulta1 = connection.prepareStatement("SELECT COUNT(*) FROM Peticiones WHERE IdUserC = "+MainUser.getId()+";");
            ResultSet result1 = consulta1.executeQuery();
            result1.next();
            int num = result1.getInt(1);
            connection.close();
            return num;
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),TextosEsp.ErrorDatos(),Toast.LENGTH_SHORT).show();
            connection.close();
            return 0;
        }
    }

    public int PetTomadas() throws SQLException{
        Connection connection = bd.conexionBD();
        try {
            PreparedStatement consulta1 = connection.prepareStatement("SELECT COUNT(*) FROM Peticiones WHERE IdUserResp = "+MainUser.getId()+";");
            ResultSet result1 = consulta1.executeQuery();
            result1.next();
            int num = result1.getInt(1);
            connection.close();
            return num;
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),TextosEsp.ErrorDatos(),Toast.LENGTH_SHORT).show();
            connection.close();
            return 0;
        }
    }
}
