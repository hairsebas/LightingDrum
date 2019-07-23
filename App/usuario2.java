package com.example.bateria;

public class usuario2 {
    private int ID;
    private String Puntaje;
    private String Fecha;


    public usuario2() {
    }

    public usuario2(int ID, String puntaje, String fecha) {
        this.ID = ID;
        Puntaje = puntaje;
        Fecha = fecha;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(String puntaje) {
        Puntaje = puntaje;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    @Override
    public String toString() {
        return   Fecha;
    }


}

