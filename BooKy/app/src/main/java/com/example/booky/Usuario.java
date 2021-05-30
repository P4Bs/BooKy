package com.example.booky;

import android.graphics.Bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {

    private int ID;
    private String nombre;
    private String contrasenya;
    private String NumTelefono;
    private String correo;
    private boolean esAdmin;

    //Usuario de la base de datos
    public Usuario(int ID, String correo, String contrasenya){
        this.ID = ID;
        this.correo= correo;
        this.contrasenya = getSHA1(contrasenya);
    }

    public Usuario(int ID, String nombre, String contrasenya,String NumTelefono,String correo,boolean esAdmin ){
        this.ID = ID;
        this.esAdmin = esAdmin;
        this.nombre = nombre;
        this.NumTelefono = NumTelefono;
        this.correo= correo;
        this.contrasenya = getSHA1(contrasenya);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return this.contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = getSHA1(contrasenya);
    }

    public String getNumTelefono() {
        return NumTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        NumTelefono = numTelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public String toString(){
        String a = "";
        a += "-ID: " + ID + "\n";
        a += "-Nombre: " + nombre + "\n";
        a += "-Contraseña: " + contrasenya + "\n";
        a += "-NumTelefono: " + NumTelefono + "\n";
        a += "-Correo: " + correo + "\n";
        a += "-Es admin: " + esAdmin + "\n";
        return a ;
    }


    // TODO: ENCONTRAR UN FORMA DE QUITAR ESTO
    private static String getSHA1(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
