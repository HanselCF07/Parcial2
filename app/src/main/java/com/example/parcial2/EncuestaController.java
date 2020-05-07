package com.example.parcial2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EncuestaController {

    private BaseDatos db;

    public EncuestaController(Context context) {
        this.db = new BaseDatos(context);
    }

    public long agregarEncuesta(Encuesta encuesta){
        try{

            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();

            valores.put(DefDB.col_cedula, encuesta.getCedula());
            valores.put(DefDB.col_nombre, encuesta.getNombre());
            valores.put(DefDB.col_estrato, encuesta.getEstrato());
            valores.put(DefDB.col_salario, encuesta.getSalario());
            valores.put(DefDB.col_nivel_educativo, encuesta.getNivel_educativo());
            valores.put(DefDB.col_fecha, encuesta.getFecha());
            long id = database.insert(DefDB.tabla_Encuestas,null, valores);
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al insertar");
            return 0;
        }
    }

    public List<Encuesta> encuestaFiltro(){
        SQLiteDatabase database = db.getReadableDatabase();
        List<Encuesta> encuestaList = new ArrayList<>();

        Cursor c = database.query(DefDB.tabla_Encuestas, null, null, null, null, null, null);

        if (c.getCount()>0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Encuesta e = new Encuesta();
                e.setCedula(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_cedula+"")));
                e.setNombre(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_nombre+"")));
                e.setEstrato(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_estrato+"")));
                e.setNivel_educativo(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_nivel_educativo+"")));
                e.setSalario(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_salario+"")));
                e.setFecha(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_fecha+"")));
                encuestaList.add(e); //add the item
                c.moveToNext();
            }

            database.close();
            return encuestaList;
        }
        else{
            database.close();
            return encuestaList;
        }
    }

    public Encuesta buscarEncuesta(String Cedula){
        SQLiteDatabase database = db.getReadableDatabase();
        Encuesta encuesta = new Encuesta();
        String[] args ={Cedula};

        Cursor c = database.query(DefDB.tabla_Encuestas, null, ""+DefDB.col_cedula+"=?", args, null, null, null);

        if (c.getCount()>0) {
            c.moveToFirst();
            encuesta.setCedula(Cedula);
            encuesta.setNombre(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_nombre+"")));
            encuesta.setEstrato(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_estrato+"")));
            encuesta.setSalario(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_salario+"")));
            encuesta.setNivel_educativo(c.getString(c.getColumnIndexOrThrow(""+DefDB.col_nivel_educativo+"")));
            encuesta.setFecha(c.getString(c.getColumnIndexOrThrow(DefDB.col_fecha)));

            database.close();
            return encuesta;
        }
        else{
            database.close();
            return encuesta;
        }

    }

    public long eliminarEncuesta(String Cedula){
        SQLiteDatabase database = db.getWritableDatabase();
        String[] args ={Cedula};

        long id = database.delete(DefDB.tabla_Encuestas,""+DefDB.col_cedula+"=?",args);
        database.close();

        return id;
    }

    public long actualizarEncuesta(Encuesta encuesta){
        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();

            String[] args ={encuesta.getCedula()};

            valores.put(DefDB.col_nombre, encuesta.getNombre());
            valores.put(DefDB.col_estrato, encuesta.getEstrato());
            valores.put(DefDB.col_salario, encuesta.getSalario());
            valores.put(DefDB.col_nivel_educativo, encuesta.getNivel_educativo());

            long id = database.update(DefDB.tabla_Encuestas, valores,""+DefDB.col_cedula+"=?",args);
            database.close();

            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }
    public Cursor allEncuestas() {
        try {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cur = database.rawQuery("select "+DefDB.col_cedula+" as _id , "+DefDB.col_nombre+", "+DefDB.col_estrato+", "+DefDB.col_salario+", " +
                    ""+DefDB.col_nivel_educativo+", "+DefDB.col_fecha+" from "+DefDB.tabla_Encuestas+"", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }
}