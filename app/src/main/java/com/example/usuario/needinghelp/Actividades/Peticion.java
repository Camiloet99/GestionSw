package com.example.usuario.needinghelp.Actividades;

public class Peticion {
    private int Id;
    private String Nombre;
    private String Descripcion;
    private float Latitud;
    private float Longitud;
    private int IdCategoria;

    public Peticion(int id, String n, String desc, float lat, float lon, int cat){
        this.Id = id;
        Nombre = n;
        Descripcion = desc;
        Latitud = lat;
        Longitud = lon;
        IdCategoria = cat;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public float getLatitud() {
        return Latitud;
    }

    public void setLatitud(float latitud) {
        Latitud = latitud;
    }

    public float getLongitud() {
        return Longitud;
    }

    public void setLongitud(float longitud) {
        Longitud = longitud;
    }

    public int getCategoria() {
        return IdCategoria;
    }

    public void setCategoria(int idcategoria) {
        IdCategoria = idcategoria;
    }

}
