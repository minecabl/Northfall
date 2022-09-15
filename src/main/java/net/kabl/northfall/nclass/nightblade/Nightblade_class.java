package net.kabl.northfall.nclass.nightblade;

import net.kabl.northfall.Northfall;
import net.kabl.northfall.nclass.NClass;
import net.minecraft.util.Identifier;

public class Nightblade_class extends NClass {



    public Nightblade_class(){
        setClass_name(new Identifier(Northfall.MOD_ID, "nightblade_class"));
        setHealthBase(20);
        setManaBase(100);
    }

}
