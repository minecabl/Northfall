package net.kabl.northfall.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModMessages {
    //Register Identifier IDs for Packets
    //Example:
    //public static final Identifier EXAMPLE_ID = new Identifier(TestMod.MOD_ID, "example");


    public static void registerC2SPackets(){
        //Register Client to Server Packets
        //Example:
        //ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);

    }

    public static void registerS2CPackets(){
        //Register Server to Client Packets
        //Example:
        //ClientPlayNetworking.registerGlobalReceiver(EXAMPLE2_ID, ExampleS2CPacket::receive);

    }
}
