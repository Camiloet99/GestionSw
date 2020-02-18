package com.example.usuario.needinghelp;

public class TextosEsp {

    public static String ErrorImp(){
        return "Error imprimiendo";
    }

    public static String ClassFName(){
        return "net.sourceforge.jtds.jdbc.Driver";
    }

    public static String ConexionAddress(){ return "jdbc:jtds:sqlserver://192.168.1.10;port=1433;databaseName=NeedingHelp;user=cam;password=123"; }

    public static String RegistroE(){ return "Registro exitoso"; }

    public static String ErrorReg(){
        return "Error en el registro";
    }

    public static String ErrorDatos(){ return "Error en los datos"; }

    public static String InsertUsuarios(){ return "insert into Usuario values(?,?,?,?)"; }

    public static String SelectPeticiones(){ return "select * from Peticiones;"; }

    public static String SelectPeticionesCat(){ return "select * from Peticiones WHERE Categoria = '"; }

    public static String InsertPeticiones(){
        return "insert into Peticiones values(?,?,?,?,?,?,?)";
    }

    public static String PeticionExitosa(){ return "Petición enviada exitosamente"; }

    public static String ErrorPet(){ return "Error enviando la petición"; }

    public static String ErrorCon(){ return "Error en la conexión"; }

    public static String ErrorUsuario(){ return "Usuario inexistente"; }

    public static String SelectUsuario(){ return "select * from Usuario WHERE NUsuario = '"; }

    public static String ErrorUserCont(){ return "Combinación usuario-contraseña inexistente"; }

    public static String CatTxt(){ return "Elige entre todas las categorías disponibles en la aplicación, para mapear solo las peticiones y solicitudes generadas con dicha categoría, por el momento sólo es posible mapear una a la vez"; }
}
