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

  private volatile UserDao _userDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(12) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `question_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `difficulty` TEXT NOT NULL, `type` TEXT NOT NULL, `data` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `points_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `difficulty` TEXT NOT NULL, `userName` TEXT NOT NULL, `points` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user_table` (`userName` TEXT NOT NULL, `puntuacion_max` INTEGER NOT NULL, `num_partidas` INTEGER NOT NULL, `ult_part` TEXT NOT NULL, `foto` TEXT NOT NULL, PRIMARY KEY(`userName`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"7f86fabeab21df1c6a57a84f2aaec2a4\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `question_table`");
        _db.execSQL("DROP TABLE IF EXISTS `points_table`");
        _db.execSQL("DROP TABLE IF EXISTS `user_table`");
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
        final HashMap<String, TableInfo.Column> _columnsQuestionTable = new HashMap<String, TableInfo.Column>(4);
        _columnsQuestionTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsQuestionTable.put("difficulty", new TableInfo.Column("difficulty", "TEXT", true, 0));
        _columnsQuestionTable.put("type", new TableInfo.Column("type", "TEXT", true, 0));
        _columnsQuestionTable.put("data", new TableInfo.Column("data", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuestionTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuestionTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuestionTable = new TableInfo("question_table", _columnsQuestionTable, _foreignKeysQuestionTable, _indicesQuestionTable);
        final TableInfo _existingQuestionTable = TableInfo.read(_db, "question_table");
        if (! _infoQuestionTable.equals(_existingQuestionTable)) {
          throw new IllegalStateException("Migration didn't properly handle question_table(com.example.laura.quiz.QuestionEntity).\n"
                  + " Expected:\n" + _infoQuestionTable + "\n"
                  + " Found:\n" + _existingQuestionTable);
        }
        final HashMap<String, TableInfo.Column> _columnsPointsTable = new HashMap<String, TableInfo.Column>(4);
        _columnsPointsTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPointsTable.put("difficulty", new TableInfo.Column("difficulty", "TEXT", true, 0));
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
        final HashMap<String, TableInfo.Column> _columnsUserTable = new HashMap<String, TableInfo.Column>(5);
        _columnsUserTable.put("userName", new TableInfo.Column("userName", "TEXT", true, 1));
        _columnsUserTable.put("puntuacion_max", new TableInfo.Column("puntuacion_max", "INTEGER", true, 0));
        _columnsUserTable.put("num_partidas", new TableInfo.Column("num_partidas", "INTEGER", true, 0));
        _columnsUserTable.put("ult_part", new TableInfo.Column("ult_part", "TEXT", true, 0));
        _columnsUserTable.put("foto", new TableInfo.Column("foto", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTable = new TableInfo("user_table", _columnsUserTable, _foreignKeysUserTable, _indicesUserTable);
        final TableInfo _existingUserTable = TableInfo.read(_db, "user_table");
        if (! _infoUserTable.equals(_existingUserTable)) {
          throw new IllegalStateException("Migration didn't properly handle user_table(com.example.laura.quiz.UserEntity).\n"
                  + " Expected:\n" + _infoUserTable + "\n"
                  + " Found:\n" + _existingUserTable);
        }
      }
    }, "7f86fabeab21df1c6a57a84f2aaec2a4", "4f719408286b582b7726b9e811500240");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "question_table","points_table","user_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `question_table`");
      _db.execSQL("DELETE FROM `points_table`");
      _db.execSQL("DELETE FROM `user_table`");
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

  @Override
  public UserDao UserDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
