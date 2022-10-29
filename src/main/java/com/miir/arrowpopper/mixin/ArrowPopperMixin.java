package com.miir.arrowpopper.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PersistentProjectileEntity.class)
public abstract class ArrowPopperMixin extends ProjectileEntity {

    public ArrowPopperMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow private int life;

    @Shadow protected abstract ItemStack asItemStack();

    @Shadow public PersistentProjectileEntity.PickupPermission pickupType;

    @Inject(at = @At("RETURN"), method = "age")
    private void arrowPopperMixin(CallbackInfo ci) {
        if (this.life >= 1200 && this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
            this.dropStack(this.asItemStack());
        }
    }
}
