package com.example.booky;

public class Calificacion {
    private int ID;
    private int IDUsuario;
    private int IDPlato;
    private String Nota;
    private String Comentario;

    public Calificacion(int ID, int IDUsuario, int IDPlato, String Nota, String comentario){
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

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
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

    @Override
    public String toString() {
        String comentario;
        if(this.Comentario.length() > 40){
            comentario = this.Comentario.substring(0, 40) + "...";
        } else{
            comentario = this.getComentario();
        }
        String a = "";
        a += "-IDUsuario: " + this.IDUsuario + "\n";
        a += "-Calificacion: " + this.Nota + "/10\n";
        a += "-Comentario: " + comentario + "\n";
        return a;
    }
}
