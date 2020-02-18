package com.example.usuario.needinghelp;

public class MainUser {
    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        MainUser.nombre = nombre;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        MainUser.correo = correo;
    }

    public static String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        MainUser.contrasena = contrasena;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        MainUser.usuario = usuario;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        MainUser.id = id;
    }

    private static String nombre, correo, contrasena, usuario;
    private static int id;

}
