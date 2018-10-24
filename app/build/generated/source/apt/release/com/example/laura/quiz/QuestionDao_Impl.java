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
public class QuestionDao_Impl implements QuestionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuestionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public QuestionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestionEntity = new EntityInsertionAdapter<QuestionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `question_table`(`id`,`difficulty`,`type`,`data`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, QuestionEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getDiff() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDiff());
        }
        if (value.getTipo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTipo());
        }
        if (value.getData() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getData());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM question_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(QuestionEntity... points) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestionEntity.insert(points);
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
  public LiveData<List<QuestionEntity>> getQuestionsByDiff(String diffSelected) {
    final String _sql = "SELECT * FROM question_table WHERE difficulty = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (diffSelected == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, diffSelected);
    }
    return new ComputableLiveData<List<QuestionEntity>>() {
      private Observer _observer;

      @Override
      protected List<QuestionEntity> compute() {
        if (_observer == null) {
          _observer = new Observer("question_table") {
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
          final int _cursorIndexOfDiff = _cursor.getColumnIndexOrThrow("difficulty");
          final int _cursorIndexOfTipo = _cursor.getColumnIndexOrThrow("type");
          final int _cursorIndexOfData = _cursor.getColumnIndexOrThrow("data");
          final List<QuestionEntity> _result = new ArrayList<QuestionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final QuestionEntity _item;
            final String _tmpDiff;
            _tmpDiff = _cursor.getString(_cursorIndexOfDiff);
            final String _tmpTipo;
            _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            _item = new QuestionEntity(_tmpTipo,_tmpDiff,_tmpData);
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
