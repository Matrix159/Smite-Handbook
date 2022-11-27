package com.matrix.data.local.db.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.matrix.data.local.db.Converters;
import com.matrix.data.local.db.entity.ItemDescription;
import com.matrix.data.local.db.entity.ItemEntity;
import com.matrix.data.local.db.model.DescriptionValue;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemDao_Impl implements ItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ItemEntity> __insertionAdapterOfItemEntity;

  private final Converters __converters = new Converters();

  public ItemDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItemEntity = new EntityInsertionAdapter<ItemEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `items` (`id`,`patchVersion`,`activeFlag`,`childItemID`,`deviceName`,`glyph`,`iconID`,`itemTier`,`price`,`restrictedRoles`,`rootItemID`,`shortDesc`,`startingItem`,`type`,`itemIconURL`,`description`,`menuItems`,`secondaryDescription`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ItemEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getPatchVersion() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPatchVersion());
        }
        final int _tmp = value.getActiveFlag() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        stmt.bindLong(4, value.getChildItemID());
        if (value.getDeviceName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDeviceName());
        }
        final int _tmp_1 = value.getGlyph() ? 1 : 0;
        stmt.bindLong(6, _tmp_1);
        stmt.bindLong(7, value.getIconID());
        stmt.bindLong(8, value.getItemTier());
        stmt.bindLong(9, value.getPrice());
        if (value.getRestrictedRoles() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getRestrictedRoles());
        }
        stmt.bindLong(11, value.getRootItemID());
        if (value.getShortDesc() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getShortDesc());
        }
        final int _tmp_2 = value.getStartingItem() ? 1 : 0;
        stmt.bindLong(13, _tmp_2);
        if (value.getType() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getType());
        }
        if (value.getItemIconURL() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getItemIconURL());
        }
        final ItemDescription _tmpItemDescription = value.getItemDescription();
        if(_tmpItemDescription != null) {
          if (_tmpItemDescription.getDescription() == null) {
            stmt.bindNull(16);
          } else {
            stmt.bindString(16, _tmpItemDescription.getDescription());
          }
          final String _tmp_3 = __converters.fromList(_tmpItemDescription.getMenuItems());
          if (_tmp_3 == null) {
            stmt.bindNull(17);
          } else {
            stmt.bindString(17, _tmp_3);
          }
          if (_tmpItemDescription.getSecondaryDescription() == null) {
            stmt.bindNull(18);
          } else {
            stmt.bindString(18, _tmpItemDescription.getSecondaryDescription());
          }
        } else {
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
        }
      }
    };
  }

  @Override
  public Object insertAll(final List<ItemEntity> items,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfItemEntity.insert(items);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<ItemEntity>> getAll() {
    final String _sql = "SELECT * FROM items";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<List<ItemEntity>>() {
      @Override
      public List<ItemEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatchVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "patchVersion");
          final int _cursorIndexOfActiveFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "activeFlag");
          final int _cursorIndexOfChildItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "childItemID");
          final int _cursorIndexOfDeviceName = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceName");
          final int _cursorIndexOfGlyph = CursorUtil.getColumnIndexOrThrow(_cursor, "glyph");
          final int _cursorIndexOfIconID = CursorUtil.getColumnIndexOrThrow(_cursor, "iconID");
          final int _cursorIndexOfItemTier = CursorUtil.getColumnIndexOrThrow(_cursor, "itemTier");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRestrictedRoles = CursorUtil.getColumnIndexOrThrow(_cursor, "restrictedRoles");
          final int _cursorIndexOfRootItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "rootItemID");
          final int _cursorIndexOfShortDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "shortDesc");
          final int _cursorIndexOfStartingItem = CursorUtil.getColumnIndexOrThrow(_cursor, "startingItem");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfItemIconURL = CursorUtil.getColumnIndexOrThrow(_cursor, "itemIconURL");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfMenuItems = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItems");
          final int _cursorIndexOfSecondaryDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "secondaryDescription");
          final List<ItemEntity> _result = new ArrayList<ItemEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ItemEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpPatchVersion;
            if (_cursor.isNull(_cursorIndexOfPatchVersion)) {
              _tmpPatchVersion = null;
            } else {
              _tmpPatchVersion = _cursor.getString(_cursorIndexOfPatchVersion);
            }
            final boolean _tmpActiveFlag;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActiveFlag);
            _tmpActiveFlag = _tmp != 0;
            final int _tmpChildItemID;
            _tmpChildItemID = _cursor.getInt(_cursorIndexOfChildItemID);
            final String _tmpDeviceName;
            if (_cursor.isNull(_cursorIndexOfDeviceName)) {
              _tmpDeviceName = null;
            } else {
              _tmpDeviceName = _cursor.getString(_cursorIndexOfDeviceName);
            }
            final boolean _tmpGlyph;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfGlyph);
            _tmpGlyph = _tmp_1 != 0;
            final int _tmpIconID;
            _tmpIconID = _cursor.getInt(_cursorIndexOfIconID);
            final int _tmpItemTier;
            _tmpItemTier = _cursor.getInt(_cursorIndexOfItemTier);
            final int _tmpPrice;
            _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
            final String _tmpRestrictedRoles;
            if (_cursor.isNull(_cursorIndexOfRestrictedRoles)) {
              _tmpRestrictedRoles = null;
            } else {
              _tmpRestrictedRoles = _cursor.getString(_cursorIndexOfRestrictedRoles);
            }
            final int _tmpRootItemID;
            _tmpRootItemID = _cursor.getInt(_cursorIndexOfRootItemID);
            final String _tmpShortDesc;
            if (_cursor.isNull(_cursorIndexOfShortDesc)) {
              _tmpShortDesc = null;
            } else {
              _tmpShortDesc = _cursor.getString(_cursorIndexOfShortDesc);
            }
            final boolean _tmpStartingItem;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfStartingItem);
            _tmpStartingItem = _tmp_2 != 0;
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpItemIconURL;
            if (_cursor.isNull(_cursorIndexOfItemIconURL)) {
              _tmpItemIconURL = null;
            } else {
              _tmpItemIconURL = _cursor.getString(_cursorIndexOfItemIconURL);
            }
            final ItemDescription _tmpItemDescription;
            if (! (_cursor.isNull(_cursorIndexOfDescription) && _cursor.isNull(_cursorIndexOfMenuItems) && _cursor.isNull(_cursorIndexOfSecondaryDescription))) {
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              final List<DescriptionValue> _tmpMenuItems;
              final String _tmp_3;
              if (_cursor.isNull(_cursorIndexOfMenuItems)) {
                _tmp_3 = null;
              } else {
                _tmp_3 = _cursor.getString(_cursorIndexOfMenuItems);
              }
              _tmpMenuItems = __converters.toList(_tmp_3);
              final String _tmpSecondaryDescription;
              if (_cursor.isNull(_cursorIndexOfSecondaryDescription)) {
                _tmpSecondaryDescription = null;
              } else {
                _tmpSecondaryDescription = _cursor.getString(_cursorIndexOfSecondaryDescription);
              }
              _tmpItemDescription = new ItemDescription(_tmpDescription,_tmpMenuItems,_tmpSecondaryDescription);
            }  else  {
              _tmpItemDescription = null;
            }
            _item = new ItemEntity(_tmpId,_tmpPatchVersion,_tmpActiveFlag,_tmpChildItemID,_tmpDeviceName,_tmpGlyph,_tmpIconID,_tmpItemDescription,_tmpItemTier,_tmpPrice,_tmpRestrictedRoles,_tmpRootItemID,_tmpShortDesc,_tmpStartingItem,_tmpType,_tmpItemIconURL);
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
  public Flow<ItemEntity> getItem(final int itemId) {
    final String _sql = "SELECT * from items where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<ItemEntity>() {
      @Override
      public ItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatchVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "patchVersion");
          final int _cursorIndexOfActiveFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "activeFlag");
          final int _cursorIndexOfChildItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "childItemID");
          final int _cursorIndexOfDeviceName = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceName");
          final int _cursorIndexOfGlyph = CursorUtil.getColumnIndexOrThrow(_cursor, "glyph");
          final int _cursorIndexOfIconID = CursorUtil.getColumnIndexOrThrow(_cursor, "iconID");
          final int _cursorIndexOfItemTier = CursorUtil.getColumnIndexOrThrow(_cursor, "itemTier");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfRestrictedRoles = CursorUtil.getColumnIndexOrThrow(_cursor, "restrictedRoles");
          final int _cursorIndexOfRootItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "rootItemID");
          final int _cursorIndexOfShortDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "shortDesc");
          final int _cursorIndexOfStartingItem = CursorUtil.getColumnIndexOrThrow(_cursor, "startingItem");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfItemIconURL = CursorUtil.getColumnIndexOrThrow(_cursor, "itemIconURL");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfMenuItems = CursorUtil.getColumnIndexOrThrow(_cursor, "menuItems");
          final int _cursorIndexOfSecondaryDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "secondaryDescription");
          final ItemEntity _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpPatchVersion;
            if (_cursor.isNull(_cursorIndexOfPatchVersion)) {
              _tmpPatchVersion = null;
            } else {
              _tmpPatchVersion = _cursor.getString(_cursorIndexOfPatchVersion);
            }
            final boolean _tmpActiveFlag;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfActiveFlag);
            _tmpActiveFlag = _tmp != 0;
            final int _tmpChildItemID;
            _tmpChildItemID = _cursor.getInt(_cursorIndexOfChildItemID);
            final String _tmpDeviceName;
            if (_cursor.isNull(_cursorIndexOfDeviceName)) {
              _tmpDeviceName = null;
            } else {
              _tmpDeviceName = _cursor.getString(_cursorIndexOfDeviceName);
            }
            final boolean _tmpGlyph;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfGlyph);
            _tmpGlyph = _tmp_1 != 0;
            final int _tmpIconID;
            _tmpIconID = _cursor.getInt(_cursorIndexOfIconID);
            final int _tmpItemTier;
            _tmpItemTier = _cursor.getInt(_cursorIndexOfItemTier);
            final int _tmpPrice;
            _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
            final String _tmpRestrictedRoles;
            if (_cursor.isNull(_cursorIndexOfRestrictedRoles)) {
              _tmpRestrictedRoles = null;
            } else {
              _tmpRestrictedRoles = _cursor.getString(_cursorIndexOfRestrictedRoles);
            }
            final int _tmpRootItemID;
            _tmpRootItemID = _cursor.getInt(_cursorIndexOfRootItemID);
            final String _tmpShortDesc;
            if (_cursor.isNull(_cursorIndexOfShortDesc)) {
              _tmpShortDesc = null;
            } else {
              _tmpShortDesc = _cursor.getString(_cursorIndexOfShortDesc);
            }
            final boolean _tmpStartingItem;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfStartingItem);
            _tmpStartingItem = _tmp_2 != 0;
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpItemIconURL;
            if (_cursor.isNull(_cursorIndexOfItemIconURL)) {
              _tmpItemIconURL = null;
            } else {
              _tmpItemIconURL = _cursor.getString(_cursorIndexOfItemIconURL);
            }
            final ItemDescription _tmpItemDescription;
            if (! (_cursor.isNull(_cursorIndexOfDescription) && _cursor.isNull(_cursorIndexOfMenuItems) && _cursor.isNull(_cursorIndexOfSecondaryDescription))) {
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              final List<DescriptionValue> _tmpMenuItems;
              final String _tmp_3;
              if (_cursor.isNull(_cursorIndexOfMenuItems)) {
                _tmp_3 = null;
              } else {
                _tmp_3 = _cursor.getString(_cursorIndexOfMenuItems);
              }
              _tmpMenuItems = __converters.toList(_tmp_3);
              final String _tmpSecondaryDescription;
              if (_cursor.isNull(_cursorIndexOfSecondaryDescription)) {
                _tmpSecondaryDescription = null;
              } else {
                _tmpSecondaryDescription = _cursor.getString(_cursorIndexOfSecondaryDescription);
              }
              _tmpItemDescription = new ItemDescription(_tmpDescription,_tmpMenuItems,_tmpSecondaryDescription);
            }  else  {
              _tmpItemDescription = null;
            }
            _result = new ItemEntity(_tmpId,_tmpPatchVersion,_tmpActiveFlag,_tmpChildItemID,_tmpDeviceName,_tmpGlyph,_tmpIconID,_tmpItemDescription,_tmpItemTier,_tmpPrice,_tmpRestrictedRoles,_tmpRootItemID,_tmpShortDesc,_tmpStartingItem,_tmpType,_tmpItemIconURL);
          } else {
            _result = null;
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
