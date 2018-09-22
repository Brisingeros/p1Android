package com.example.laura.quiz;

import android.graphics.drawable.Drawable;

public class Group {

    private int id;
    private String value;
    private String tipo;
    private int idImg;

    public Group(int i, String v, String t){
        this.id = i;
        this.value = v;
        this.tipo = t;
        this.idImg = -1;
    }

    public Group(int i, String v, String t, int r){
        this.id = i;
        this.value = v;
        this.tipo = t;
        this.idImg = r;

    }

    public int getId() {
        return id;
    }

    public int getIdImg() { return idImg; }

    public String getValue() {
        return value;
    }

    public String getTipo() {
        return tipo;
    }
}
