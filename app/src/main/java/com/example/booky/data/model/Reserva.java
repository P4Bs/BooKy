package com.example.booky.data.model;

public class Reserva {
    private int ID;
    private String Mesa;
    private String Fecha;
    private int Ocupantes;
    private boolean Cancelada;
    private String intervaloTiempo;

    public Reserva(int ID, String mesa, String fecha, int ocupantes, String intervaloTiempo){
        this.setID(ID);
        this.setMesa(mesa);
        this.setFecha(fecha);
        this.setOcupantes(ocupantes);
        this.setIntervaloTiempo(intervaloTiempo);
        this.setCancelada(false);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMesa() {
        return Mesa;
    }

    public void setMesa(String mesa) {
        Mesa = mesa;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getOcupantes() {
        return Ocupantes;
    }

    public void setOcupantes(int ocupantes) {
        Ocupantes = ocupantes;
    }

    public boolean isCancelada() {
        return Cancelada;
    }

    public void setCancelada(boolean cancelada) {
        Cancelada = cancelada;
    }

    public String getIntervaloTiempo() {
        return intervaloTiempo;
    }

    public void setIntervaloTiempo(String intervaloTiempo) {
        this.intervaloTiempo = intervaloTiempo;
    }
}
