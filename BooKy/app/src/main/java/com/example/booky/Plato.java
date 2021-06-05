package com.example.booky;

public class Plato {
    private int ID;
    private String Nombre;
    private String Descripcion;
    private String Precio;
    private String Alergenos;

    public Plato(int ID, String nombre, String descripcion, String alergenos, String precio){
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

    public String getPrecio() {
        return this.Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }
    
    @Override
    public String toString() {
        String descripcion;
        if(this.Descripcion.length() > 40){
            descripcion = this.Descripcion.substring(0, 40) + "...";
        } else{
            descripcion = this.getDescripcion();
        }
        String a = "";
        a += "-Nombre: " + this.Nombre + "\n";
        a += "-Descripcion: " + descripcion + "\n";
        a += "-Alergenos: " + this.Alergenos + "\n";
        a += "-Precio: " + this.Precio + " €";
        return a;
    }
}
