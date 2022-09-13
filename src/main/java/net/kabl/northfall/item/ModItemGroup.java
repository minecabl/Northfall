package net.kabl.northfall.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kabl.northfall.Northfall;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup Northfall = FabricItemGroupBuilder.build(new Identifier(net.kabl.northfall.Northfall.MOD_ID, "northfall_itemgroup"), () -> new ItemStack(Items.CARROT_ON_A_STICK));
}
