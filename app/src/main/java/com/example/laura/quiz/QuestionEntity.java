package com.example.laura.quiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "question_table")
public class QuestionEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "difficulty")
    private String diff;

    @NonNull
    @ColumnInfo(name = "type")
    private String tipo;

    @NonNull
    @ColumnInfo(name = "data")
    private String data;

    public QuestionEntity(String tipo, String diff, String data){

        this.tipo = tipo;
        this.diff = diff;
        this.data = data;

    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDiff() {
        return diff;
    }

    public String getData() {
        return data;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }
}
