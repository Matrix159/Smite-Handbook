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
import com.matrix.data.local.db.entity.Ability;
import com.matrix.data.local.db.entity.AbilityDescription;
import com.matrix.data.local.db.entity.GodEntity;
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
public final class GodDao_Impl implements GodDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GodEntity> __insertionAdapterOfGodEntity;

  private final Converters __converters = new Converters();

  public GodDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGodEntity = new EntityInsertionAdapter<GodEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `gods` (`id`,`patchVersion`,`attackSpeed`,`attackSpeedPerLevel`,`autoBanned`,`cons`,`hp5PerLevel`,`health`,`healthPerFive`,`healthPerLevel`,`lore`,`mp5PerLevel`,`magicProtection`,`magicProtectionPerLevel`,`magicalPower`,`magicalPowerPerLevel`,`mana`,`manaPerFive`,`manaPerLevel`,`name`,`onFreeRotation`,`pantheon`,`physicalPower`,`physicalPowerPerLevel`,`physicalProtection`,`physicalProtectionPerLevel`,`pros`,`roles`,`speed`,`title`,`type`,`godCardURL`,`godIconURL`,`latestGod`,`ability_details_1_id`,`ability_details_1_summary`,`ability_details_1_url`,`ability_details_1_cooldown`,`ability_details_1_cost`,`ability_details_1_description`,`ability_details_1_menuItems`,`ability_details_1_rankItems`,`ability_details_2_id`,`ability_details_2_summary`,`ability_details_2_url`,`ability_details_2_cooldown`,`ability_details_2_cost`,`ability_details_2_description`,`ability_details_2_menuItems`,`ability_details_2_rankItems`,`ability_details_3_id`,`ability_details_3_summary`,`ability_details_3_url`,`ability_details_3_cooldown`,`ability_details_3_cost`,`ability_details_3_description`,`ability_details_3_menuItems`,`ability_details_3_rankItems`,`ability_details_4_id`,`ability_details_4_summary`,`ability_details_4_url`,`ability_details_4_cooldown`,`ability_details_4_cost`,`ability_details_4_description`,`ability_details_4_menuItems`,`ability_details_4_rankItems`,`ability_details_5_id`,`ability_details_5_summary`,`ability_details_5_url`,`ability_details_5_cooldown`,`ability_details_5_cost`,`ability_details_5_description`,`ability_details_5_menuItems`,`ability_details_5_rankItems`,`basic_attack_desccooldown`,`basic_attack_desccost`,`basic_attack_descdescription`,`basic_attack_descmenuItems`,`basic_attack_descrankItems`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GodEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getPatchVersion() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPatchVersion());
        }
        stmt.bindDouble(3, value.getAttackSpeed());
        stmt.bindDouble(4, value.getAttackSpeedPerLevel());
        final int _tmp = value.getAutoBanned() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getCons() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCons());
        }
        stmt.bindDouble(7, value.getHp5PerLevel());
        stmt.bindLong(8, value.getHealth());
        stmt.bindDouble(9, value.getHealthPerFive());
        stmt.bindDouble(10, value.getHealthPerLevel());
        if (value.getLore() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLore());
        }
        stmt.bindDouble(12, value.getMp5PerLevel());
        stmt.bindDouble(13, value.getMagicProtection());
        stmt.bindDouble(14, value.getMagicProtectionPerLevel());
        stmt.bindLong(15, value.getMagicalPower());
        stmt.bindDouble(16, value.getMagicalPowerPerLevel());
        stmt.bindLong(17, value.getMana());
        stmt.bindDouble(18, value.getManaPerFive());
        stmt.bindDouble(19, value.getManaPerLevel());
        if (value.getName() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getName());
        }
        final int _tmp_1 = value.getOnFreeRotation() ? 1 : 0;
        stmt.bindLong(21, _tmp_1);
        if (value.getPantheon() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getPantheon());
        }
        stmt.bindLong(23, value.getPhysicalPower());
        stmt.bindDouble(24, value.getPhysicalPowerPerLevel());
        stmt.bindDouble(25, value.getPhysicalProtection());
        stmt.bindDouble(26, value.getPhysicalProtectionPerLevel());
        if (value.getPros() == null) {
          stmt.bindNull(27);
        } else {
          stmt.bindString(27, value.getPros());
        }
        if (value.getRoles() == null) {
          stmt.bindNull(28);
        } else {
          stmt.bindString(28, value.getRoles());
        }
        stmt.bindLong(29, value.getSpeed());
        if (value.getTitle() == null) {
          stmt.bindNull(30);
        } else {
          stmt.bindString(30, value.getTitle());
        }
        if (value.getType() == null) {
          stmt.bindNull(31);
        } else {
          stmt.bindString(31, value.getType());
        }
        if (value.getGodCardURL() == null) {
          stmt.bindNull(32);
        } else {
          stmt.bindString(32, value.getGodCardURL());
        }
        if (value.getGodIconURL() == null) {
          stmt.bindNull(33);
        } else {
          stmt.bindString(33, value.getGodIconURL());
        }
        final int _tmp_2 = value.getLatestGod() ? 1 : 0;
        stmt.bindLong(34, _tmp_2);
        final Ability _tmpAbilityDetails1 = value.getAbilityDetails1();
        if(_tmpAbilityDetails1 != null) {
          stmt.bindLong(35, _tmpAbilityDetails1.getId());
          if (_tmpAbilityDetails1.getSummary() == null) {
            stmt.bindNull(36);
          } else {
            stmt.bindString(36, _tmpAbilityDetails1.getSummary());
          }
          if (_tmpAbilityDetails1.getUrl() == null) {
            stmt.bindNull(37);
          } else {
            stmt.bindString(37, _tmpAbilityDetails1.getUrl());
          }
          final AbilityDescription _tmpDescription = _tmpAbilityDetails1.getDescription();
          if(_tmpDescription != null) {
            if (_tmpDescription.getCooldown() == null) {
              stmt.bindNull(38);
            } else {
              stmt.bindString(38, _tmpDescription.getCooldown());
            }
            if (_tmpDescription.getCost() == null) {
              stmt.bindNull(39);
            } else {
              stmt.bindString(39, _tmpDescription.getCost());
            }
            if (_tmpDescription.getDescription() == null) {
              stmt.bindNull(40);
            } else {
              stmt.bindString(40, _tmpDescription.getDescription());
            }
            final String _tmp_3 = __converters.fromList(_tmpDescription.getMenuItems());
            if (_tmp_3 == null) {
              stmt.bindNull(41);
            } else {
              stmt.bindString(41, _tmp_3);
            }
            final String _tmp_4 = __converters.fromList(_tmpDescription.getRankItems());
            if (_tmp_4 == null) {
              stmt.bindNull(42);
            } else {
              stmt.bindString(42, _tmp_4);
            }
          } else {
            stmt.bindNull(38);
            stmt.bindNull(39);
            stmt.bindNull(40);
            stmt.bindNull(41);
            stmt.bindNull(42);
          }
        } else {
          stmt.bindNull(35);
          stmt.bindNull(36);
          stmt.bindNull(37);
          stmt.bindNull(38);
          stmt.bindNull(39);
          stmt.bindNull(40);
          stmt.bindNull(41);
          stmt.bindNull(42);
        }
        final Ability _tmpAbilityDetails2 = value.getAbilityDetails2();
        if(_tmpAbilityDetails2 != null) {
          stmt.bindLong(43, _tmpAbilityDetails2.getId());
          if (_tmpAbilityDetails2.getSummary() == null) {
            stmt.bindNull(44);
          } else {
            stmt.bindString(44, _tmpAbilityDetails2.getSummary());
          }
          if (_tmpAbilityDetails2.getUrl() == null) {
            stmt.bindNull(45);
          } else {
            stmt.bindString(45, _tmpAbilityDetails2.getUrl());
          }
          final AbilityDescription _tmpDescription_1 = _tmpAbilityDetails2.getDescription();
          if(_tmpDescription_1 != null) {
            if (_tmpDescription_1.getCooldown() == null) {
              stmt.bindNull(46);
            } else {
              stmt.bindString(46, _tmpDescription_1.getCooldown());
            }
            if (_tmpDescription_1.getCost() == null) {
              stmt.bindNull(47);
            } else {
              stmt.bindString(47, _tmpDescription_1.getCost());
            }
            if (_tmpDescription_1.getDescription() == null) {
              stmt.bindNull(48);
            } else {
              stmt.bindString(48, _tmpDescription_1.getDescription());
            }
            final String _tmp_5 = __converters.fromList(_tmpDescription_1.getMenuItems());
            if (_tmp_5 == null) {
              stmt.bindNull(49);
            } else {
              stmt.bindString(49, _tmp_5);
            }
            final String _tmp_6 = __converters.fromList(_tmpDescription_1.getRankItems());
            if (_tmp_6 == null) {
              stmt.bindNull(50);
            } else {
              stmt.bindString(50, _tmp_6);
            }
          } else {
            stmt.bindNull(46);
            stmt.bindNull(47);
            stmt.bindNull(48);
            stmt.bindNull(49);
            stmt.bindNull(50);
          }
        } else {
          stmt.bindNull(43);
          stmt.bindNull(44);
          stmt.bindNull(45);
          stmt.bindNull(46);
          stmt.bindNull(47);
          stmt.bindNull(48);
          stmt.bindNull(49);
          stmt.bindNull(50);
        }
        final Ability _tmpAbilityDetails3 = value.getAbilityDetails3();
        if(_tmpAbilityDetails3 != null) {
          stmt.bindLong(51, _tmpAbilityDetails3.getId());
          if (_tmpAbilityDetails3.getSummary() == null) {
            stmt.bindNull(52);
          } else {
            stmt.bindString(52, _tmpAbilityDetails3.getSummary());
          }
          if (_tmpAbilityDetails3.getUrl() == null) {
            stmt.bindNull(53);
          } else {
            stmt.bindString(53, _tmpAbilityDetails3.getUrl());
          }
          final AbilityDescription _tmpDescription_2 = _tmpAbilityDetails3.getDescription();
          if(_tmpDescription_2 != null) {
            if (_tmpDescription_2.getCooldown() == null) {
              stmt.bindNull(54);
            } else {
              stmt.bindString(54, _tmpDescription_2.getCooldown());
            }
            if (_tmpDescription_2.getCost() == null) {
              stmt.bindNull(55);
            } else {
              stmt.bindString(55, _tmpDescription_2.getCost());
            }
            if (_tmpDescription_2.getDescription() == null) {
              stmt.bindNull(56);
            } else {
              stmt.bindString(56, _tmpDescription_2.getDescription());
            }
            final String _tmp_7 = __converters.fromList(_tmpDescription_2.getMenuItems());
            if (_tmp_7 == null) {
              stmt.bindNull(57);
            } else {
              stmt.bindString(57, _tmp_7);
            }
            final String _tmp_8 = __converters.fromList(_tmpDescription_2.getRankItems());
            if (_tmp_8 == null) {
              stmt.bindNull(58);
            } else {
              stmt.bindString(58, _tmp_8);
            }
          } else {
            stmt.bindNull(54);
            stmt.bindNull(55);
            stmt.bindNull(56);
            stmt.bindNull(57);
            stmt.bindNull(58);
          }
        } else {
          stmt.bindNull(51);
          stmt.bindNull(52);
          stmt.bindNull(53);
          stmt.bindNull(54);
          stmt.bindNull(55);
          stmt.bindNull(56);
          stmt.bindNull(57);
          stmt.bindNull(58);
        }
        final Ability _tmpAbilityDetails4 = value.getAbilityDetails4();
        if(_tmpAbilityDetails4 != null) {
          stmt.bindLong(59, _tmpAbilityDetails4.getId());
          if (_tmpAbilityDetails4.getSummary() == null) {
            stmt.bindNull(60);
          } else {
            stmt.bindString(60, _tmpAbilityDetails4.getSummary());
          }
          if (_tmpAbilityDetails4.getUrl() == null) {
            stmt.bindNull(61);
          } else {
            stmt.bindString(61, _tmpAbilityDetails4.getUrl());
          }
          final AbilityDescription _tmpDescription_3 = _tmpAbilityDetails4.getDescription();
          if(_tmpDescription_3 != null) {
            if (_tmpDescription_3.getCooldown() == null) {
              stmt.bindNull(62);
            } else {
              stmt.bindString(62, _tmpDescription_3.getCooldown());
            }
            if (_tmpDescription_3.getCost() == null) {
              stmt.bindNull(63);
            } else {
              stmt.bindString(63, _tmpDescription_3.getCost());
            }
            if (_tmpDescription_3.getDescription() == null) {
              stmt.bindNull(64);
            } else {
              stmt.bindString(64, _tmpDescription_3.getDescription());
            }
            final String _tmp_9 = __converters.fromList(_tmpDescription_3.getMenuItems());
            if (_tmp_9 == null) {
              stmt.bindNull(65);
            } else {
              stmt.bindString(65, _tmp_9);
            }
            final String _tmp_10 = __converters.fromList(_tmpDescription_3.getRankItems());
            if (_tmp_10 == null) {
              stmt.bindNull(66);
            } else {
              stmt.bindString(66, _tmp_10);
            }
          } else {
            stmt.bindNull(62);
            stmt.bindNull(63);
            stmt.bindNull(64);
            stmt.bindNull(65);
            stmt.bindNull(66);
          }
        } else {
          stmt.bindNull(59);
          stmt.bindNull(60);
          stmt.bindNull(61);
          stmt.bindNull(62);
          stmt.bindNull(63);
          stmt.bindNull(64);
          stmt.bindNull(65);
          stmt.bindNull(66);
        }
        final Ability _tmpAbilityDetails5 = value.getAbilityDetails5();
        if(_tmpAbilityDetails5 != null) {
          stmt.bindLong(67, _tmpAbilityDetails5.getId());
          if (_tmpAbilityDetails5.getSummary() == null) {
            stmt.bindNull(68);
          } else {
            stmt.bindString(68, _tmpAbilityDetails5.getSummary());
          }
          if (_tmpAbilityDetails5.getUrl() == null) {
            stmt.bindNull(69);
          } else {
            stmt.bindString(69, _tmpAbilityDetails5.getUrl());
          }
          final AbilityDescription _tmpDescription_4 = _tmpAbilityDetails5.getDescription();
          if(_tmpDescription_4 != null) {
            if (_tmpDescription_4.getCooldown() == null) {
              stmt.bindNull(70);
            } else {
              stmt.bindString(70, _tmpDescription_4.getCooldown());
            }
            if (_tmpDescription_4.getCost() == null) {
              stmt.bindNull(71);
            } else {
              stmt.bindString(71, _tmpDescription_4.getCost());
            }
            if (_tmpDescription_4.getDescription() == null) {
              stmt.bindNull(72);
            } else {
              stmt.bindString(72, _tmpDescription_4.getDescription());
            }
            final String _tmp_11 = __converters.fromList(_tmpDescription_4.getMenuItems());
            if (_tmp_11 == null) {
              stmt.bindNull(73);
            } else {
              stmt.bindString(73, _tmp_11);
            }
            final String _tmp_12 = __converters.fromList(_tmpDescription_4.getRankItems());
            if (_tmp_12 == null) {
              stmt.bindNull(74);
            } else {
              stmt.bindString(74, _tmp_12);
            }
          } else {
            stmt.bindNull(70);
            stmt.bindNull(71);
            stmt.bindNull(72);
            stmt.bindNull(73);
            stmt.bindNull(74);
          }
        } else {
          stmt.bindNull(67);
          stmt.bindNull(68);
          stmt.bindNull(69);
          stmt.bindNull(70);
          stmt.bindNull(71);
          stmt.bindNull(72);
          stmt.bindNull(73);
          stmt.bindNull(74);
        }
        final AbilityDescription _tmpBasicAttack = value.getBasicAttack();
        if(_tmpBasicAttack != null) {
          if (_tmpBasicAttack.getCooldown() == null) {
            stmt.bindNull(75);
          } else {
            stmt.bindString(75, _tmpBasicAttack.getCooldown());
          }
          if (_tmpBasicAttack.getCost() == null) {
            stmt.bindNull(76);
          } else {
            stmt.bindString(76, _tmpBasicAttack.getCost());
          }
          if (_tmpBasicAttack.getDescription() == null) {
            stmt.bindNull(77);
          } else {
            stmt.bindString(77, _tmpBasicAttack.getDescription());
          }
          final String _tmp_13 = __converters.fromList(_tmpBasicAttack.getMenuItems());
          if (_tmp_13 == null) {
            stmt.bindNull(78);
          } else {
            stmt.bindString(78, _tmp_13);
          }
          final String _tmp_14 = __converters.fromList(_tmpBasicAttack.getRankItems());
          if (_tmp_14 == null) {
            stmt.bindNull(79);
          } else {
            stmt.bindString(79, _tmp_14);
          }
        } else {
          stmt.bindNull(75);
          stmt.bindNull(76);
          stmt.bindNull(77);
          stmt.bindNull(78);
          stmt.bindNull(79);
        }
      }
    };
  }

  @Override
  public Object insertAll(final List<GodEntity> gods,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGodEntity.insert(gods);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<GodEntity>> getAll() {
    final String _sql = "SELECT * FROM gods";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"gods"}, new Callable<List<GodEntity>>() {
      @Override
      public List<GodEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatchVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "patchVersion");
          final int _cursorIndexOfAttackSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "attackSpeed");
          final int _cursorIndexOfAttackSpeedPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "attackSpeedPerLevel");
          final int _cursorIndexOfAutoBanned = CursorUtil.getColumnIndexOrThrow(_cursor, "autoBanned");
          final int _cursorIndexOfCons = CursorUtil.getColumnIndexOrThrow(_cursor, "cons");
          final int _cursorIndexOfHp5PerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "hp5PerLevel");
          final int _cursorIndexOfHealth = CursorUtil.getColumnIndexOrThrow(_cursor, "health");
          final int _cursorIndexOfHealthPerFive = CursorUtil.getColumnIndexOrThrow(_cursor, "healthPerFive");
          final int _cursorIndexOfHealthPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "healthPerLevel");
          final int _cursorIndexOfLore = CursorUtil.getColumnIndexOrThrow(_cursor, "lore");
          final int _cursorIndexOfMp5PerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "mp5PerLevel");
          final int _cursorIndexOfMagicProtection = CursorUtil.getColumnIndexOrThrow(_cursor, "magicProtection");
          final int _cursorIndexOfMagicProtectionPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "magicProtectionPerLevel");
          final int _cursorIndexOfMagicalPower = CursorUtil.getColumnIndexOrThrow(_cursor, "magicalPower");
          final int _cursorIndexOfMagicalPowerPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "magicalPowerPerLevel");
          final int _cursorIndexOfMana = CursorUtil.getColumnIndexOrThrow(_cursor, "mana");
          final int _cursorIndexOfManaPerFive = CursorUtil.getColumnIndexOrThrow(_cursor, "manaPerFive");
          final int _cursorIndexOfManaPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "manaPerLevel");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfOnFreeRotation = CursorUtil.getColumnIndexOrThrow(_cursor, "onFreeRotation");
          final int _cursorIndexOfPantheon = CursorUtil.getColumnIndexOrThrow(_cursor, "pantheon");
          final int _cursorIndexOfPhysicalPower = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalPower");
          final int _cursorIndexOfPhysicalPowerPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalPowerPerLevel");
          final int _cursorIndexOfPhysicalProtection = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalProtection");
          final int _cursorIndexOfPhysicalProtectionPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalProtectionPerLevel");
          final int _cursorIndexOfPros = CursorUtil.getColumnIndexOrThrow(_cursor, "pros");
          final int _cursorIndexOfRoles = CursorUtil.getColumnIndexOrThrow(_cursor, "roles");
          final int _cursorIndexOfSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "speed");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfGodCardURL = CursorUtil.getColumnIndexOrThrow(_cursor, "godCardURL");
          final int _cursorIndexOfGodIconURL = CursorUtil.getColumnIndexOrThrow(_cursor, "godIconURL");
          final int _cursorIndexOfLatestGod = CursorUtil.getColumnIndexOrThrow(_cursor, "latestGod");
          final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_id");
          final int _cursorIndexOfSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_summary");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_url");
          final int _cursorIndexOfCooldown = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_cooldown");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_cost");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_description");
          final int _cursorIndexOfMenuItems = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_menuItems");
          final int _cursorIndexOfRankItems = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_rankItems");
          final int _cursorIndexOfId_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_id");
          final int _cursorIndexOfSummary_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_summary");
          final int _cursorIndexOfUrl_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_url");
          final int _cursorIndexOfCooldown_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_cooldown");
          final int _cursorIndexOfCost_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_cost");
          final int _cursorIndexOfDescription_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_description");
          final int _cursorIndexOfMenuItems_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_menuItems");
          final int _cursorIndexOfRankItems_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_rankItems");
          final int _cursorIndexOfId_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_id");
          final int _cursorIndexOfSummary_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_summary");
          final int _cursorIndexOfUrl_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_url");
          final int _cursorIndexOfCooldown_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_cooldown");
          final int _cursorIndexOfCost_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_cost");
          final int _cursorIndexOfDescription_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_description");
          final int _cursorIndexOfMenuItems_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_menuItems");
          final int _cursorIndexOfRankItems_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_rankItems");
          final int _cursorIndexOfId_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_id");
          final int _cursorIndexOfSummary_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_summary");
          final int _cursorIndexOfUrl_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_url");
          final int _cursorIndexOfCooldown_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_cooldown");
          final int _cursorIndexOfCost_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_cost");
          final int _cursorIndexOfDescription_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_description");
          final int _cursorIndexOfMenuItems_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_menuItems");
          final int _cursorIndexOfRankItems_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_rankItems");
          final int _cursorIndexOfId_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_id");
          final int _cursorIndexOfSummary_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_summary");
          final int _cursorIndexOfUrl_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_url");
          final int _cursorIndexOfCooldown_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_cooldown");
          final int _cursorIndexOfCost_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_cost");
          final int _cursorIndexOfDescription_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_description");
          final int _cursorIndexOfMenuItems_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_menuItems");
          final int _cursorIndexOfRankItems_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_rankItems");
          final int _cursorIndexOfCooldown_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_desccooldown");
          final int _cursorIndexOfCost_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_desccost");
          final int _cursorIndexOfDescription_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_descdescription");
          final int _cursorIndexOfMenuItems_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_descmenuItems");
          final int _cursorIndexOfRankItems_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_descrankItems");
          final List<GodEntity> _result = new ArrayList<GodEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final GodEntity _item;
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
            _item = new GodEntity(_tmpId,_tmpPatchVersion,_tmpAbilityDetails1,_tmpAbilityDetails2,_tmpAbilityDetails3,_tmpAbilityDetails4,_tmpAbilityDetails5,_tmpBasicAttack,_tmpAttackSpeed,_tmpAttackSpeedPerLevel,_tmpAutoBanned,_tmpCons,_tmpHp5PerLevel,_tmpHealth,_tmpHealthPerFive,_tmpHealthPerLevel,_tmpLore,_tmpMp5PerLevel,_tmpMagicProtection,_tmpMagicProtectionPerLevel,_tmpMagicalPower,_tmpMagicalPowerPerLevel,_tmpMana,_tmpManaPerFive,_tmpManaPerLevel,_tmpName,_tmpOnFreeRotation,_tmpPantheon,_tmpPhysicalPower,_tmpPhysicalPowerPerLevel,_tmpPhysicalProtection,_tmpPhysicalProtectionPerLevel,_tmpPros,_tmpRoles,_tmpSpeed,_tmpTitle,_tmpType,_tmpGodCardURL,_tmpGodIconURL,_tmpLatestGod);
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
  public Flow<GodEntity> getGod(final int godId) {
    final String _sql = "SELECT * FROM gods WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, godId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"gods"}, new Callable<GodEntity>() {
      @Override
      public GodEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPatchVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "patchVersion");
          final int _cursorIndexOfAttackSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "attackSpeed");
          final int _cursorIndexOfAttackSpeedPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "attackSpeedPerLevel");
          final int _cursorIndexOfAutoBanned = CursorUtil.getColumnIndexOrThrow(_cursor, "autoBanned");
          final int _cursorIndexOfCons = CursorUtil.getColumnIndexOrThrow(_cursor, "cons");
          final int _cursorIndexOfHp5PerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "hp5PerLevel");
          final int _cursorIndexOfHealth = CursorUtil.getColumnIndexOrThrow(_cursor, "health");
          final int _cursorIndexOfHealthPerFive = CursorUtil.getColumnIndexOrThrow(_cursor, "healthPerFive");
          final int _cursorIndexOfHealthPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "healthPerLevel");
          final int _cursorIndexOfLore = CursorUtil.getColumnIndexOrThrow(_cursor, "lore");
          final int _cursorIndexOfMp5PerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "mp5PerLevel");
          final int _cursorIndexOfMagicProtection = CursorUtil.getColumnIndexOrThrow(_cursor, "magicProtection");
          final int _cursorIndexOfMagicProtectionPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "magicProtectionPerLevel");
          final int _cursorIndexOfMagicalPower = CursorUtil.getColumnIndexOrThrow(_cursor, "magicalPower");
          final int _cursorIndexOfMagicalPowerPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "magicalPowerPerLevel");
          final int _cursorIndexOfMana = CursorUtil.getColumnIndexOrThrow(_cursor, "mana");
          final int _cursorIndexOfManaPerFive = CursorUtil.getColumnIndexOrThrow(_cursor, "manaPerFive");
          final int _cursorIndexOfManaPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "manaPerLevel");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfOnFreeRotation = CursorUtil.getColumnIndexOrThrow(_cursor, "onFreeRotation");
          final int _cursorIndexOfPantheon = CursorUtil.getColumnIndexOrThrow(_cursor, "pantheon");
          final int _cursorIndexOfPhysicalPower = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalPower");
          final int _cursorIndexOfPhysicalPowerPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalPowerPerLevel");
          final int _cursorIndexOfPhysicalProtection = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalProtection");
          final int _cursorIndexOfPhysicalProtectionPerLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "physicalProtectionPerLevel");
          final int _cursorIndexOfPros = CursorUtil.getColumnIndexOrThrow(_cursor, "pros");
          final int _cursorIndexOfRoles = CursorUtil.getColumnIndexOrThrow(_cursor, "roles");
          final int _cursorIndexOfSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "speed");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfGodCardURL = CursorUtil.getColumnIndexOrThrow(_cursor, "godCardURL");
          final int _cursorIndexOfGodIconURL = CursorUtil.getColumnIndexOrThrow(_cursor, "godIconURL");
          final int _cursorIndexOfLatestGod = CursorUtil.getColumnIndexOrThrow(_cursor, "latestGod");
          final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_id");
          final int _cursorIndexOfSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_summary");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_url");
          final int _cursorIndexOfCooldown = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_cooldown");
          final int _cursorIndexOfCost = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_cost");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_description");
          final int _cursorIndexOfMenuItems = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_menuItems");
          final int _cursorIndexOfRankItems = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_1_rankItems");
          final int _cursorIndexOfId_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_id");
          final int _cursorIndexOfSummary_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_summary");
          final int _cursorIndexOfUrl_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_url");
          final int _cursorIndexOfCooldown_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_cooldown");
          final int _cursorIndexOfCost_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_cost");
          final int _cursorIndexOfDescription_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_description");
          final int _cursorIndexOfMenuItems_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_menuItems");
          final int _cursorIndexOfRankItems_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_2_rankItems");
          final int _cursorIndexOfId_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_id");
          final int _cursorIndexOfSummary_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_summary");
          final int _cursorIndexOfUrl_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_url");
          final int _cursorIndexOfCooldown_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_cooldown");
          final int _cursorIndexOfCost_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_cost");
          final int _cursorIndexOfDescription_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_description");
          final int _cursorIndexOfMenuItems_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_menuItems");
          final int _cursorIndexOfRankItems_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_3_rankItems");
          final int _cursorIndexOfId_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_id");
          final int _cursorIndexOfSummary_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_summary");
          final int _cursorIndexOfUrl_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_url");
          final int _cursorIndexOfCooldown_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_cooldown");
          final int _cursorIndexOfCost_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_cost");
          final int _cursorIndexOfDescription_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_description");
          final int _cursorIndexOfMenuItems_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_menuItems");
          final int _cursorIndexOfRankItems_3 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_4_rankItems");
          final int _cursorIndexOfId_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_id");
          final int _cursorIndexOfSummary_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_summary");
          final int _cursorIndexOfUrl_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_url");
          final int _cursorIndexOfCooldown_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_cooldown");
          final int _cursorIndexOfCost_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_cost");
          final int _cursorIndexOfDescription_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_description");
          final int _cursorIndexOfMenuItems_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_menuItems");
          final int _cursorIndexOfRankItems_4 = CursorUtil.getColumnIndexOrThrow(_cursor, "ability_details_5_rankItems");
          final int _cursorIndexOfCooldown_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_desccooldown");
          final int _cursorIndexOfCost_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_desccost");
          final int _cursorIndexOfDescription_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_descdescription");
          final int _cursorIndexOfMenuItems_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_descmenuItems");
          final int _cursorIndexOfRankItems_5 = CursorUtil.getColumnIndexOrThrow(_cursor, "basic_attack_descrankItems");
          final GodEntity _result;
          if(_cursor.moveToFirst()) {
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
            _result = new GodEntity(_tmpId,_tmpPatchVersion,_tmpAbilityDetails1,_tmpAbilityDetails2,_tmpAbilityDetails3,_tmpAbilityDetails4,_tmpAbilityDetails5,_tmpBasicAttack,_tmpAttackSpeed,_tmpAttackSpeedPerLevel,_tmpAutoBanned,_tmpCons,_tmpHp5PerLevel,_tmpHealth,_tmpHealthPerFive,_tmpHealthPerLevel,_tmpLore,_tmpMp5PerLevel,_tmpMagicProtection,_tmpMagicProtectionPerLevel,_tmpMagicalPower,_tmpMagicalPowerPerLevel,_tmpMana,_tmpManaPerFive,_tmpManaPerLevel,_tmpName,_tmpOnFreeRotation,_tmpPantheon,_tmpPhysicalPower,_tmpPhysicalPowerPerLevel,_tmpPhysicalProtection,_tmpPhysicalProtectionPerLevel,_tmpPros,_tmpRoles,_tmpSpeed,_tmpTitle,_tmpType,_tmpGodCardURL,_tmpGodIconURL,_tmpLatestGod);
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
