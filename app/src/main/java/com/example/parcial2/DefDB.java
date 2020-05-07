package com.example.parcial2;

public class DefDB {

    public static final String nameDb = "Encuestas";
    public static final String tabla_Encuestas = "encuesta";
    public static final String col_cedula = "cedula";
    public static final String col_nombre = "nombre";
    public static final String col_estrato = "estrato";
    public static final String col_salario = "salario";
    public static final String col_nivel_educativo = "nivel_educativo";
    public static final String col_fecha = "fecha";

    public static final String create_tabla_recibos = "CREATE TABLE IF NOT EXISTS "+tabla_Encuestas+" (" +
            "  "+col_cedula+" INTEGER PRIMARY KEY," +
            "  "+col_nombre+" TEXT," +
            "  "+col_estrato+" TEXT," +
            "  "+col_salario+" TEXT," +
            "  "+col_nivel_educativo+" TEXT," +
            "  "+col_fecha+" TEXT" +
            ");";

}
