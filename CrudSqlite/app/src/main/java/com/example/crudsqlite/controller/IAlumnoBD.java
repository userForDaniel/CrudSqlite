package com.example.crudsqlite.controller;

import com.example.crudsqlite.model.Alumno;

import java.util.List;

public interface IAlumnoBD {

    Alumno elemento(int id);
    Alumno elemento(String nombre);

    List<Alumno> lista();
    void agregar(Alumno alumno);
    void actualizar(int id, Alumno alumno);
    void eliminar(int id);
}
