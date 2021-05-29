package com.example.booky;

public class Calificacion {
    private int ID;
    private int IDUsuario;
    private int IDPlato;
    private int Nota;
    private String Comentario;

    public Calificacion(int ID, int IDUsuario, int IDPlato, int Nota, String comentario){
        this.ID = ID;
        this.IDUsuario = IDUsuario;
        this.IDPlato = IDPlato;
        this.Nota = Nota;
        this.Comentario = comentario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNota() {
        return Nota;
    }

    public void setNota(int nota) {
        Nota = nota;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        this.Comentario = comentario;
    }

    public int getIDPlato() {
        return IDPlato;
    }

    public void setIDPlato(int IDPlato){
        this.IDPlato = IDPlato;
    }

    public int getIDUsuario(){
        return this.IDUsuario;
    }

    public void setIDUsuario(int IDUsuario){
        this.IDUsuario = IDUsuario;
    }
}
