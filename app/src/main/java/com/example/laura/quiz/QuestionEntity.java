package com.example.laura.quiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "questions_table")
public class QuestionEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name="type")
    private String type;

    @NonNull
    @ColumnInfo(name="data")
    private String data;

    public QuestionEntity(int id, String type, String data){

        this.id = id;
        this.type = type;
        this.data = data;

    }

    public int getId(){

        return this.id;

    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

}
