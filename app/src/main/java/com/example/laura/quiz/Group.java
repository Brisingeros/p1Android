package com.example.laura.quiz;

public class Group {

    private int id;
    private String value;
    private String tipo;

    public Group(int i, String v, String t){
        this.id = i;
        this.value = v;
        this.tipo = t;
    }

    public int getId() {
        return id;
    }


    public String getValue() {
        return value;
    }

    public String getTipo() {
        return tipo;
    }
}
