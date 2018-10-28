package com.example.laura.quiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "user_table")
public class UserEntity implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "userName")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "puntuacion_max")
    private int punt_max;

    @NonNull
    @ColumnInfo(name = "num_partidas")
    private int num_partidas;

    @NonNull
    @ColumnInfo(name = "ult_part")
    private String ult_partida;

    @NonNull
    @ColumnInfo(name = "foto")
    private String path_foto;

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public void setPunt_max(@NonNull int punt_max) {
        this.punt_max = punt_max;
    }

    public void setNum_partidas(@NonNull int num_partidas) {
        this.num_partidas = num_partidas;
    }

    public void setUlt_partida(@NonNull String ult_partida) {
        this.ult_partida = ult_partida;
    }

    public void setPath_foto(@NonNull String path_foto) {
        this.path_foto = path_foto;
    }

    public UserEntity(String nombre, int punt_max, int num_partidas, String ult_partida, String path_foto) {

        this.nombre = nombre;
        this.punt_max = punt_max;
        this.num_partidas = num_partidas;
        this.ult_partida = ult_partida;
        this.path_foto = path_foto;

    }

    public UserEntity() {

        this.id = -1;
        this.nombre = "Anónimo";
        this.punt_max = 0;
        this.num_partidas = 0;
        this.ult_partida = "";
        this.path_foto = "mr_x";

    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    @NonNull
    public int getPunt_max() {
        return punt_max;
    }

    @NonNull
    public int getNum_partidas() {
        return num_partidas;
    }

    @NonNull
    public String getUlt_partida() {
        return ult_partida;
    }

    @NonNull
    public String getPath_foto() {
        return path_foto;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
