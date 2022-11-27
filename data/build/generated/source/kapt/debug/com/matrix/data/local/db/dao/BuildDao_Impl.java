package com.matrix.data.local.db.dao;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.matrix.data.local.db.Converters;
import com.matrix.data.local.db.entity.Ability;
import com.matrix.data.local.db.entity.AbilityDescription;
import com.matrix.data.local.db.entity.BuildDbResult;
import com.matrix.data.local.db.entity.BuildEntity;
import com.matrix.data.local.db.entity.BuildItemCrossRef;
import com.matrix.data.local.db.entity.GodEntity;
import com.matrix.data.local.db.entity.ItemDescription;
import com.matrix.data.local.db.entity.ItemEntity;
import com.matrix.data.local.db.model.DescriptionValue;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BuildDao_Impl implements BuildDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BuildEntity> __insertionAdapterOfBuildEntity;

  private final EntityInsertionAdapter<BuildItemCrossRef> __insertionAdapterOfBuildItemCrossRef;

  private final EntityDeletionOrUpdateAdapter<BuildEntity> __deletionAdapterOfBuildEntity;

  private final Converters __converters = new Converters();

  public BuildDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBuildEntity = new EntityInsertionAdapter<BuildEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `builds` (`id`,`name`,`godId`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BuildEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getGodId());
      }
    };
    this.__insertionAdapterOfBuildItemCrossRef = new EntityInsertionAdapter<BuildItemCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `BuildItemCrossRef` (`buildId`,`itemId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BuildItemCrossRef value) {
        stmt.bindLong(1, value.getBuildId());
        stmt.bindLong(2, value.getItemId());
      }
    };
    this.__deletionAdapterOfBuildEntity = new EntityDeletionOrUpdateAdapter<BuildEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `builds` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BuildEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
  }

  @Override
  public Object insertBuildEntity(final BuildEntity buildEntity,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfBuildEntity.insertAndReturnId(buildEntity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertBuildItemCrossRef(final BuildItemCrossRef buildItemCrossRef,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBuildItemCrossRef.insert(buildItemCrossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteBuildEntity(final BuildEntity buildEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBuildEntity.handle(buildEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object createBuild(final BuildEntity buildEntity, final List<Integer> itemIds,
      final Continuation<? super Unit> continuation) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> BuildDao.DefaultImpls.createBuild(BuildDao_Impl.this, buildEntity, itemIds, __cont), continuation);
  }

  @Override
  public Flow<List<BuildDbResult>> getAll() {
    final String _sql = "SELECT * FROM builds";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[]{"gods","BuildItemCrossRef","items","builds"}, new Callable<List<BuildDbResult>>() {
      @Override
      public List<BuildDbResult> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfGodId = CursorUtil.getColumnIndexOrThrow(_cursor, "godId");
            final LongSparseArray<GodEntity> _collectionGod = new LongSparseArray<GodEntity>();
            final LongSparseArray<ArrayList<ItemEntity>> _collectionItems = new LongSparseArray<ArrayList<ItemEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfGodId);
              _collectionGod.put(_tmpKey, null);
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
                ArrayList<ItemEntity> _tmpItemsCollection = _collectionItems.get(_tmpKey_1);
                if (_tmpItemsCollection == null) {
                  _tmpItemsCollection = new ArrayList<ItemEntity>();
                  _collectionItems.put(_tmpKey_1, _tmpItemsCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipgodsAscomMatrixDataLocalDbEntityGodEntity(_collectionGod);
            __fetchRelationshipitemsAscomMatrixDataLocalDbEntityItemEntity(_collectionItems);
            final List<BuildDbResult> _result = new ArrayList<BuildDbResult>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final BuildDbResult _item;
              final BuildEntity _tmpBuild;
              if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfGodId))) {
                final Integer _tmpId;
                if (_cursor.isNull(_cursorIndexOfId)) {
                  _tmpId = null;
                } else {
                  _tmpId = _cursor.getInt(_cursorIndexOfId);
                }
                final String _tmpName;
                if (_cursor.isNull(_cursorIndexOfName)) {
                  _tmpName = null;
                } else {
                  _tmpName = _cursor.getString(_cursorIndexOfName);
                }
                final int _tmpGodId;
                _tmpGodId = _cursor.getInt(_cursorIndexOfGodId);
                _tmpBuild = new BuildEntity(_tmpId,_tmpName,_tmpGodId);
              }  else  {
                _tmpBuild = null;
              }
              GodEntity _tmpGod = null;
              final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfGodId);
              _tmpGod = _collectionGod.get(_tmpKey_2);
              ArrayList<ItemEntity> _tmpItemsCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfId);
                _tmpItemsCollection_1 = _collectionItems.get(_tmpKey_3);
              }
              if (_tmpItemsCollection_1 == null) {
                _tmpItemsCollection_1 = new ArrayList<ItemEntity>();
              }
              _item = new BuildDbResult(_tmpBuild,_tmpGod,_tmpItemsCollection_1);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<BuildDbResult> getBuild(final int buildId) {
    final String _sql = "SELECT * FROM builds where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, buildId);
    return CoroutinesRoom.createFlow(__db, true, new String[]{"gods","BuildItemCrossRef","items","builds"}, new Callable<BuildDbResult>() {
      @Override
      public BuildDbResult call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfGodId = CursorUtil.getColumnIndexOrThrow(_cursor, "godId");
            final LongSparseArray<GodEntity> _collectionGod = new LongSparseArray<GodEntity>();
            final LongSparseArray<ArrayList<ItemEntity>> _collectionItems = new LongSparseArray<ArrayList<ItemEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey = _cursor.getLong(_cursorIndexOfGodId);
              _collectionGod.put(_tmpKey, null);
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
                ArrayList<ItemEntity> _tmpItemsCollection = _collectionItems.get(_tmpKey_1);
                if (_tmpItemsCollection == null) {
                  _tmpItemsCollection = new ArrayList<ItemEntity>();
                  _collectionItems.put(_tmpKey_1, _tmpItemsCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipgodsAscomMatrixDataLocalDbEntityGodEntity(_collectionGod);
            __fetchRelationshipitemsAscomMatrixDataLocalDbEntityItemEntity(_collectionItems);
            final BuildDbResult _result;
            if(_cursor.moveToFirst()) {
              final BuildEntity _tmpBuild;
              if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfGodId))) {
                final Integer _tmpId;
                if (_cursor.isNull(_cursorIndexOfId)) {
                  _tmpId = null;
                } else {
                  _tmpId = _cursor.getInt(_cursorIndexOfId);
                }
                final String _tmpName;
                if (_cursor.isNull(_cursorIndexOfName)) {
                  _tmpName = null;
                } else {
                  _tmpName = _cursor.getString(_cursorIndexOfName);
                }
                final int _tmpGodId;
                _tmpGodId = _cursor.getInt(_cursorIndexOfGodId);
                _tmpBuild = new BuildEntity(_tmpId,_tmpName,_tmpGodId);
              }  else  {
                _tmpBuild = null;
              }
              GodEntity _tmpGod = null;
              final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfGodId);
              _tmpGod = _collectionGod.get(_tmpKey_2);
              ArrayList<ItemEntity> _tmpItemsCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfId);
                _tmpItemsCollection_1 = _collectionItems.get(_tmpKey_3);
              }
              if (_tmpItemsCollection_1 == null) {
                _tmpItemsCollection_1 = new ArrayList<ItemEntity>();
              }
              _result = new BuildDbResult(_tmpBuild,_tmpGod,_tmpItemsCollection_1);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
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

  private void __fetchRelationshipgodsAscomMatrixDataLocalDbEntityGodEntity(
      final LongSparseArray<GodEntity> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<GodEntity> _tmpInnerMap = new LongSparseArray<GodEntity>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipgodsAscomMatrixDataLocalDbEntityGodEntity(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<GodEntity>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipgodsAscomMatrixDataLocalDbEntityGodEntity(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`patchVersion`,`attackSpeed`,`attackSpeedPerLevel`,`autoBanned`,`cons`,`hp5PerLevel`,`health`,`healthPerFive`,`healthPerLevel`,`lore`,`mp5PerLevel`,`magicProtection`,`magicProtectionPerLevel`,`magicalPower`,`magicalPowerPerLevel`,`mana`,`manaPerFive`,`manaPerLevel`,`name`,`onFreeRotation`,`pantheon`,`physicalPower`,`physicalPowerPerLevel`,`physicalProtection`,`physicalProtectionPerLevel`,`pros`,`roles`,`speed`,`title`,`type`,`godCardURL`,`godIconURL`,`latestGod`,`ability_details_1_id`,`ability_details_1_summary`,`ability_details_1_url`,`ability_details_1_cooldown`,`ability_details_1_cost`,`ability_details_1_description`,`ability_details_1_menuItems`,`ability_details_1_rankItems`,`ability_details_2_id`,`ability_details_2_summary`,`ability_details_2_url`,`ability_details_2_cooldown`,`ability_details_2_cost`,`ability_details_2_description`,`ability_details_2_menuItems`,`ability_details_2_rankItems`,`ability_details_3_id`,`ability_details_3_summary`,`ability_details_3_url`,`ability_details_3_cooldown`,`ability_details_3_cost`,`ability_details_3_description`,`ability_details_3_menuItems`,`ability_details_3_rankItems`,`ability_details_4_id`,`ability_details_4_summary`,`ability_details_4_url`,`ability_details_4_cooldown`,`ability_details_4_cost`,`ability_details_4_description`,`ability_details_4_menuItems`,`ability_details_4_rankItems`,`ability_details_5_id`,`ability_details_5_summary`,`ability_details_5_url`,`ability_details_5_cooldown`,`ability_details_5_cost`,`ability_details_5_description`,`ability_details_5_menuItems`,`ability_details_5_rankItems`,`basic_attack_desccooldown`,`basic_attack_desccost`,`basic_attack_descdescription`,`basic_attack_descmenuItems`,`basic_attack_descrankItems` FROM `gods` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfPatchVersion = 1;
      final int _cursorIndexOfAttackSpeed = 2;
      final int _cursorIndexOfAttackSpeedPerLevel = 3;
      final int _cursorIndexOfAutoBanned = 4;
      final int _cursorIndexOfCons = 5;
      final int _cursorIndexOfHp5PerLevel = 6;
      final int _cursorIndexOfHealth = 7;
      final int _cursorIndexOfHealthPerFive = 8;
      final int _cursorIndexOfHealthPerLevel = 9;
      final int _cursorIndexOfLore = 10;
      final int _cursorIndexOfMp5PerLevel = 11;
      final int _cursorIndexOfMagicProtection = 12;
      final int _cursorIndexOfMagicProtectionPerLevel = 13;
      final int _cursorIndexOfMagicalPower = 14;
      final int _cursorIndexOfMagicalPowerPerLevel = 15;
      final int _cursorIndexOfMana = 16;
      final int _cursorIndexOfManaPerFive = 17;
      final int _cursorIndexOfManaPerLevel = 18;
      final int _cursorIndexOfName = 19;
      final int _cursorIndexOfOnFreeRotation = 20;
      final int _cursorIndexOfPantheon = 21;
      final int _cursorIndexOfPhysicalPower = 22;
      final int _cursorIndexOfPhysicalPowerPerLevel = 23;
      final int _cursorIndexOfPhysicalProtection = 24;
      final int _cursorIndexOfPhysicalProtectionPerLevel = 25;
      final int _cursorIndexOfPros = 26;
      final int _cursorIndexOfRoles = 27;
      final int _cursorIndexOfSpeed = 28;
      final int _cursorIndexOfTitle = 29;
      final int _cursorIndexOfType = 30;
      final int _cursorIndexOfGodCardURL = 31;
      final int _cursorIndexOfGodIconURL = 32;
      final int _cursorIndexOfLatestGod = 33;
      final int _cursorIndexOfId_1 = 34;
      final int _cursorIndexOfSummary = 35;
      final int _cursorIndexOfUrl = 36;
      final int _cursorIndexOfCooldown = 37;
      final int _cursorIndexOfCost = 38;
      final int _cursorIndexOfDescription = 39;
      final int _cursorIndexOfMenuItems = 40;
      final int _cursorIndexOfRankItems = 41;
      final int _cursorIndexOfId_2 = 42;
      final int _cursorIndexOfSummary_1 = 43;
      final int _cursorIndexOfUrl_1 = 44;
      final int _cursorIndexOfCooldown_1 = 45;
      final int _cursorIndexOfCost_1 = 46;
      final int _cursorIndexOfDescription_1 = 47;
      final int _cursorIndexOfMenuItems_1 = 48;
      final int _cursorIndexOfRankItems_1 = 49;
      final int _cursorIndexOfId_3 = 50;
      final int _cursorIndexOfSummary_2 = 51;
      final int _cursorIndexOfUrl_2 = 52;
      final int _cursorIndexOfCooldown_2 = 53;
      final int _cursorIndexOfCost_2 = 54;
      final int _cursorIndexOfDescription_2 = 55;
      final int _cursorIndexOfMenuItems_2 = 56;
      final int _cursorIndexOfRankItems_2 = 57;
      final int _cursorIndexOfId_4 = 58;
      final int _cursorIndexOfSummary_3 = 59;
      final int _cursorIndexOfUrl_3 = 60;
      final int _cursorIndexOfCooldown_3 = 61;
      final int _cursorIndexOfCost_3 = 62;
      final int _cursorIndexOfDescription_3 = 63;
      final int _cursorIndexOfMenuItems_3 = 64;
      final int _cursorIndexOfRankItems_3 = 65;
      final int _cursorIndexOfId_5 = 66;
      final int _cursorIndexOfSummary_4 = 67;
      final int _cursorIndexOfUrl_4 = 68;
      final int _cursorIndexOfCooldown_4 = 69;
      final int _cursorIndexOfCost_4 = 70;
      final int _cursorIndexOfDescription_4 = 71;
      final int _cursorIndexOfMenuItems_4 = 72;
      final int _cursorIndexOfRankItems_4 = 73;
      final int _cursorIndexOfCooldown_5 = 74;
      final int _cursorIndexOfCost_5 = 75;
      final int _cursorIndexOfDescription_5 = 76;
      final int _cursorIndexOfMenuItems_5 = 77;
      final int _cursorIndexOfRankItems_5 = 78;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final GodEntity _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpPatchVersion;
          if (_cursor.isNull(_cursorIndexOfPatchVersion)) {
            _tmpPatchVersion = null;
          } else {
            _tmpPatchVersion = _cursor.getString(_cursorIndexOfPatchVersion);
          }
          final double _tmpAttackSpeed;
          _tmpAttackSpeed = _cursor.getDouble(_cursorIndexOfAttackSpeed);
          final double _tmpAttackSpeedPerLevel;
          _tmpAttackSpeedPerLevel = _cursor.getDouble(_cursorIndexOfAttackSpeedPerLevel);
          final boolean _tmpAutoBanned;
          final int _tmp;
          _tmp = _cursor.getInt(_cursorIndexOfAutoBanned);
          _tmpAutoBanned = _tmp != 0;
          final String _tmpCons;
          if (_cursor.isNull(_cursorIndexOfCons)) {
            _tmpCons = null;
          } else {
            _tmpCons = _cursor.getString(_cursorIndexOfCons);
          }
          final double _tmpHp5PerLevel;
          _tmpHp5PerLevel = _cursor.getDouble(_cursorIndexOfHp5PerLevel);
          final long _tmpHealth;
          _tmpHealth = _cursor.getLong(_cursorIndexOfHealth);
          final double _tmpHealthPerFive;
          _tmpHealthPerFive = _cursor.getDouble(_cursorIndexOfHealthPerFive);
          final double _tmpHealthPerLevel;
          _tmpHealthPerLevel = _cursor.getDouble(_cursorIndexOfHealthPerLevel);
          final String _tmpLore;
          if (_cursor.isNull(_cursorIndexOfLore)) {
            _tmpLore = null;
          } else {
            _tmpLore = _cursor.getString(_cursorIndexOfLore);
          }
          final double _tmpMp5PerLevel;
          _tmpMp5PerLevel = _cursor.getDouble(_cursorIndexOfMp5PerLevel);
          final double _tmpMagicProtection;
          _tmpMagicProtection = _cursor.getDouble(_cursorIndexOfMagicProtection);
          final double _tmpMagicProtectionPerLevel;
          _tmpMagicProtectionPerLevel = _cursor.getDouble(_cursorIndexOfMagicProtectionPerLevel);
          final long _tmpMagicalPower;
          _tmpMagicalPower = _cursor.getLong(_cursorIndexOfMagicalPower);
          final double _tmpMagicalPowerPerLevel;
          _tmpMagicalPowerPerLevel = _cursor.getDouble(_cursorIndexOfMagicalPowerPerLevel);
          final long _tmpMana;
          _tmpMana = _cursor.getLong(_cursorIndexOfMana);
          final double _tmpManaPerFive;
          _tmpManaPerFive = _cursor.getDouble(_cursorIndexOfManaPerFive);
          final double _tmpManaPerLevel;
          _tmpManaPerLevel = _cursor.getDouble(_cursorIndexOfManaPerLevel);
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          final boolean _tmpOnFreeRotation;
          final int _tmp_1;
          _tmp_1 = _cursor.getInt(_cursorIndexOfOnFreeRotation);
          _tmpOnFreeRotation = _tmp_1 != 0;
          final String _tmpPantheon;
          if (_cursor.isNull(_cursorIndexOfPantheon)) {
            _tmpPantheon = null;
          } else {
            _tmpPantheon = _cursor.getString(_cursorIndexOfPantheon);
          }
          final long _tmpPhysicalPower;
          _tmpPhysicalPower = _cursor.getLong(_cursorIndexOfPhysicalPower);
          final double _tmpPhysicalPowerPerLevel;
          _tmpPhysicalPowerPerLevel = _cursor.getDouble(_cursorIndexOfPhysicalPowerPerLevel);
          final double _tmpPhysicalProtection;
          _tmpPhysicalProtection = _cursor.getDouble(_cursorIndexOfPhysicalProtection);
          final double _tmpPhysicalProtectionPerLevel;
          _tmpPhysicalProtectionPerLevel = _cursor.getDouble(_cursorIndexOfPhysicalProtectionPerLevel);
          final String _tmpPros;
          if (_cursor.isNull(_cursorIndexOfPros)) {
            _tmpPros = null;
          } else {
            _tmpPros = _cursor.getString(_cursorIndexOfPros);
          }
          final String _tmpRoles;
          if (_cursor.isNull(_cursorIndexOfRoles)) {
            _tmpRoles = null;
          } else {
            _tmpRoles = _cursor.getString(_cursorIndexOfRoles);
          }
          final long _tmpSpeed;
          _tmpSpeed = _cursor.getLong(_cursorIndexOfSpeed);
          final String _tmpTitle;
          if (_cursor.isNull(_cursorIndexOfTitle)) {
            _tmpTitle = null;
          } else {
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
          }
          final String _tmpType;
          if (_cursor.isNull(_cursorIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _cursor.getString(_cursorIndexOfType);
          }
          final String _tmpGodCardURL;
          if (_cursor.isNull(_cursorIndexOfGodCardURL)) {
            _tmpGodCardURL = null;
          } else {
            _tmpGodCardURL = _cursor.getString(_cursorIndexOfGodCardURL);
          }
          final String _tmpGodIconURL;
          if (_cursor.isNull(_cursorIndexOfGodIconURL)) {
            _tmpGodIconURL = null;
          } else {
            _tmpGodIconURL = _cursor.getString(_cursorIndexOfGodIconURL);
          }
          final boolean _tmpLatestGod;
          final int _tmp_2;
          _tmp_2 = _cursor.getInt(_cursorIndexOfLatestGod);
          _tmpLatestGod = _tmp_2 != 0;
          final Ability _tmpAbilityDetails1;
          if (! (_cursor.isNull(_cursorIndexOfId_1) && _cursor.isNull(_cursorIndexOfSummary) && _cursor.isNull(_cursorIndexOfUrl) && _cursor.isNull(_cursorIndexOfCooldown) && _cursor.isNull(_cursorIndexOfCost) && _cursor.isNull(_cursorIndexOfDescription) && _cursor.isNull(_cursorIndexOfMenuItems) && _cursor.isNull(_cursorIndexOfRankItems))) {
            final long _tmpId_1;
            _tmpId_1 = _cursor.getLong(_cursorIndexOfId_1);
            final String _tmpSummary;
            if (_cursor.isNull(_cursorIndexOfSummary)) {
              _tmpSummary = null;
            } else {
              _tmpSummary = _cursor.getString(_cursorIndexOfSummary);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final AbilityDescription _tmpDescription;
            if (! (_cursor.isNull(_cursorIndexOfCooldown) && _cursor.isNull(_cursorIndexOfCost) && _cursor.isNull(_cursorIndexOfDescription) && _cursor.isNull(_cursorIndexOfMenuItems) && _cursor.isNull(_cursorIndexOfRankItems))) {
              final String _tmpCooldown;
              if (_cursor.isNull(_cursorIndexOfCooldown)) {
                _tmpCooldown = null;
              } else {
                _tmpCooldown = _cursor.getString(_cursorIndexOfCooldown);
              }
              final String _tmpCost;
              if (_cursor.isNull(_cursorIndexOfCost)) {
                _tmpCost = null;
              } else {
                _tmpCost = _cursor.getString(_cursorIndexOfCost);
              }
              final String _tmpDescription_5;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription_5 = null;
              } else {
                _tmpDescription_5 = _cursor.getString(_cursorIndexOfDescription);
              }
              final List<DescriptionValue> _tmpMenuItems;
              final String _tmp_3;
              if (_cursor.isNull(_cursorIndexOfMenuItems)) {
                _tmp_3 = null;
              } else {
                _tmp_3 = _cursor.getString(_cursorIndexOfMenuItems);
              }
              _tmpMenuItems = __converters.toList(_tmp_3);
              final List<DescriptionValue> _tmpRankItems;
              final String _tmp_4;
              if (_cursor.isNull(_cursorIndexOfRankItems)) {
                _tmp_4 = null;
              } else {
                _tmp_4 = _cursor.getString(_cursorIndexOfRankItems);
              }
              _tmpRankItems = __converters.toList(_tmp_4);
              _tmpDescription = new AbilityDescription(_tmpCooldown,_tmpCost,_tmpDescription_5,_tmpMenuItems,_tmpRankItems);
            }  else  {
              _tmpDescription = null;
            }
            _tmpAbilityDetails1 = new Ability(_tmpId_1,_tmpDescription,_tmpSummary,_tmpUrl);
          }  else  {
            _tmpAbilityDetails1 = null;
          }
          final Ability _tmpAbilityDetails2;
          if (! (_cursor.isNull(_cursorIndexOfId_2) && _cursor.isNull(_cursorIndexOfSummary_1) && _cursor.isNull(_cursorIndexOfUrl_1) && _cursor.isNull(_cursorIndexOfCooldown_1) && _cursor.isNull(_cursorIndexOfCost_1) && _cursor.isNull(_cursorIndexOfDescription_1) && _cursor.isNull(_cursorIndexOfMenuItems_1) && _cursor.isNull(_cursorIndexOfRankItems_1))) {
            final long _tmpId_2;
            _tmpId_2 = _cursor.getLong(_cursorIndexOfId_2);
            final String _tmpSummary_1;
            if (_cursor.isNull(_cursorIndexOfSummary_1)) {
              _tmpSummary_1 = null;
            } else {
              _tmpSummary_1 = _cursor.getString(_cursorIndexOfSummary_1);
            }
            final String _tmpUrl_1;
            if (_cursor.isNull(_cursorIndexOfUrl_1)) {
              _tmpUrl_1 = null;
            } else {
              _tmpUrl_1 = _cursor.getString(_cursorIndexOfUrl_1);
            }
            final AbilityDescription _tmpDescription_1;
            if (! (_cursor.isNull(_cursorIndexOfCooldown_1) && _cursor.isNull(_cursorIndexOfCost_1) && _cursor.isNull(_cursorIndexOfDescription_1) && _cursor.isNull(_cursorIndexOfMenuItems_1) && _cursor.isNull(_cursorIndexOfRankItems_1))) {
              final String _tmpCooldown_1;
              if (_cursor.isNull(_cursorIndexOfCooldown_1)) {
                _tmpCooldown_1 = null;
              } else {
                _tmpCooldown_1 = _cursor.getString(_cursorIndexOfCooldown_1);
              }
              final String _tmpCost_1;
              if (_cursor.isNull(_cursorIndexOfCost_1)) {
                _tmpCost_1 = null;
              } else {
                _tmpCost_1 = _cursor.getString(_cursorIndexOfCost_1);
              }
              final String _tmpDescription_6;
              if (_cursor.isNull(_cursorIndexOfDescription_1)) {
                _tmpDescription_6 = null;
              } else {
                _tmpDescription_6 = _cursor.getString(_cursorIndexOfDescription_1);
              }
              final List<DescriptionValue> _tmpMenuItems_1;
              final String _tmp_5;
              if (_cursor.isNull(_cursorIndexOfMenuItems_1)) {
                _tmp_5 = null;
              } else {
                _tmp_5 = _cursor.getString(_cursorIndexOfMenuItems_1);
              }
              _tmpMenuItems_1 = __converters.toList(_tmp_5);
              final List<DescriptionValue> _tmpRankItems_1;
              final String _tmp_6;
              if (_cursor.isNull(_cursorIndexOfRankItems_1)) {
                _tmp_6 = null;
              } else {
                _tmp_6 = _cursor.getString(_cursorIndexOfRankItems_1);
              }
              _tmpRankItems_1 = __converters.toList(_tmp_6);
              _tmpDescription_1 = new AbilityDescription(_tmpCooldown_1,_tmpCost_1,_tmpDescription_6,_tmpMenuItems_1,_tmpRankItems_1);
            }  else  {
              _tmpDescription_1 = null;
            }
            _tmpAbilityDetails2 = new Ability(_tmpId_2,_tmpDescription_1,_tmpSummary_1,_tmpUrl_1);
          }  else  {
            _tmpAbilityDetails2 = null;
          }
          final Ability _tmpAbilityDetails3;
          if (! (_cursor.isNull(_cursorIndexOfId_3) && _cursor.isNull(_cursorIndexOfSummary_2) && _cursor.isNull(_cursorIndexOfUrl_2) && _cursor.isNull(_cursorIndexOfCooldown_2) && _cursor.isNull(_cursorIndexOfCost_2) && _cursor.isNull(_cursorIndexOfDescription_2) && _cursor.isNull(_cursorIndexOfMenuItems_2) && _cursor.isNull(_cursorIndexOfRankItems_2))) {
            final long _tmpId_3;
            _tmpId_3 = _cursor.getLong(_cursorIndexOfId_3);
            final String _tmpSummary_2;
            if (_cursor.isNull(_cursorIndexOfSummary_2)) {
              _tmpSummary_2 = null;
            } else {
              _tmpSummary_2 = _cursor.getString(_cursorIndexOfSummary_2);
            }
            final String _tmpUrl_2;
            if (_cursor.isNull(_cursorIndexOfUrl_2)) {
              _tmpUrl_2 = null;
            } else {
              _tmpUrl_2 = _cursor.getString(_cursorIndexOfUrl_2);
            }
            final AbilityDescription _tmpDescription_2;
            if (! (_cursor.isNull(_cursorIndexOfCooldown_2) && _cursor.isNull(_cursorIndexOfCost_2) && _cursor.isNull(_cursorIndexOfDescription_2) && _cursor.isNull(_cursorIndexOfMenuItems_2) && _cursor.isNull(_cursorIndexOfRankItems_2))) {
              final String _tmpCooldown_2;
              if (_cursor.isNull(_cursorIndexOfCooldown_2)) {
                _tmpCooldown_2 = null;
              } else {
                _tmpCooldown_2 = _cursor.getString(_cursorIndexOfCooldown_2);
              }
              final String _tmpCost_2;
              if (_cursor.isNull(_cursorIndexOfCost_2)) {
                _tmpCost_2 = null;
              } else {
                _tmpCost_2 = _cursor.getString(_cursorIndexOfCost_2);
              }
              final String _tmpDescription_7;
              if (_cursor.isNull(_cursorIndexOfDescription_2)) {
                _tmpDescription_7 = null;
              } else {
                _tmpDescription_7 = _cursor.getString(_cursorIndexOfDescription_2);
              }
              final List<DescriptionValue> _tmpMenuItems_2;
              final String _tmp_7;
              if (_cursor.isNull(_cursorIndexOfMenuItems_2)) {
                _tmp_7 = null;
              } else {
                _tmp_7 = _cursor.getString(_cursorIndexOfMenuItems_2);
              }
              _tmpMenuItems_2 = __converters.toList(_tmp_7);
              final List<DescriptionValue> _tmpRankItems_2;
              final String _tmp_8;
              if (_cursor.isNull(_cursorIndexOfRankItems_2)) {
                _tmp_8 = null;
              } else {
                _tmp_8 = _cursor.getString(_cursorIndexOfRankItems_2);
              }
              _tmpRankItems_2 = __converters.toList(_tmp_8);
              _tmpDescription_2 = new AbilityDescription(_tmpCooldown_2,_tmpCost_2,_tmpDescription_7,_tmpMenuItems_2,_tmpRankItems_2);
            }  else  {
              _tmpDescription_2 = null;
            }
            _tmpAbilityDetails3 = new Ability(_tmpId_3,_tmpDescription_2,_tmpSummary_2,_tmpUrl_2);
          }  else  {
            _tmpAbilityDetails3 = null;
          }
          final Ability _tmpAbilityDetails4;
          if (! (_cursor.isNull(_cursorIndexOfId_4) && _cursor.isNull(_cursorIndexOfSummary_3) && _cursor.isNull(_cursorIndexOfUrl_3) && _cursor.isNull(_cursorIndexOfCooldown_3) && _cursor.isNull(_cursorIndexOfCost_3) && _cursor.isNull(_cursorIndexOfDescription_3) && _cursor.isNull(_cursorIndexOfMenuItems_3) && _cursor.isNull(_cursorIndexOfRankItems_3))) {
            final long _tmpId_4;
            _tmpId_4 = _cursor.getLong(_cursorIndexOfId_4);
            final String _tmpSummary_3;
            if (_cursor.isNull(_cursorIndexOfSummary_3)) {
              _tmpSummary_3 = null;
            } else {
              _tmpSummary_3 = _cursor.getString(_cursorIndexOfSummary_3);
            }
            final String _tmpUrl_3;
            if (_cursor.isNull(_cursorIndexOfUrl_3)) {
              _tmpUrl_3 = null;
            } else {
              _tmpUrl_3 = _cursor.getString(_cursorIndexOfUrl_3);
            }
            final AbilityDescription _tmpDescription_3;
            if (! (_cursor.isNull(_cursorIndexOfCooldown_3) && _cursor.isNull(_cursorIndexOfCost_3) && _cursor.isNull(_cursorIndexOfDescription_3) && _cursor.isNull(_cursorIndexOfMenuItems_3) && _cursor.isNull(_cursorIndexOfRankItems_3))) {
              final String _tmpCooldown_3;
              if (_cursor.isNull(_cursorIndexOfCooldown_3)) {
                _tmpCooldown_3 = null;
              } else {
                _tmpCooldown_3 = _cursor.getString(_cursorIndexOfCooldown_3);
              }
              final String _tmpCost_3;
              if (_cursor.isNull(_cursorIndexOfCost_3)) {
                _tmpCost_3 = null;
              } else {
                _tmpCost_3 = _cursor.getString(_cursorIndexOfCost_3);
              }
              final String _tmpDescription_8;
              if (_cursor.isNull(_cursorIndexOfDescription_3)) {
                _tmpDescription_8 = null;
              } else {
                _tmpDescription_8 = _cursor.getString(_cursorIndexOfDescription_3);
              }
              final List<DescriptionValue> _tmpMenuItems_3;
              final String _tmp_9;
              if (_cursor.isNull(_cursorIndexOfMenuItems_3)) {
                _tmp_9 = null;
              } else {
                _tmp_9 = _cursor.getString(_cursorIndexOfMenuItems_3);
              }
              _tmpMenuItems_3 = __converters.toList(_tmp_9);
              final List<DescriptionValue> _tmpRankItems_3;
              final String _tmp_10;
              if (_cursor.isNull(_cursorIndexOfRankItems_3)) {
                _tmp_10 = null;
              } else {
                _tmp_10 = _cursor.getString(_cursorIndexOfRankItems_3);
              }
              _tmpRankItems_3 = __converters.toList(_tmp_10);
              _tmpDescription_3 = new AbilityDescription(_tmpCooldown_3,_tmpCost_3,_tmpDescription_8,_tmpMenuItems_3,_tmpRankItems_3);
            }  else  {
              _tmpDescription_3 = null;
            }
            _tmpAbilityDetails4 = new Ability(_tmpId_4,_tmpDescription_3,_tmpSummary_3,_tmpUrl_3);
          }  else  {
            _tmpAbilityDetails4 = null;
          }
          final Ability _tmpAbilityDetails5;
          if (! (_cursor.isNull(_cursorIndexOfId_5) && _cursor.isNull(_cursorIndexOfSummary_4) && _cursor.isNull(_cursorIndexOfUrl_4) && _cursor.isNull(_cursorIndexOfCooldown_4) && _cursor.isNull(_cursorIndexOfCost_4) && _cursor.isNull(_cursorIndexOfDescription_4) && _cursor.isNull(_cursorIndexOfMenuItems_4) && _cursor.isNull(_cursorIndexOfRankItems_4))) {
            final long _tmpId_5;
            _tmpId_5 = _cursor.getLong(_cursorIndexOfId_5);
            final String _tmpSummary_4;
            if (_cursor.isNull(_cursorIndexOfSummary_4)) {
              _tmpSummary_4 = null;
            } else {
              _tmpSummary_4 = _cursor.getString(_cursorIndexOfSummary_4);
            }
            final String _tmpUrl_4;
            if (_cursor.isNull(_cursorIndexOfUrl_4)) {
              _tmpUrl_4 = null;
            } else {
              _tmpUrl_4 = _cursor.getString(_cursorIndexOfUrl_4);
            }
            final AbilityDescription _tmpDescription_4;
            if (! (_cursor.isNull(_cursorIndexOfCooldown_4) && _cursor.isNull(_cursorIndexOfCost_4) && _cursor.isNull(_cursorIndexOfDescription_4) && _cursor.isNull(_cursorIndexOfMenuItems_4) && _cursor.isNull(_cursorIndexOfRankItems_4))) {
              final String _tmpCooldown_4;
              if (_cursor.isNull(_cursorIndexOfCooldown_4)) {
                _tmpCooldown_4 = null;
              } else {
                _tmpCooldown_4 = _cursor.getString(_cursorIndexOfCooldown_4);
              }
              final String _tmpCost_4;
              if (_cursor.isNull(_cursorIndexOfCost_4)) {
                _tmpCost_4 = null;
              } else {
                _tmpCost_4 = _cursor.getString(_cursorIndexOfCost_4);
              }
              final String _tmpDescription_9;
              if (_cursor.isNull(_cursorIndexOfDescription_4)) {
                _tmpDescription_9 = null;
              } else {
                _tmpDescription_9 = _cursor.getString(_cursorIndexOfDescription_4);
              }
              final List<DescriptionValue> _tmpMenuItems_4;
              final String _tmp_11;
              if (_cursor.isNull(_cursorIndexOfMenuItems_4)) {
                _tmp_11 = null;
              } else {
                _tmp_11 = _cursor.getString(_cursorIndexOfMenuItems_4);
              }
              _tmpMenuItems_4 = __converters.toList(_tmp_11);
              final List<DescriptionValue> _tmpRankItems_4;
              final String _tmp_12;
              if (_cursor.isNull(_cursorIndexOfRankItems_4)) {
                _tmp_12 = null;
              } else {
                _tmp_12 = _cursor.getString(_cursorIndexOfRankItems_4);
              }
              _tmpRankItems_4 = __converters.toList(_tmp_12);
              _tmpDescription_4 = new AbilityDescription(_tmpCooldown_4,_tmpCost_4,_tmpDescription_9,_tmpMenuItems_4,_tmpRankItems_4);
            }  else  {
              _tmpDescription_4 = null;
            }
            _tmpAbilityDetails5 = new Ability(_tmpId_5,_tmpDescription_4,_tmpSummary_4,_tmpUrl_4);
          }  else  {
            _tmpAbilityDetails5 = null;
          }
          final AbilityDescription _tmpBasicAttack;
          if (! (_cursor.isNull(_cursorIndexOfCooldown_5) && _cursor.isNull(_cursorIndexOfCost_5) && _cursor.isNull(_cursorIndexOfDescription_5) && _cursor.isNull(_cursorIndexOfMenuItems_5) && _cursor.isNull(_cursorIndexOfRankItems_5))) {
            final String _tmpCooldown_5;
            if (_cursor.isNull(_cursorIndexOfCooldown_5)) {
              _tmpCooldown_5 = null;
            } else {
              _tmpCooldown_5 = _cursor.getString(_cursorIndexOfCooldown_5);
            }
            final String _tmpCost_5;
            if (_cursor.isNull(_cursorIndexOfCost_5)) {
              _tmpCost_5 = null;
            } else {
              _tmpCost_5 = _cursor.getString(_cursorIndexOfCost_5);
            }
            final String _tmpDescription_10;
            if (_cursor.isNull(_cursorIndexOfDescription_5)) {
              _tmpDescription_10 = null;
            } else {
              _tmpDescription_10 = _cursor.getString(_cursorIndexOfDescription_5);
            }
            final List<DescriptionValue> _tmpMenuItems_5;
            final String _tmp_13;
            if (_cursor.isNull(_cursorIndexOfMenuItems_5)) {
              _tmp_13 = null;
            } else {
              _tmp_13 = _cursor.getString(_cursorIndexOfMenuItems_5);
            }
            _tmpMenuItems_5 = __converters.toList(_tmp_13);
            final List<DescriptionValue> _tmpRankItems_5;
            final String _tmp_14;
            if (_cursor.isNull(_cursorIndexOfRankItems_5)) {
              _tmp_14 = null;
            } else {
              _tmp_14 = _cursor.getString(_cursorIndexOfRankItems_5);
            }
            _tmpRankItems_5 = __converters.toList(_tmp_14);
            _tmpBasicAttack = new AbilityDescription(_tmpCooldown_5,_tmpCost_5,_tmpDescription_10,_tmpMenuItems_5,_tmpRankItems_5);
          }  else  {
            _tmpBasicAttack = null;
          }
          _item_1 = new GodEntity(_tmpId,_tmpPatchVersion,_tmpAbilityDetails1,_tmpAbilityDetails2,_tmpAbilityDetails3,_tmpAbilityDetails4,_tmpAbilityDetails5,_tmpBasicAttack,_tmpAttackSpeed,_tmpAttackSpeedPerLevel,_tmpAutoBanned,_tmpCons,_tmpHp5PerLevel,_tmpHealth,_tmpHealthPerFive,_tmpHealthPerLevel,_tmpLore,_tmpMp5PerLevel,_tmpMagicProtection,_tmpMagicProtectionPerLevel,_tmpMagicalPower,_tmpMagicalPowerPerLevel,_tmpMana,_tmpManaPerFive,_tmpManaPerLevel,_tmpName,_tmpOnFreeRotation,_tmpPantheon,_tmpPhysicalPower,_tmpPhysicalPowerPerLevel,_tmpPhysicalProtection,_tmpPhysicalProtectionPerLevel,_tmpPros,_tmpRoles,_tmpSpeed,_tmpTitle,_tmpType,_tmpGodCardURL,_tmpGodIconURL,_tmpLatestGod);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipitemsAscomMatrixDataLocalDbEntityItemEntity(
      final LongSparseArray<ArrayList<ItemEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<ItemEntity>> _tmpInnerMap = new LongSparseArray<ArrayList<ItemEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipitemsAscomMatrixDataLocalDbEntityItemEntity(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<ItemEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipitemsAscomMatrixDataLocalDbEntityItemEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `items`.`id` AS `id`,`items`.`patchVersion` AS `patchVersion`,`items`.`activeFlag` AS `activeFlag`,`items`.`childItemID` AS `childItemID`,`items`.`deviceName` AS `deviceName`,`items`.`glyph` AS `glyph`,`items`.`iconID` AS `iconID`,`items`.`itemTier` AS `itemTier`,`items`.`price` AS `price`,`items`.`restrictedRoles` AS `restrictedRoles`,`items`.`rootItemID` AS `rootItemID`,`items`.`shortDesc` AS `shortDesc`,`items`.`startingItem` AS `startingItem`,`items`.`type` AS `type`,`items`.`itemIconURL` AS `itemIconURL`,`items`.`description` AS `description`,`items`.`menuItems` AS `menuItems`,`items`.`secondaryDescription` AS `secondaryDescription`,_junction.`buildId` FROM `BuildItemCrossRef` AS _junction INNER JOIN `items` ON (_junction.`itemId` = `items`.`id`) WHERE _junction.`buildId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 18; // _junction.buildId;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfPatchVersion = 1;
      final int _cursorIndexOfActiveFlag = 2;
      final int _cursorIndexOfChildItemID = 3;
      final int _cursorIndexOfDeviceName = 4;
      final int _cursorIndexOfGlyph = 5;
      final int _cursorIndexOfIconID = 6;
      final int _cursorIndexOfItemTier = 7;
      final int _cursorIndexOfPrice = 8;
      final int _cursorIndexOfRestrictedRoles = 9;
      final int _cursorIndexOfRootItemID = 10;
      final int _cursorIndexOfShortDesc = 11;
      final int _cursorIndexOfStartingItem = 12;
      final int _cursorIndexOfType = 13;
      final int _cursorIndexOfItemIconURL = 14;
      final int _cursorIndexOfDescription = 15;
      final int _cursorIndexOfMenuItems = 16;
      final int _cursorIndexOfSecondaryDescription = 17;
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<ItemEntity> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final ItemEntity _item_1;
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
            _item_1 = new ItemEntity(_tmpId,_tmpPatchVersion,_tmpActiveFlag,_tmpChildItemID,_tmpDeviceName,_tmpGlyph,_tmpIconID,_tmpItemDescription,_tmpItemTier,_tmpPrice,_tmpRestrictedRoles,_tmpRootItemID,_tmpShortDesc,_tmpStartingItem,_tmpType,_tmpItemIconURL);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
