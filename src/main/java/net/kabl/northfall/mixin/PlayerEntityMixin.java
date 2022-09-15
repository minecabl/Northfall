/*
    Class: Inject and Overwrite stuff in PlayerEntity class
    Author: Kabl
    Date: 14.09.2022
    Last Change: 15.09.2022
    Returns: nothing
    Desc:
        Change things of the Player entity:
            - MaxHealth
*/

package net.kabl.northfall.mixin;

import com.mojang.authlib.GameProfile;
import net.kabl.northfall.playerdata.PlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {

    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /*
    Function: Inject custom maxhealth into PlayerEntity constructor
    Author: Kabl
    Date: 14.09.2022
    Last Change: 15.09.2022
    Returns: nothing
    Desc:
        Change things of the Player entity:
            - MaxHealth
    */

    @Inject(at = @At("TAIL"), method = "<init>")
    protected void constructorInjector(World world, BlockPos pos, float yaw, GameProfile gameProfile, PlayerPublicKey publicKey, CallbackInfo ci){
        PlayerData data = new PlayerData((PlayerEntity)(Entity)this);
        ((LivingEntity)(Entity)this).getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(data.getNorthfallMaxHealth());
    }

}
