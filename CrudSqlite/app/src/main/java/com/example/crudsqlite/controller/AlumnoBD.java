package com.example.crudsqlite.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.crudsqlite.model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoBD extends SQLiteOpenHelper implements IAlumnoBD {

    Context context;
    private List<Alumno> AlumnoList = new ArrayList<>();

    public AlumnoBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name,factory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE Alumno("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT, " +
                "apellido TEXT, " +
                "correo TEXT, " +
                "carrera TEXT) ";
        db.execSQL(sql);

        String insert = "INSERT INTO Alumno VALUES(null, " +
                "'Daniel', "+
                "'Celestino Aquino', "+
                "'daniel@gmail.com', "+
                "'Sistemas')";
        db.execSQL(insert);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public Alumno elemento(int id) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM  Alumno WHERE _id=" + id;
        Cursor cursor = database.rawQuery(sql,null);
        try {
            if (cursor.moveToNext())
                return extraerAlumno(cursor);
            else
                return null;
        }catch (Exception e){
            Log.d("TAG", "Error elemento(id) AlumnoBD" + e.getMessage());
            throw  e;
        }finally {
            if (cursor != null) cursor.close();
        }

    }

    private Alumno extraerAlumno(Cursor cursor) {
        Alumno alumno = new Alumno();
        alumno.setId(cursor.getInt(0));
        alumno.setNombre(cursor.getString(1));
        alumno.setApellido(cursor.getString(2));
        alumno.setCorreo(cursor.getString(3));
        alumno.setCarrera(cursor.getString(4));

        return alumno;
    }

    @Override
    public Alumno elemento(String nombre) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM  Alumno WHERE nombre= '" + nombre + "'";
        Cursor cursor = database.rawQuery(sql,null);
        try {
            if (cursor.moveToNext())
                return extraerAlumno(cursor);
            else
                return null;
        }catch (Exception e){
            Log.d("TAG", "Error elemento(nombre) AlumnoBD" + e.getMessage());
            throw  e;
        }finally {
            if (cursor != null) cursor.close();
        }
    }

    @Override
    public List<Alumno> lista() {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM  Alumno ORDER BY nombre ASC";
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                AlumnoList.add(
                        new Alumno(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4))
                );
            }while (cursor.moveToNext());
        }
        cursor.close();
        return AlumnoList;
    }

    @Override
    public void agregar(Alumno alumno) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", alumno.getNombre());
        values.put("apellido", alumno.getApellido());
        values.put("correo", alumno.getCorreo());
        values.put("carrera", alumno.getCarrera());
        database.insert("Alumno", null, values);

    }

    @Override
    public void actualizar(int id, Alumno alumno) {

        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        ContentValues values = new ContentValues();

        values.put("nombre", alumno.getNombre());
        values.put("apellido", alumno.getApellido());
        values.put("correo", alumno.getCorreo());
        values.put("carrera", alumno.getCarrera());
        database.update("Alumno", values,"_id=?", parametros);


    }

    @Override
    public void eliminar(int id) {

        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};

        database.delete("Alumno", "_id=?", parametros);

    }
}
