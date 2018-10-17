package com.example.laura.quiz;

public class User {

    private String nombre;
    private String puntosMax;
    private String ult_partida;
    private String num_partidas;
    private String imgPath;

    public User(String n, String pM, String fecha, String nP, String path) {

        this.nombre = n;
        this.puntosMax = pM;
        this.ult_partida = fecha;
        this.num_partidas = nP;
        this.imgPath = path;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntosMax() {
        return puntosMax;
    }

    public void setPuntosMax(String puntosMax) {
        this.puntosMax = puntosMax;
    }

    public String getUlt_partida() {
        return ult_partida;
    }

    public void setUlt_partida(String ult_partida) {
        this.ult_partida = ult_partida;
    }

    public String getNum_partidas() {
        return num_partidas;
    }

    public void setNum_partidas(String num_partidas) {
        this.num_partidas = num_partidas;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
