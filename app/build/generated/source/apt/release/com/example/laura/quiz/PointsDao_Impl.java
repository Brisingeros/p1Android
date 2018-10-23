package com.example.laura.quiz;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import android.support.annotation.NonNull;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class PointsDao_Impl implements PointsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPointEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PointsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPointEntity = new EntityInsertionAdapter<PointEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `points_table`(`id`,`difficulty`,`userName`,`points`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PointEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getDifficulty() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDifficulty());
        }
        if (value.getUserName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUserName());
        }
        stmt.bindLong(4, value.getPoints());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM points_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(PointEntity... points) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPointEntity.insert(points);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<PointEntity>> getAllPoints(String dif) {
    final String _sql = "SELECT * FROM points_table WHERE difficulty = ? ORDER BY points DESC LIMIT 4";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (dif == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, dif);
    }
    return new ComputableLiveData<List<PointEntity>>() {
      private Observer _observer;

      @Override
      protected List<PointEntity> compute() {
        if (_observer == null) {
          _observer = new Observer("points_table") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfDifficulty = _cursor.getColumnIndexOrThrow("difficulty");
          final int _cursorIndexOfUserName = _cursor.getColumnIndexOrThrow("userName");
          final int _cursorIndexOfPoints = _cursor.getColumnIndexOrThrow("points");
          final List<PointEntity> _result = new ArrayList<PointEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PointEntity _item;
            final String _tmpDifficulty;
            _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
            final String _tmpUserName;
            _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
            final int _tmpPoints;
            _tmpPoints = _cursor.getInt(_cursorIndexOfPoints);
            _item = new PointEntity(_tmpDifficulty,_tmpUserName,_tmpPoints);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
