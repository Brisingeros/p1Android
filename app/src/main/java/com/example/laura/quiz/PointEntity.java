package com.example.laura.quiz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "points_table")
public class PointEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "userName")
    private String userName;

    @NonNull
    @ColumnInfo(name = "points")
    private int points;

    public PointEntity(String userName, int points){

        //this.id = id;
        this.userName = userName;
        this.points = points;

    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getPoints() {
        return points;
    }


    public void setId(@NonNull int id) {
        this.id = id;
    }
}
