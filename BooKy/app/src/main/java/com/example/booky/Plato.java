package com.example.booky;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.StringJoiner;

public class Plato {
    private int ID;
    private String Nombre;
    private String Descripcion;
    private int Precio;
    private String Alergenos;

    public Plato(int ID, String nombre, String descripcion, String alergenos, int precio){
        this.setID(ID);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.Alergenos = alergenos;
        this.setPrecio(precio);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getAlergenos() { return Alergenos; }

    public void setAlergenos(String alergenos) {
        Alergenos = alergenos;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }
    
    @Override
    public String toString() {
        String a = ""; 
        a += "Nombre: " + Nombre + " .Descripcion: " + Descripcion + " .Alergenos: " + Alergenos + " .Precio: " + Precio + "€.";
        return a;
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


}
