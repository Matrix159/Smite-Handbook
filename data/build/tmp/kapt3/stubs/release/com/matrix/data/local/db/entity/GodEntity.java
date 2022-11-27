package com.matrix.data.local.db.entity;

import java.lang.System;

@androidx.room.Entity(tableName = "gods")
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\by\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u0092\u00012\u00020\u0001:\u0002\u0092\u0001B\u00c9\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u000f\u0012\u0006\u0010\u0018\u001a\u00020\u000f\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u000f\u0012\u0006\u0010\u001b\u001a\u00020\u000f\u0012\u0006\u0010\u001c\u001a\u00020\u000f\u0012\u0006\u0010\u001d\u001a\u00020\u0016\u0012\u0006\u0010\u001e\u001a\u00020\u000f\u0012\u0006\u0010\u001f\u001a\u00020\u0016\u0012\u0006\u0010 \u001a\u00020\u000f\u0012\u0006\u0010!\u001a\u00020\u000f\u0012\u0006\u0010\"\u001a\u00020\u0005\u0012\u0006\u0010#\u001a\u00020\u0012\u0012\u0006\u0010$\u001a\u00020\u0005\u0012\u0006\u0010%\u001a\u00020\u0016\u0012\u0006\u0010&\u001a\u00020\u000f\u0012\u0006\u0010\'\u001a\u00020\u000f\u0012\u0006\u0010(\u001a\u00020\u000f\u0012\u0006\u0010)\u001a\u00020\u0005\u0012\u0006\u0010*\u001a\u00020\u0005\u0012\u0006\u0010+\u001a\u00020\u0016\u0012\u0006\u0010,\u001a\u00020\u0005\u0012\u0006\u0010-\u001a\u00020\u0005\u0012\u0006\u0010.\u001a\u00020\u0005\u0012\u0006\u0010/\u001a\u00020\u0005\u0012\u0006\u00100\u001a\u00020\u0012\u00a2\u0006\u0002\u00101J\t\u0010c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010d\u001a\u00020\u000fH\u00c6\u0003J\t\u0010e\u001a\u00020\u0012H\u00c6\u0003J\t\u0010f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010g\u001a\u00020\u000fH\u00c6\u0003J\t\u0010h\u001a\u00020\u0016H\u00c6\u0003J\t\u0010i\u001a\u00020\u000fH\u00c6\u0003J\t\u0010j\u001a\u00020\u000fH\u00c6\u0003J\t\u0010k\u001a\u00020\u0005H\u00c6\u0003J\t\u0010l\u001a\u00020\u000fH\u00c6\u0003J\t\u0010m\u001a\u00020\u000fH\u00c6\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010o\u001a\u00020\u000fH\u00c6\u0003J\t\u0010p\u001a\u00020\u0016H\u00c6\u0003J\t\u0010q\u001a\u00020\u000fH\u00c6\u0003J\t\u0010r\u001a\u00020\u0016H\u00c6\u0003J\t\u0010s\u001a\u00020\u000fH\u00c6\u0003J\t\u0010t\u001a\u00020\u000fH\u00c6\u0003J\t\u0010u\u001a\u00020\u0005H\u00c6\u0003J\t\u0010v\u001a\u00020\u0012H\u00c6\u0003J\t\u0010w\u001a\u00020\u0005H\u00c6\u0003J\t\u0010x\u001a\u00020\u0016H\u00c6\u0003J\t\u0010y\u001a\u00020\u0007H\u00c6\u0003J\t\u0010z\u001a\u00020\u000fH\u00c6\u0003J\t\u0010{\u001a\u00020\u000fH\u00c6\u0003J\t\u0010|\u001a\u00020\u000fH\u00c6\u0003J\t\u0010}\u001a\u00020\u0005H\u00c6\u0003J\t\u0010~\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u007f\u001a\u00020\u0016H\u00c6\u0003J\n\u0010\u0080\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u0082\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u0083\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u0084\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u0085\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u0086\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u0088\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u0089\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u008a\u0001\u001a\u00020\u000fH\u00c6\u0003J\u009c\u0003\u0010\u008b\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u000f2\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f2\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\b\b\u0002\u0010\u001d\u001a\u00020\u00162\b\b\u0002\u0010\u001e\u001a\u00020\u000f2\b\b\u0002\u0010\u001f\u001a\u00020\u00162\b\b\u0002\u0010 \u001a\u00020\u000f2\b\b\u0002\u0010!\u001a\u00020\u000f2\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00122\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u00162\b\b\u0002\u0010&\u001a\u00020\u000f2\b\b\u0002\u0010\'\u001a\u00020\u000f2\b\b\u0002\u0010(\u001a\u00020\u000f2\b\b\u0002\u0010)\u001a\u00020\u00052\b\b\u0002\u0010*\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020\u00162\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020\u00052\b\b\u0002\u0010.\u001a\u00020\u00052\b\b\u0002\u0010/\u001a\u00020\u00052\b\b\u0002\u00100\u001a\u00020\u0012H\u00c6\u0001J\u0015\u0010\u008c\u0001\u001a\u00020\u00122\t\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010\u008e\u0001\u001a\u00020\u0003H\u00d6\u0001J\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001J\n\u0010\u0091\u0001\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00103R\u0016\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00103R\u0016\u0010\n\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00103R\u0016\u0010\u000b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00103R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u00109R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0011\u0010\u0013\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u001a\u0010.\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010@\"\u0004\bB\u0010CR\u0011\u0010/\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010@R\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0011\u0010\u0017\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u00109R\u0011\u0010\u0018\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u00109R\u0011\u0010\u0014\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u00109R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u0011\u00100\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\bL\u0010<R\u0011\u0010\u0019\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u0010@R\u0011\u0010\u001b\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bN\u00109R\u0011\u0010\u001c\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u00109R\u0011\u0010\u001d\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u0010FR\u0011\u0010\u001e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bQ\u00109R\u0011\u0010\u001f\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\bR\u0010FR\u0011\u0010 \u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bS\u00109R\u0011\u0010!\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bT\u00109R\u0011\u0010\u001a\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\bU\u00109R\u0011\u0010\"\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bV\u0010@R\u0011\u0010#\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\bW\u0010<R\u0011\u0010$\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bX\u0010@R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bY\u0010@R\u0011\u0010%\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\bZ\u0010FR\u0011\u0010&\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b[\u00109R\u0011\u0010\'\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\\\u00109R\u0011\u0010(\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b]\u00109R\u0011\u0010)\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b^\u0010@R\u0011\u0010*\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b_\u0010@R\u0011\u0010+\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b`\u0010FR\u0011\u0010,\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\ba\u0010@R\u0011\u0010-\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bb\u0010@\u00a8\u0006\u0093\u0001"}, d2 = {"Lcom/matrix/data/local/db/entity/GodEntity;", "", "id", "", "patchVersion", "", "abilityDetails1", "Lcom/matrix/data/local/db/entity/Ability;", "abilityDetails2", "abilityDetails3", "abilityDetails4", "abilityDetails5", "basicAttack", "Lcom/matrix/data/local/db/entity/AbilityDescription;", "attackSpeed", "", "attackSpeedPerLevel", "autoBanned", "", "cons", "hp5PerLevel", "health", "", "healthPerFive", "healthPerLevel", "lore", "mp5PerLevel", "magicProtection", "magicProtectionPerLevel", "magicalPower", "magicalPowerPerLevel", "mana", "manaPerFive", "manaPerLevel", "name", "onFreeRotation", "pantheon", "physicalPower", "physicalPowerPerLevel", "physicalProtection", "physicalProtectionPerLevel", "pros", "roles", "speed", "title", "type", "godCardURL", "godIconURL", "latestGod", "(ILjava/lang/String;Lcom/matrix/data/local/db/entity/Ability;Lcom/matrix/data/local/db/entity/Ability;Lcom/matrix/data/local/db/entity/Ability;Lcom/matrix/data/local/db/entity/Ability;Lcom/matrix/data/local/db/entity/Ability;Lcom/matrix/data/local/db/entity/AbilityDescription;DDZLjava/lang/String;DJDDLjava/lang/String;DDDJDJDDLjava/lang/String;ZLjava/lang/String;JDDDLjava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAbilityDetails1", "()Lcom/matrix/data/local/db/entity/Ability;", "getAbilityDetails2", "getAbilityDetails3", "getAbilityDetails4", "getAbilityDetails5", "getAttackSpeed", "()D", "getAttackSpeedPerLevel", "getAutoBanned", "()Z", "getBasicAttack", "()Lcom/matrix/data/local/db/entity/AbilityDescription;", "getCons", "()Ljava/lang/String;", "getGodCardURL", "setGodCardURL", "(Ljava/lang/String;)V", "getGodIconURL", "getHealth", "()J", "getHealthPerFive", "getHealthPerLevel", "getHp5PerLevel", "getId", "()I", "getLatestGod", "getLore", "getMagicProtection", "getMagicProtectionPerLevel", "getMagicalPower", "getMagicalPowerPerLevel", "getMana", "getManaPerFive", "getManaPerLevel", "getMp5PerLevel", "getName", "getOnFreeRotation", "getPantheon", "getPatchVersion", "getPhysicalPower", "getPhysicalPowerPerLevel", "getPhysicalProtection", "getPhysicalProtectionPerLevel", "getPros", "getRoles", "getSpeed", "getTitle", "getType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toDomain", "Lcom/matrix/domain/models/GodInformation;", "toString", "Companion", "data_release"})
public final class GodEntity {
    @androidx.room.PrimaryKey
    private final int id = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String patchVersion = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded(prefix = "ability_details_1_")
    private final com.matrix.data.local.db.entity.Ability abilityDetails1 = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded(prefix = "ability_details_2_")
    private final com.matrix.data.local.db.entity.Ability abilityDetails2 = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded(prefix = "ability_details_3_")
    private final com.matrix.data.local.db.entity.Ability abilityDetails3 = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded(prefix = "ability_details_4_")
    private final com.matrix.data.local.db.entity.Ability abilityDetails4 = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded(prefix = "ability_details_5_")
    private final com.matrix.data.local.db.entity.Ability abilityDetails5 = null;
    @org.jetbrains.annotations.NotNull
    @androidx.room.Embedded(prefix = "basic_attack_desc")
    private final com.matrix.data.local.db.entity.AbilityDescription basicAttack = null;
    private final double attackSpeed = 0.0;
    private final double attackSpeedPerLevel = 0.0;
    private final boolean autoBanned = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String cons = null;
    private final double hp5PerLevel = 0.0;
    private final long health = 0L;
    private final double healthPerFive = 0.0;
    private final double healthPerLevel = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String lore = null;
    private final double mp5PerLevel = 0.0;
    private final double magicProtection = 0.0;
    private final double magicProtectionPerLevel = 0.0;
    private final long magicalPower = 0L;
    private final double magicalPowerPerLevel = 0.0;
    private final long mana = 0L;
    private final double manaPerFive = 0.0;
    private final double manaPerLevel = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    private final boolean onFreeRotation = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String pantheon = null;
    private final long physicalPower = 0L;
    private final double physicalPowerPerLevel = 0.0;
    private final double physicalProtection = 0.0;
    private final double physicalProtectionPerLevel = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String pros = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String roles = null;
    private final long speed = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String type = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String godCardURL;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String godIconURL = null;
    private final boolean latestGod = false;
    @org.jetbrains.annotations.NotNull
    public static final com.matrix.data.local.db.entity.GodEntity.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.GodEntity copy(int id, @org.jetbrains.annotations.Nullable
    java.lang.String patchVersion, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails1, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails2, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails3, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails4, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails5, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.AbilityDescription basicAttack, double attackSpeed, double attackSpeedPerLevel, boolean autoBanned, @org.jetbrains.annotations.NotNull
    java.lang.String cons, double hp5PerLevel, long health, double healthPerFive, double healthPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String lore, double mp5PerLevel, double magicProtection, double magicProtectionPerLevel, long magicalPower, double magicalPowerPerLevel, long mana, double manaPerFive, double manaPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String name, boolean onFreeRotation, @org.jetbrains.annotations.NotNull
    java.lang.String pantheon, long physicalPower, double physicalPowerPerLevel, double physicalProtection, double physicalProtectionPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String pros, @org.jetbrains.annotations.NotNull
    java.lang.String roles, long speed, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String godCardURL, @org.jetbrains.annotations.NotNull
    java.lang.String godIconURL, boolean latestGod) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public GodEntity(int id, @org.jetbrains.annotations.Nullable
    java.lang.String patchVersion, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails1, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails2, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails3, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails4, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.Ability abilityDetails5, @org.jetbrains.annotations.NotNull
    com.matrix.data.local.db.entity.AbilityDescription basicAttack, double attackSpeed, double attackSpeedPerLevel, boolean autoBanned, @org.jetbrains.annotations.NotNull
    java.lang.String cons, double hp5PerLevel, long health, double healthPerFive, double healthPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String lore, double mp5PerLevel, double magicProtection, double magicProtectionPerLevel, long magicalPower, double magicalPowerPerLevel, long mana, double manaPerFive, double manaPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String name, boolean onFreeRotation, @org.jetbrains.annotations.NotNull
    java.lang.String pantheon, long physicalPower, double physicalPowerPerLevel, double physicalProtection, double physicalProtectionPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String pros, @org.jetbrains.annotations.NotNull
    java.lang.String roles, long speed, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String godCardURL, @org.jetbrains.annotations.NotNull
    java.lang.String godIconURL, boolean latestGod) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPatchVersion() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability getAbilityDetails1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability getAbilityDetails2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability getAbilityDetails3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability getAbilityDetails4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.Ability getAbilityDetails5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.AbilityDescription component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.local.db.entity.AbilityDescription getBasicAttack() {
        return null;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    public final double getAttackSpeed() {
        return 0.0;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    public final double getAttackSpeedPerLevel() {
        return 0.0;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final boolean getAutoBanned() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCons() {
        return null;
    }
    
    public final double component13() {
        return 0.0;
    }
    
    public final double getHp5PerLevel() {
        return 0.0;
    }
    
    public final long component14() {
        return 0L;
    }
    
    public final long getHealth() {
        return 0L;
    }
    
    public final double component15() {
        return 0.0;
    }
    
    public final double getHealthPerFive() {
        return 0.0;
    }
    
    public final double component16() {
        return 0.0;
    }
    
    public final double getHealthPerLevel() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLore() {
        return null;
    }
    
    public final double component18() {
        return 0.0;
    }
    
    public final double getMp5PerLevel() {
        return 0.0;
    }
    
    public final double component19() {
        return 0.0;
    }
    
    public final double getMagicProtection() {
        return 0.0;
    }
    
    public final double component20() {
        return 0.0;
    }
    
    public final double getMagicProtectionPerLevel() {
        return 0.0;
    }
    
    public final long component21() {
        return 0L;
    }
    
    public final long getMagicalPower() {
        return 0L;
    }
    
    public final double component22() {
        return 0.0;
    }
    
    public final double getMagicalPowerPerLevel() {
        return 0.0;
    }
    
    public final long component23() {
        return 0L;
    }
    
    public final long getMana() {
        return 0L;
    }
    
    public final double component24() {
        return 0.0;
    }
    
    public final double getManaPerFive() {
        return 0.0;
    }
    
    public final double component25() {
        return 0.0;
    }
    
    public final double getManaPerLevel() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    public final boolean component27() {
        return false;
    }
    
    public final boolean getOnFreeRotation() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPantheon() {
        return null;
    }
    
    public final long component29() {
        return 0L;
    }
    
    public final long getPhysicalPower() {
        return 0L;
    }
    
    public final double component30() {
        return 0.0;
    }
    
    public final double getPhysicalPowerPerLevel() {
        return 0.0;
    }
    
    public final double component31() {
        return 0.0;
    }
    
    public final double getPhysicalProtection() {
        return 0.0;
    }
    
    public final double component32() {
        return 0.0;
    }
    
    public final double getPhysicalProtectionPerLevel() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component33() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPros() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component34() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRoles() {
        return null;
    }
    
    public final long component35() {
        return 0L;
    }
    
    public final long getSpeed() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component36() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component37() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component38() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodCardURL() {
        return null;
    }
    
    public final void setGodCardURL(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component39() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodIconURL() {
        return null;
    }
    
    public final boolean component40() {
        return false;
    }
    
    public final boolean getLatestGod() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.domain.models.GodInformation toDomain() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a8\u0006\t"}, d2 = {"Lcom/matrix/data/local/db/entity/GodEntity$Companion;", "", "()V", "fromApi", "Lcom/matrix/data/local/db/entity/GodEntity;", "god", "Lcom/matrix/data/network/model/GodApiResult;", "patchVersion", "", "data_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.matrix.data.local.db.entity.GodEntity fromApi(@org.jetbrains.annotations.NotNull
        com.matrix.data.network.model.GodApiResult god, @org.jetbrains.annotations.Nullable
        java.lang.String patchVersion) {
            return null;
        }
    }
}