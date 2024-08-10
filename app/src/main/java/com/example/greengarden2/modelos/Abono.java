package com.example.greengarden2.modelos;

public class Abono {
    private float Kilogramo;
    private float precio;
    private String mes;

    public Abono(float kilogramo, float precio, String mes) {
        Kilogramo = kilogramo;
        this.precio = precio;
        this.mes = mes;
    }

    public float getKilogramo() {
        return Kilogramo;
    }

    public void setKilogramo(float kilogramo) {
        Kilogramo = kilogramo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
