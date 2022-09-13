package net.kabl.northfall.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.RaycastContext;

import java.util.List;

public class Targeting {
    /*
    Function: Get AOE Target Entities of source entity in range
    Author: Kabl
    Date: 10.09.2022
    Returns: Entity or null
    Desc:
        Get All Entities in Bounding Box of range blocks, including Y level difference
    */

    public static List<Entity> getAOETargets(final LivingEntity e, double range){
        return e.getWorld().getOtherEntities(e, e.getBoundingBox().expand(range));
    }

    /*
    Function: Get target Entity
    Author: Kabl
    Date: 09.09.2022
    Returns: Entity or null
    Desc:
        Default crosshair targeting option, only in range of default hits
     */
    public static Entity getPlayerCrosshairTarget(MinecraftClient c){
        HitResult hit = c.crosshairTarget;

        if(hit.getType() == HitResult.Type.ENTITY){
            //Distance check??
            EntityHitResult entityHit = (EntityHitResult) hit;
            return entityHit.getEntity();
        } else {
            return null;
        }
    }

    /*
    Function: Get targeted Entity at arbitrary range
    Author: Kabl
    Date: 10.09.2022
    Returns: entity or null
    Desc:
        Targeting option for entities at arbitrary reach, need raycasting
        Default raycasting setup copied from https://fabricmc.net/wiki/tutorial:pixel_raycast
     */

    public static Entity getPlayerRaycastEntityTarget(MinecraftClient client, double reach){
        if(client != null){
            //--- Pre function calculation of current look-direction
            float tickDelta = client.getTickDelta();

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            assert client.cameraEntity != null;
            Vec3d cameraDirection = client.cameraEntity.getRotationVec(tickDelta);
            double fov = client.options.getFov().getValue();
            double angleSize = fov/height;
            Vec3f verticalRotationAxis = new Vec3f(cameraDirection);
            verticalRotationAxis.cross(Vec3f.POSITIVE_Y);
            /*if(!verticalRotationAxis.normalize()) {
                return null; //The camera is pointing directly up or down, you'll have to fix this one
            }*/

            Vec3f horizontalRotationAxis = new Vec3f(cameraDirection);
            horizontalRotationAxis.cross(verticalRotationAxis);
            horizontalRotationAxis.normalize();

            verticalRotationAxis = new Vec3f(cameraDirection);
            verticalRotationAxis.cross(horizontalRotationAxis);

            //Mapping of direction
            Vec3d direction = map(
                    (float) angleSize,
                    cameraDirection,
                    horizontalRotationAxis,
                    verticalRotationAxis,
                    width/2,
                    height/2,
                    width,
                    height
            );

            //Call default raycast function
            HitResult hit = raycastInDirection(client, tickDelta, direction, reach);
            client.player.sendMessage(Text.literal("Hit?: "+hit));

            //Only if entity is hit
            if(hit.getType() == HitResult.Type.ENTITY){
                client.player.sendMessage(Text.literal("Hit entity: "+hit));
                return ((EntityHitResult)hit).getEntity();
            }
        }
        return null;
    }

    /*
    Function: Get targeted Block at arbitrary range
    Author: Kabl
    Date: 10.09.2022
    Returns: BlockPos or null
    Desc:
        Targeting option for blocks at arbritary reach, need raycasting
        Default Raycasting setup copied from https://fabricmc.net/wiki/tutorial:pixel_raycast
     */

    public static BlockPos getPlayerRaycastBlockTarget(MinecraftClient client, double reach){
        if(client != null){
            //--- Pre function calculation of current look-direction
            float tickDelta = client.getTickDelta();

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            assert client.cameraEntity != null;
            Vec3d cameraDirection = client.cameraEntity.getRotationVec(tickDelta);
            double fov = client.options.getFov().getValue();
            double angleSize = fov/height;
            Vec3f verticalRotationAxis = new Vec3f(cameraDirection);
            verticalRotationAxis.cross(Vec3f.POSITIVE_Y);
            /*if(!verticalRotationAxis.normalize()) {
                return null; //The camera is pointing directly up or down, you'll have to fix this one
            }*/

            Vec3f horizontalRotationAxis = new Vec3f(cameraDirection);
            horizontalRotationAxis.cross(verticalRotationAxis);
            horizontalRotationAxis.normalize();

            verticalRotationAxis = new Vec3f(cameraDirection);
            verticalRotationAxis.cross(horizontalRotationAxis);

            //Mapping of direction
            Vec3d direction = map(
                    (float) angleSize,
                    cameraDirection,
                    horizontalRotationAxis,
                    verticalRotationAxis,
                    width/2,
                    height/2,
                    width,
                    height
            );

            //Call default raycast function
            HitResult hit = raycastInDirection(client, tickDelta, direction, reach);
            client.player.sendMessage(Text.literal("Hit?: "+hit));

            //Only if block is hit
            if(hit.getType() == HitResult.Type.BLOCK){
                return ((BlockHitResult)hit).getBlockPos();
            }

        }
        return null;
    }

    /*
    Function: Default Raycast Function
    Author: Kabl
    Date: 10.09.2022
    Returns: Hitresult
    Desc:
        Default raycast function from https://fabricmc.net/wiki/tutorial:pixel_raycast
        raycast from client position in direction at reach
     */

    private static HitResult raycastInDirection(MinecraftClient client, float tickDelta, Vec3d direction, double reach) {
        Entity entity = client.getCameraEntity();
        if (entity == null || client.world == null) {
            return null;
        }

        HitResult target = raycast(entity, reach, tickDelta, false, direction);
        boolean tooFar = false;
        double extendedReach = reach;

        /*
        //For different reaches depending on gamemode:
        if (client.interactionManager.hasExtendedReach()) {
            extendedReach = 6.0D;//Change this to extend the reach
            reachDistance = extendedReach;
        } else {
            if (reachDistance > 3.0D) {
                tooFar = true;
            }
        }*/

        Vec3d cameraPos = entity.getCameraPosVec(tickDelta);

        extendedReach = extendedReach * extendedReach;
        if (target != null) {
            extendedReach = target.getPos().squaredDistanceTo(cameraPos);
        }

        Vec3d vec3d3 = cameraPos.add(direction.multiply(reach));
        Box box = entity
                .getBoundingBox()
                .stretch(entity.getRotationVec(1.0F).multiply(reach))
                .expand(1.0D, 1.0D, 1.0D);
        EntityHitResult entityHitResult = ProjectileUtil.raycast(
                entity,
                cameraPos,
                vec3d3,
                box,
                (entityx) -> !entityx.isSpectator(),
                extendedReach
        );

        if (entityHitResult == null) {
            return target;
        }

        Entity entity2 = entityHitResult.getEntity();
        Vec3d vec3d4 = entityHitResult.getPos();
        double g = cameraPos.squaredDistanceTo(vec3d4);
        if (g < extendedReach || target == null) {
            target = entityHitResult;
            if (entity2 instanceof LivingEntity || entity2 instanceof ItemFrameEntity) {
                client.targetedEntity = entity2;
            }
        }

        return target;
    }

    //Raycast help function
    private static HitResult raycast(Entity entity, double maxDistance, float tickDelta, boolean includeFluids, Vec3d direction) {
        Vec3d end = entity.getCameraPosVec(tickDelta).add(direction.multiply(maxDistance));
        return entity.world.raycast(new RaycastContext(
                entity.getCameraPosVec(tickDelta),
                end,
                RaycastContext.ShapeType.OUTLINE,
                includeFluids ? RaycastContext.FluidHandling.ANY : RaycastContext.FluidHandling.NONE,
                entity
        ));
    }
    //Mapping funcion for direction vector
    private static Vec3d map(float anglePerPixel, Vec3d center, Vec3f horizontalRotationAxis,
                             Vec3f verticalRotationAxis, int x, int y, int width, int height) {
        float horizontalRotation = (x - width/2f) * anglePerPixel;
        float verticalRotation = (y - height/2f) * anglePerPixel;

        final Vec3f temp2 = new Vec3f(center);
        temp2.rotate(verticalRotationAxis.getDegreesQuaternion(verticalRotation));
        temp2.rotate(horizontalRotationAxis.getDegreesQuaternion(horizontalRotation));
        return new Vec3d(temp2);
    }
}
