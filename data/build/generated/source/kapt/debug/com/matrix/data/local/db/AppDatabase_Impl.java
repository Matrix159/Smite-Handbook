package com.matrix.data.local.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.matrix.data.local.db.dao.BuildDao;
import com.matrix.data.local.db.dao.BuildDao_Impl;
import com.matrix.data.local.db.dao.GodDao;
import com.matrix.data.local.db.dao.GodDao_Impl;
import com.matrix.data.local.db.dao.ItemDao;
import com.matrix.data.local.db.dao.ItemDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile GodDao _godDao;

  private volatile ItemDao _itemDao;

  private volatile BuildDao _buildDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `gods` (`id` INTEGER NOT NULL, `patchVersion` TEXT, `attackSpeed` REAL NOT NULL, `attackSpeedPerLevel` REAL NOT NULL, `autoBanned` INTEGER NOT NULL, `cons` TEXT NOT NULL, `hp5PerLevel` REAL NOT NULL, `health` INTEGER NOT NULL, `healthPerFive` REAL NOT NULL, `healthPerLevel` REAL NOT NULL, `lore` TEXT NOT NULL, `mp5PerLevel` REAL NOT NULL, `magicProtection` REAL NOT NULL, `magicProtectionPerLevel` REAL NOT NULL, `magicalPower` INTEGER NOT NULL, `magicalPowerPerLevel` REAL NOT NULL, `mana` INTEGER NOT NULL, `manaPerFive` REAL NOT NULL, `manaPerLevel` REAL NOT NULL, `name` TEXT NOT NULL, `onFreeRotation` INTEGER NOT NULL, `pantheon` TEXT NOT NULL, `physicalPower` INTEGER NOT NULL, `physicalPowerPerLevel` REAL NOT NULL, `physicalProtection` REAL NOT NULL, `physicalProtectionPerLevel` REAL NOT NULL, `pros` TEXT NOT NULL, `roles` TEXT NOT NULL, `speed` INTEGER NOT NULL, `title` TEXT NOT NULL, `type` TEXT NOT NULL, `godCardURL` TEXT NOT NULL, `godIconURL` TEXT NOT NULL, `latestGod` INTEGER NOT NULL, `ability_details_1_id` INTEGER NOT NULL, `ability_details_1_summary` TEXT NOT NULL, `ability_details_1_url` TEXT NOT NULL, `ability_details_1_cooldown` TEXT NOT NULL, `ability_details_1_cost` TEXT NOT NULL, `ability_details_1_description` TEXT NOT NULL, `ability_details_1_menuItems` TEXT NOT NULL, `ability_details_1_rankItems` TEXT NOT NULL, `ability_details_2_id` INTEGER NOT NULL, `ability_details_2_summary` TEXT NOT NULL, `ability_details_2_url` TEXT NOT NULL, `ability_details_2_cooldown` TEXT NOT NULL, `ability_details_2_cost` TEXT NOT NULL, `ability_details_2_description` TEXT NOT NULL, `ability_details_2_menuItems` TEXT NOT NULL, `ability_details_2_rankItems` TEXT NOT NULL, `ability_details_3_id` INTEGER NOT NULL, `ability_details_3_summary` TEXT NOT NULL, `ability_details_3_url` TEXT NOT NULL, `ability_details_3_cooldown` TEXT NOT NULL, `ability_details_3_cost` TEXT NOT NULL, `ability_details_3_description` TEXT NOT NULL, `ability_details_3_menuItems` TEXT NOT NULL, `ability_details_3_rankItems` TEXT NOT NULL, `ability_details_4_id` INTEGER NOT NULL, `ability_details_4_summary` TEXT NOT NULL, `ability_details_4_url` TEXT NOT NULL, `ability_details_4_cooldown` TEXT NOT NULL, `ability_details_4_cost` TEXT NOT NULL, `ability_details_4_description` TEXT NOT NULL, `ability_details_4_menuItems` TEXT NOT NULL, `ability_details_4_rankItems` TEXT NOT NULL, `ability_details_5_id` INTEGER NOT NULL, `ability_details_5_summary` TEXT NOT NULL, `ability_details_5_url` TEXT NOT NULL, `ability_details_5_cooldown` TEXT NOT NULL, `ability_details_5_cost` TEXT NOT NULL, `ability_details_5_description` TEXT NOT NULL, `ability_details_5_menuItems` TEXT NOT NULL, `ability_details_5_rankItems` TEXT NOT NULL, `basic_attack_desccooldown` TEXT NOT NULL, `basic_attack_desccost` TEXT NOT NULL, `basic_attack_descdescription` TEXT NOT NULL, `basic_attack_descmenuItems` TEXT NOT NULL, `basic_attack_descrankItems` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `items` (`id` INTEGER NOT NULL, `patchVersion` TEXT, `activeFlag` INTEGER NOT NULL, `childItemID` INTEGER NOT NULL, `deviceName` TEXT NOT NULL, `glyph` INTEGER NOT NULL, `iconID` INTEGER NOT NULL, `itemTier` INTEGER NOT NULL, `price` INTEGER NOT NULL, `restrictedRoles` TEXT NOT NULL, `rootItemID` INTEGER NOT NULL, `shortDesc` TEXT NOT NULL, `startingItem` INTEGER NOT NULL, `type` TEXT NOT NULL, `itemIconURL` TEXT NOT NULL, `description` TEXT, `menuItems` TEXT NOT NULL, `secondaryDescription` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `builds` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `name` TEXT, `godId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `BuildItemCrossRef` (`buildId` INTEGER NOT NULL, `itemId` INTEGER NOT NULL, PRIMARY KEY(`buildId`, `itemId`), FOREIGN KEY(`buildId`) REFERENCES `builds`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_BuildItemCrossRef_itemId` ON `BuildItemCrossRef` (`itemId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a65185f1444f4ad14ba0a336fc6fe682')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `gods`");
        _db.execSQL("DROP TABLE IF EXISTS `items`");
        _db.execSQL("DROP TABLE IF EXISTS `builds`");
        _db.execSQL("DROP TABLE IF EXISTS `BuildItemCrossRef`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
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
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsGods = new HashMap<String, TableInfo.Column>(79);
        _columnsGods.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("patchVersion", new TableInfo.Column("patchVersion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("attackSpeed", new TableInfo.Column("attackSpeed", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("attackSpeedPerLevel", new TableInfo.Column("attackSpeedPerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("autoBanned", new TableInfo.Column("autoBanned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("cons", new TableInfo.Column("cons", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("hp5PerLevel", new TableInfo.Column("hp5PerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("health", new TableInfo.Column("health", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("healthPerFive", new TableInfo.Column("healthPerFive", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("healthPerLevel", new TableInfo.Column("healthPerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("lore", new TableInfo.Column("lore", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("mp5PerLevel", new TableInfo.Column("mp5PerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("magicProtection", new TableInfo.Column("magicProtection", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("magicProtectionPerLevel", new TableInfo.Column("magicProtectionPerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("magicalPower", new TableInfo.Column("magicalPower", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("magicalPowerPerLevel", new TableInfo.Column("magicalPowerPerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("mana", new TableInfo.Column("mana", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("manaPerFive", new TableInfo.Column("manaPerFive", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("manaPerLevel", new TableInfo.Column("manaPerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("onFreeRotation", new TableInfo.Column("onFreeRotation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("pantheon", new TableInfo.Column("pantheon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("physicalPower", new TableInfo.Column("physicalPower", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("physicalPowerPerLevel", new TableInfo.Column("physicalPowerPerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("physicalProtection", new TableInfo.Column("physicalProtection", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("physicalProtectionPerLevel", new TableInfo.Column("physicalProtectionPerLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("pros", new TableInfo.Column("pros", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("roles", new TableInfo.Column("roles", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("speed", new TableInfo.Column("speed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("godCardURL", new TableInfo.Column("godCardURL", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("godIconURL", new TableInfo.Column("godIconURL", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("latestGod", new TableInfo.Column("latestGod", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_id", new TableInfo.Column("ability_details_1_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_summary", new TableInfo.Column("ability_details_1_summary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_url", new TableInfo.Column("ability_details_1_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_cooldown", new TableInfo.Column("ability_details_1_cooldown", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_cost", new TableInfo.Column("ability_details_1_cost", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_description", new TableInfo.Column("ability_details_1_description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_menuItems", new TableInfo.Column("ability_details_1_menuItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_1_rankItems", new TableInfo.Column("ability_details_1_rankItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_id", new TableInfo.Column("ability_details_2_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_summary", new TableInfo.Column("ability_details_2_summary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_url", new TableInfo.Column("ability_details_2_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_cooldown", new TableInfo.Column("ability_details_2_cooldown", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_cost", new TableInfo.Column("ability_details_2_cost", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_description", new TableInfo.Column("ability_details_2_description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_menuItems", new TableInfo.Column("ability_details_2_menuItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_2_rankItems", new TableInfo.Column("ability_details_2_rankItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_id", new TableInfo.Column("ability_details_3_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_summary", new TableInfo.Column("ability_details_3_summary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_url", new TableInfo.Column("ability_details_3_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_cooldown", new TableInfo.Column("ability_details_3_cooldown", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_cost", new TableInfo.Column("ability_details_3_cost", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_description", new TableInfo.Column("ability_details_3_description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_menuItems", new TableInfo.Column("ability_details_3_menuItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_3_rankItems", new TableInfo.Column("ability_details_3_rankItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_id", new TableInfo.Column("ability_details_4_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_summary", new TableInfo.Column("ability_details_4_summary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_url", new TableInfo.Column("ability_details_4_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_cooldown", new TableInfo.Column("ability_details_4_cooldown", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_cost", new TableInfo.Column("ability_details_4_cost", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_description", new TableInfo.Column("ability_details_4_description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_menuItems", new TableInfo.Column("ability_details_4_menuItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_4_rankItems", new TableInfo.Column("ability_details_4_rankItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_id", new TableInfo.Column("ability_details_5_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_summary", new TableInfo.Column("ability_details_5_summary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_url", new TableInfo.Column("ability_details_5_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_cooldown", new TableInfo.Column("ability_details_5_cooldown", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_cost", new TableInfo.Column("ability_details_5_cost", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_description", new TableInfo.Column("ability_details_5_description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_menuItems", new TableInfo.Column("ability_details_5_menuItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("ability_details_5_rankItems", new TableInfo.Column("ability_details_5_rankItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("basic_attack_desccooldown", new TableInfo.Column("basic_attack_desccooldown", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("basic_attack_desccost", new TableInfo.Column("basic_attack_desccost", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("basic_attack_descdescription", new TableInfo.Column("basic_attack_descdescription", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("basic_attack_descmenuItems", new TableInfo.Column("basic_attack_descmenuItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGods.put("basic_attack_descrankItems", new TableInfo.Column("basic_attack_descrankItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGods = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGods = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGods = new TableInfo("gods", _columnsGods, _foreignKeysGods, _indicesGods);
        final TableInfo _existingGods = TableInfo.read(_db, "gods");
        if (! _infoGods.equals(_existingGods)) {
          return new RoomOpenHelper.ValidationResult(false, "gods(com.matrix.data.local.db.entity.GodEntity).\n"
                  + " Expected:\n" + _infoGods + "\n"
                  + " Found:\n" + _existingGods);
        }
        final HashMap<String, TableInfo.Column> _columnsItems = new HashMap<String, TableInfo.Column>(18);
        _columnsItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("patchVersion", new TableInfo.Column("patchVersion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("activeFlag", new TableInfo.Column("activeFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("childItemID", new TableInfo.Column("childItemID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("deviceName", new TableInfo.Column("deviceName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("glyph", new TableInfo.Column("glyph", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("iconID", new TableInfo.Column("iconID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("itemTier", new TableInfo.Column("itemTier", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("price", new TableInfo.Column("price", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("restrictedRoles", new TableInfo.Column("restrictedRoles", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("rootItemID", new TableInfo.Column("rootItemID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("shortDesc", new TableInfo.Column("shortDesc", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("startingItem", new TableInfo.Column("startingItem", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("itemIconURL", new TableInfo.Column("itemIconURL", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("menuItems", new TableInfo.Column("menuItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("secondaryDescription", new TableInfo.Column("secondaryDescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoItems = new TableInfo("items", _columnsItems, _foreignKeysItems, _indicesItems);
        final TableInfo _existingItems = TableInfo.read(_db, "items");
        if (! _infoItems.equals(_existingItems)) {
          return new RoomOpenHelper.ValidationResult(false, "items(com.matrix.data.local.db.entity.ItemEntity).\n"
                  + " Expected:\n" + _infoItems + "\n"
                  + " Found:\n" + _existingItems);
        }
        final HashMap<String, TableInfo.Column> _columnsBuilds = new HashMap<String, TableInfo.Column>(3);
        _columnsBuilds.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBuilds.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBuilds.put("godId", new TableInfo.Column("godId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBuilds = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBuilds = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBuilds = new TableInfo("builds", _columnsBuilds, _foreignKeysBuilds, _indicesBuilds);
        final TableInfo _existingBuilds = TableInfo.read(_db, "builds");
        if (! _infoBuilds.equals(_existingBuilds)) {
          return new RoomOpenHelper.ValidationResult(false, "builds(com.matrix.data.local.db.entity.BuildEntity).\n"
                  + " Expected:\n" + _infoBuilds + "\n"
                  + " Found:\n" + _existingBuilds);
        }
        final HashMap<String, TableInfo.Column> _columnsBuildItemCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsBuildItemCrossRef.put("buildId", new TableInfo.Column("buildId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBuildItemCrossRef.put("itemId", new TableInfo.Column("itemId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBuildItemCrossRef = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysBuildItemCrossRef.add(new TableInfo.ForeignKey("builds", "CASCADE", "NO ACTION",Arrays.asList("buildId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesBuildItemCrossRef = new HashSet<TableInfo.Index>(1);
        _indicesBuildItemCrossRef.add(new TableInfo.Index("index_BuildItemCrossRef_itemId", false, Arrays.asList("itemId"), Arrays.asList("ASC")));
        final TableInfo _infoBuildItemCrossRef = new TableInfo("BuildItemCrossRef", _columnsBuildItemCrossRef, _foreignKeysBuildItemCrossRef, _indicesBuildItemCrossRef);
        final TableInfo _existingBuildItemCrossRef = TableInfo.read(_db, "BuildItemCrossRef");
        if (! _infoBuildItemCrossRef.equals(_existingBuildItemCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "BuildItemCrossRef(com.matrix.data.local.db.entity.BuildItemCrossRef).\n"
                  + " Expected:\n" + _infoBuildItemCrossRef + "\n"
                  + " Found:\n" + _existingBuildItemCrossRef);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a65185f1444f4ad14ba0a336fc6fe682", "0feb5aaa803daf87d61b21e7517a3aec");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "gods","items","builds","BuildItemCrossRef");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `gods`");
      _db.execSQL("DELETE FROM `items`");
      _db.execSQL("DELETE FROM `builds`");
      _db.execSQL("DELETE FROM `BuildItemCrossRef`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(GodDao.class, GodDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ItemDao.class, ItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BuildDao.class, BuildDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public GodDao godDao() {
    if (_godDao != null) {
      return _godDao;
    } else {
      synchronized(this) {
        if(_godDao == null) {
          _godDao = new GodDao_Impl(this);
        }
        return _godDao;
      }
    }
  }

  @Override
  public ItemDao itemDao() {
    if (_itemDao != null) {
      return _itemDao;
    } else {
      synchronized(this) {
        if(_itemDao == null) {
          _itemDao = new ItemDao_Impl(this);
        }
        return _itemDao;
      }
    }
  }

  @Override
  public BuildDao buildDao() {
    if (_buildDao != null) {
      return _buildDao;
    } else {
      synchronized(this) {
        if(_buildDao == null) {
          _buildDao = new BuildDao_Impl(this);
        }
        return _buildDao;
      }
    }
  }
}
