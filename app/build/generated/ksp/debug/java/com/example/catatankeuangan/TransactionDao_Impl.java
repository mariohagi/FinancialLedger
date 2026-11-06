package com.example.catatankeuangan;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Transaction> __deletionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __updateAdapterOfTransaction;

  private final SharedSQLiteStatement __preparedStmtOfUpdateWalletSource;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`id`,`description`,`amount`,`type`,`category`,`walletSource`,`date`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDescription());
        statement.bindDouble(3, entity.getAmount());
        statement.bindString(4, __Type_enumToString(entity.getType()));
        statement.bindString(5, entity.getCategory());
        statement.bindString(6, entity.getWalletSource());
        final Long _tmp = __converters.dateToTimestamp(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp);
        }
      }
    };
    this.__deletionAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`description` = ?,`amount` = ?,`type` = ?,`category` = ?,`walletSource` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDescription());
        statement.bindDouble(3, entity.getAmount());
        statement.bindString(4, __Type_enumToString(entity.getType()));
        statement.bindString(5, entity.getCategory());
        statement.bindString(6, entity.getWalletSource());
        final Long _tmp = __converters.dateToTimestamp(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, _tmp);
        }
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateWalletSource = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE transactions SET walletSource = ? WHERE walletSource = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Transaction tx) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransaction.insert(tx);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Transaction tx) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransaction.handle(tx);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Transaction tx) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTransaction.handle(tx);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateWalletSource(final String oldName, final String newName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateWalletSource.acquire();
    int _argIndex = 1;
    _stmt.bindString(_argIndex, newName);
    _argIndex = 2;
    _stmt.bindString(_argIndex, oldName);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateWalletSource.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Transaction> getAll() {
    final String _sql = "SELECT * FROM transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfWalletSource = CursorUtil.getColumnIndexOrThrow(_cursor, "walletSource");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Transaction _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final Transaction.Type _tmpType;
        _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        final String _tmpWalletSource;
        _tmpWalletSource = _cursor.getString(_cursorIndexOfWalletSource);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        final Date _tmp_1 = __converters.fromTimestamp(_tmp);
        if (_tmp_1 == null) {
          throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
        } else {
          _tmpDate = _tmp_1;
        }
        _item = new Transaction(_tmpId,_tmpDescription,_tmpAmount,_tmpType,_tmpCategory,_tmpWalletSource,_tmpDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<Transaction>> getAllFlow() {
    final String _sql = "SELECT * FROM transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfWalletSource = CursorUtil.getColumnIndexOrThrow(_cursor, "walletSource");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final Transaction.Type _tmpType;
            _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpWalletSource;
            _tmpWalletSource = _cursor.getString(_cursorIndexOfWalletSource);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            final Date _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpDate = _tmp_1;
            }
            _item = new Transaction(_tmpId,_tmpDescription,_tmpAmount,_tmpType,_tmpCategory,_tmpWalletSource,_tmpDate);
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
    });
  }

  @Override
  public List<Transaction> getByType(final Transaction.Type type) {
    final String _sql = "SELECT * FROM transactions WHERE type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __Type_enumToString(type));
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfWalletSource = CursorUtil.getColumnIndexOrThrow(_cursor, "walletSource");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Transaction _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final Transaction.Type _tmpType;
        _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
        final String _tmpCategory;
        _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        final String _tmpWalletSource;
        _tmpWalletSource = _cursor.getString(_cursorIndexOfWalletSource);
        final Date _tmpDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDate);
        }
        final Date _tmp_1 = __converters.fromTimestamp(_tmp);
        if (_tmp_1 == null) {
          throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
        } else {
          _tmpDate = _tmp_1;
        }
        _item = new Transaction(_tmpId,_tmpDescription,_tmpAmount,_tmpType,_tmpCategory,_tmpWalletSource,_tmpDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<Transaction>> getByTypeFlow(final Transaction.Type type) {
    final String _sql = "SELECT * FROM transactions WHERE type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __Type_enumToString(type));
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfWalletSource = CursorUtil.getColumnIndexOrThrow(_cursor, "walletSource");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final Transaction.Type _tmpType;
            _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpWalletSource;
            _tmpWalletSource = _cursor.getString(_cursorIndexOfWalletSource);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            final Date _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpDate = _tmp_1;
            }
            _item = new Transaction(_tmpId,_tmpDescription,_tmpAmount,_tmpType,_tmpCategory,_tmpWalletSource,_tmpDate);
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
    });
  }

  @Override
  public double getBalanceByWalletSource(final String walletSource) {
    final String _sql = "SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE walletSource = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, walletSource);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0.0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<Double> getBalanceByWalletSourceFlow(final String walletSource) {
    final String _sql = "SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE walletSource = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, walletSource);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<Double>() {
      @Override
      @NonNull
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final double _tmp;
            _tmp = _cursor.getDouble(0);
            _result = _tmp;
          } else {
            _result = 0.0;
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
    });
  }

  @Override
  public Flow<List<Transaction>> getByYearMonthFlow(final int year, final int month) {
    final String _sql = "\n"
            + "        SELECT * FROM transactions\n"
            + "        WHERE CAST(strftime('%Y', date/1000, 'unixepoch') AS INTEGER) = ?\n"
            + "          AND CAST(strftime('%m', date/1000, 'unixepoch') AS INTEGER) = ?\n"
            + "        ORDER BY date DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, year);
    _argIndex = 2;
    _statement.bindLong(_argIndex, month);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfWalletSource = CursorUtil.getColumnIndexOrThrow(_cursor, "walletSource");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final Transaction.Type _tmpType;
            _tmpType = __Type_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpWalletSource;
            _tmpWalletSource = _cursor.getString(_cursorIndexOfWalletSource);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            final Date _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.Date', but it was NULL.");
            } else {
              _tmpDate = _tmp_1;
            }
            _item = new Transaction(_tmpId,_tmpDescription,_tmpAmount,_tmpType,_tmpCategory,_tmpWalletSource,_tmpDate);
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
    });
  }

  @Override
  public Object getDescriptionSuggestions(final String query,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "\n"
            + "        SELECT DISTINCT description FROM transactions\n"
            + "        WHERE LOWER(description) LIKE '%' || LOWER(?) || '%'\n"
            + "          AND description != ''\n"
            + "        ORDER BY description\n"
            + "        LIMIT 8\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __Type_enumToString(@NonNull final Transaction.Type _value) {
    switch (_value) {
      case INCOME: return "INCOME";
      case EXPENSE: return "EXPENSE";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private Transaction.Type __Type_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "INCOME": return Transaction.Type.INCOME;
      case "EXPENSE": return Transaction.Type.EXPENSE;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
