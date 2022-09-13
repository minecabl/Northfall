package net.kabl.northfall;

import net.fabricmc.api.ModInitializer;
import net.kabl.northfall.block.ModBlocks;
import net.kabl.northfall.item.ModItems;
import net.kabl.northfall.networking.ModMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Northfall implements ModInitializer {
	public static final String MOD_ID = "northfall";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModMessages.registerC2SPackets();

		LOGGER.info("Northfall loaded!");
	}
}
