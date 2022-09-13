package net.kabl.northfall;

import net.fabricmc.api.ClientModInitializer;
import net.kabl.northfall.networking.ModMessages;

public class NorthfallClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
    }
}
