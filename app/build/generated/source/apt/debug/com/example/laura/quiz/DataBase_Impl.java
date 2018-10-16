package com.example.laura.quiz;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class DataBase_Impl extends DataBase {
  private volatile QuestionDao _questionDao;

  private volatile PointsDao _pointsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `questions_table` (`id` INTEGER NOT NULL, `tipo` TEXT NOT NULL, `data` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `points_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userName` TEXT NOT NULL, `points` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"eef190d26289f72ff5d043ac591385d7\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `questions_table`");
        _db.execSQL("DROP TABLE IF EXISTS `points_table`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsQuestionsTable = new HashMap<String, TableInfo.Column>(3);
        _columnsQuestionsTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsQuestionsTable.put("tipo", new TableInfo.Column("tipo", "TEXT", true, 0));
        _columnsQuestionsTable.put("data", new TableInfo.Column("data", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuestionsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuestionsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuestionsTable = new TableInfo("questions_table", _columnsQuestionsTable, _foreignKeysQuestionsTable, _indicesQuestionsTable);
        final TableInfo _existingQuestionsTable = TableInfo.read(_db, "questions_table");
        if (! _infoQuestionsTable.equals(_existingQuestionsTable)) {
          throw new IllegalStateException("Migration didn't properly handle questions_table(com.example.laura.quiz.QuestionEntity).\n"
                  + " Expected:\n" + _infoQuestionsTable + "\n"
                  + " Found:\n" + _existingQuestionsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsPointsTable = new HashMap<String, TableInfo.Column>(3);
        _columnsPointsTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPointsTable.put("userName", new TableInfo.Column("userName", "TEXT", true, 0));
        _columnsPointsTable.put("points", new TableInfo.Column("points", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPointsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPointsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPointsTable = new TableInfo("points_table", _columnsPointsTable, _foreignKeysPointsTable, _indicesPointsTable);
        final TableInfo _existingPointsTable = TableInfo.read(_db, "points_table");
        if (! _infoPointsTable.equals(_existingPointsTable)) {
          throw new IllegalStateException("Migration didn't properly handle points_table(com.example.laura.quiz.PointEntity).\n"
                  + " Expected:\n" + _infoPointsTable + "\n"
                  + " Found:\n" + _existingPointsTable);
        }
      }
    }, "eef190d26289f72ff5d043ac591385d7", "84fcac3a41a40a25c857e65de2f7e587");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "questions_table","points_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `questions_table`");
      _db.execSQL("DELETE FROM `points_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public QuestionDao questionDao() {
    if (_questionDao != null) {
      return _questionDao;
    } else {
      synchronized(this) {
        if(_questionDao == null) {
          _questionDao = new QuestionDao_Impl(this);
        }
        return _questionDao;
      }
    }
  }

  @Override
  public PointsDao pointsDao() {
    if (_pointsDao != null) {
      return _pointsDao;
    } else {
      synchronized(this) {
        if(_pointsDao == null) {
          _pointsDao = new PointsDao_Impl(this);
        }
        return _pointsDao;
      }
    }
  }
}
