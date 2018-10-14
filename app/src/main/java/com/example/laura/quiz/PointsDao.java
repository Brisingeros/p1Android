package com.example.laura.quiz;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PointsDao {

    @Insert
    void insert(PointEntity points);

    @Query("DELETE FROM points_table")
    void deleteAll();

    @Query("SELECT * FROM points_table ORDER BY points DESC LIMIT 10")
    LiveData<List<PointEntity>> getAllPoints();

}
