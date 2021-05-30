package com.example.booky;

public class Reserva {
    private int ID;
    private int Mesa;
    private int Dia;
    private int Mes;
    private int Ocupantes;
    private String intervaloTiempo;

    public Reserva(int ID, int mesa, int Dia, int Mes, int ocupantes, String intervaloTiempo){
        this.setID(ID);
        this.setMesa(mesa);
        this.setDia(Dia);
        this.setMes(Mes);
        this.setOcupantes(ocupantes);
        this.setIntervaloTiempo(intervaloTiempo);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMesa() {
        return Mesa;
    }

    public void setMesa(int mesa) {
        Mesa = mesa;
    }

    public int getDia(){
        return this.Dia;
    }

    public void setDia(int dia){
        this.Dia = dia;
    }

    public int getMes(){
        return this.Mes;
    }

    public void setMes(int mes){
        this.Mes = mes;
    }

    public int getOcupantes() {
        return Ocupantes;
    }

    public void setOcupantes(int ocupantes) {
        Ocupantes = ocupantes;
    }

    public String getIntervaloTiempo() {
        return intervaloTiempo;
    }

    public void setIntervaloTiempo(String intervaloTiempo) {
        this.intervaloTiempo = intervaloTiempo;
    }
}
