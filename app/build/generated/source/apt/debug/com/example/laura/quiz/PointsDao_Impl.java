package com.example.laura.quiz;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

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
        return "INSERT OR ABORT INTO `points_table`(`id`,`userName`,`points`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PointEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getUserName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserName());
        }
        stmt.bindLong(3, value.getPoints());
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
  public void insert(PointEntity points) {
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
  public List<PointEntity> getAllPoints() {
    final String _sql = "SELECT * FROM points_table ORDER BY points DESC LIMIT 10";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfUserName = _cursor.getColumnIndexOrThrow("userName");
      final int _cursorIndexOfPoints = _cursor.getColumnIndexOrThrow("points");
      final List<PointEntity> _result = new ArrayList<PointEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PointEntity _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        final int _tmpPoints;
        _tmpPoints = _cursor.getInt(_cursorIndexOfPoints);
        _item = new PointEntity(_tmpId,_tmpUserName,_tmpPoints);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
