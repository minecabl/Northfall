package net.kabl.northfall.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kabl.northfall.Northfall;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    //Register Blocks with IDs here
    //Example:
    //public static final Block MYTHRIL_BLOCK = registerBlock("mythril_block", new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.MYTHRIL);



    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(Northfall.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Northfall.MOD_ID, name), block);
    }

    public static void registerModBlocks(){
        Northfall.LOGGER.info("Registering ModBlocks for "+Northfall.MOD_ID);
    }
}
