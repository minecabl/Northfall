package net.kabl.northfall.item;

import net.kabl.northfall.Northfall;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    //Register Moditems with ID here
    //Example:
    //public static final Item MYTRHIL_INGOT = registerItem("mythril_ingot", new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Northfall.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Northfall.LOGGER.info("Northfall Items registered!");
    }
}
