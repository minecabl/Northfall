/*
    Class: Handle Playerdata
    Author: Kabl
    Date: 13.09.2022
    Last Change: 15.09.2022
    Desc:
        Handle all custom data stored on the player
*/

package net.kabl.northfall.playerdata;

import net.kabl.northfall.nclass.NClass;
import net.kabl.northfall.util.IEntityDataSaver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PlayerData {
    PlayerEntity player;
    IEntityDataSaver iplayer;
    NbtCompound nbt;

    double NORTHFALL_MAX_HEALTH;
    double NORTHFALL_MAX_MANA;
    int NORTHFALL_EXP;
    NClass CURRENT_CLASS;

    public PlayerData(PlayerEntity p){
        this.player = p;
        this.iplayer = (IEntityDataSaver)p;
        nbt = iplayer.getPersistentData();

        //Get current MAX_HEALTH or, if no previous MAX_HEALTH found, set default MAX_HEALTH
        this.NORTHFALL_MAX_HEALTH = nbt.contains("NORTHFALL_MAX_HEALTH") ? nbt.getDouble("NORTHFALL_MAX_HEALTH") : CURRENT_CLASS.getHealthBase();

        //Get current MAX_MANA or, if no previous MAX_MANA found, set default MAX_MANA
        this.NORTHFALL_MAX_MANA = nbt.contains("NORTHFALL_MAX_MANA") ? nbt.getDouble("NORTHFALL_MAX_MANA") : CURRENT_CLASS.getManaBase();
    }

    public void setNorthfallMaxHealth(){

    }

    public double getNorthfallMaxHealth(){
        NbtCompound nbt = iplayer.getPersistentData();
        this.NORTHFALL_MAX_HEALTH = nbt.getDouble("NORTHFALL_MAX_HEALTH");
        return this.NORTHFALL_MAX_HEALTH;
    }

    public void setNORTHFALL_MAX_MANA(){

    }

    public double getNorthfallMaxMana(){
        NbtCompound nbt = iplayer.getPersistentData();
        this.NORTHFALL_MAX_HEALTH = nbt.getDouble("NORTHFALL_MAX_MANA");
        return this.NORTHFALL_MAX_MANA;
    }
}
