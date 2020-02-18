package com.example.usuario.needinghelp.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.usuario.needinghelp.MainUser;
import com.example.usuario.needinghelp.TextosEsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BaseDeDatosMg extends AppCompatActivity {

    private String peticion;
    private int cat;
    private ArrayList<Peticion> lista;
    private User user;

    public BaseDeDatosMg(){

    }

    public Connection conexionBD(){
        Connection connection =null;
        try {
            Class.forName(TextosEsp.ClassFName()).newInstance();
            connection = DriverManager.getConnection(TextosEsp.ConexionAddress());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),TextosEsp.ErrorCon(),Toast.LENGTH_SHORT).show();
        }
        return connection;
    }

    public ArrayList<Peticion> getCoordenadas() throws SQLException {
        ArrayList<Peticion> lista = new ArrayList<>();
        Connection conexion = this.conexionBD();
        try {
            PreparedStatement consulta1 = conexion.prepareStatement(TextosEsp.SelectPeticiones());
            ResultSet result1 = consulta1.executeQuery();
            while(result1.next()){
                int id = result1.getInt("Id");
                String nombre = result1.getString("Nombre");
                String desc = result1.getString("Descripcion");
                float lat = result1.getFloat("Latitud");
                float lon = result1.getFloat("Longitud");
                int cat = result1.getInt("IdCategoria");
                Peticion k = new Peticion(id, nombre, desc, lat, lon, cat);
                lista.add(k);
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),TextosEsp.ErrorDatos(),Toast.LENGTH_SHORT).show();
        }
        conexion.close();
        return lista;
    }

    public ArrayList<Peticion> getCoordenadasCat() throws SQLException{
        cat = CategoriasActivity.cadena;
        peticion = "SELECT * FROM Peticiones WHERE IdCategoria = '"+Integer.toString(cat)+"';";
        Toast.makeText(getApplicationContext(),peticion,Toast.LENGTH_SHORT).show();
        lista = new ArrayList<>();
        Connection conexion = this.conexionBD();
        try{
            PreparedStatement consultaCat = conexion.prepareStatement(peticion);
            ResultSet resultSet = consultaCat.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                String nombre = resultSet.getString("Nombre");
                String desc = resultSet.getString("Descripcion");
                float lat = resultSet.getFloat("Latitud");
                float lon = resultSet.getFloat("Longitud");
                int cat = resultSet.getInt("IdCategoria");
                Peticion k = new Peticion(id, nombre, desc, lat, lon, cat);
                lista.add(k);
            }
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),TextosEsp.ErrorDatos(),Toast.LENGTH_SHORT).show();
        }
        conexion.close();
        return lista;
    }


}
