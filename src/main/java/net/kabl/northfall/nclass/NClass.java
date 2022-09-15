package net.kabl.northfall.nclass;

import net.minecraft.util.Identifier;

public abstract class NClass {
    private static Identifier CLASS_NAME;

    private static double HEALTH_BASE;
    private static double MANA_BASE;

    private static double HEALTH_PER_LEVEL;
    private static double MANA_PER_LEVEL;

    private static NSkill[] SKILLS;
    private static int[] UPGRADES;


    public static Identifier getClass_name() {
        return CLASS_NAME;
    }

    public static void setClass_name(Identifier class_name) {
        NClass.CLASS_NAME = class_name;
    }

    public static double getHealthBase() {
        return HEALTH_BASE;
    }

    public static void setHealthBase(double healthBase) {
        HEALTH_BASE = healthBase;
    }

    public static double getManaBase() {
        return MANA_BASE;
    }

    public static void setManaBase(double manaBase) {
        MANA_BASE = manaBase;
    }
}
