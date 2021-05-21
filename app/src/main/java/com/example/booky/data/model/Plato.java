package com.example.booky.data.model;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Plato {
    private int ID;
    private String Nombre;
    private String Descripcion;
    private Bitmap Imagen;
    private List<String> Alergenos;
    private int Precio;

    public Plato(int ID, String nombre, Bitmap imagen, String descripcion, List<String> alergenos, int precio){
        this.setID(ID);
        this.setNombre(nombre);
        this.setImagen(imagen);
        this.setDescripcion(descripcion);
        this.setAlergenos(alergenos);
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

    public byte[] getImagenAsByteArray() {
        return getBitmapAsByteArray(this.Imagen);
    }

    public void setImagen(Bitmap imagen) {
        Imagen = imagen;
    }

    public String getAlergenos() {
        return uneAlergenos(Alergenos);
    }

    public void setAlergenos(List<String> alergenos) {
        Alergenos = alergenos;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private String uneAlergenos(List<String> alergenos){
        String alergUnidos = "";
        for(int i = 0; i < alergenos.size(); i++){
            alergUnidos += alergenos.get(i);
            if(i < alergenos.size() - 1){
                alergUnidos += ", ";
            }
        }
        return alergUnidos;
    }
}
