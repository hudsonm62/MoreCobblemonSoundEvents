package com.ziroau.morecobblemonsoundevents.mixin;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.ziroau.morecobblemonsoundevents.ModSounds.PKM_DISMOUNT;
import static com.ziroau.morecobblemonsoundevents.ModSounds.PKM_MOUNT;
import static com.ziroau.morecobblemonsoundevents.SoundEventHandler.SOUND_CATEGORY;

@Mixin(Entity.class)
public abstract class CobblemonMountSoundMixin {
    @Inject(
            method = "startRiding(Lnet/minecraft/entity/Entity;)Z",
            at = @At("RETURN")
    )
    private void moreCobblemonSoundEvents$playMountSound(
            Entity vehicle,
            CallbackInfoReturnable<Boolean> cir
    ) {
        // wait for return and check if pokemon
        if (!cir.getReturnValueZ()) return;
        if (!(vehicle instanceof PokemonEntity)) return;

        Entity self = (Entity) (Object) this;

        // since its client shared classes, double check this
        if (self.getWorld().isClient()) return;

        if (self instanceof ServerPlayerEntity player) {
            player.playSoundToPlayer(
                    PKM_MOUNT,
                    SOUND_CATEGORY,
                    1.0F,
                    1.0F
            );
        }
    }
    @Inject(
            method = "stopRiding",
            at = @At("HEAD")
    )
    private void moreCobblemonSoundEvents$playDismountSound(CallbackInfo ci) {
        Entity self = (Entity) (Object) this;

        if (self.getWorld().isClient()) return;

        Entity vehicle = self.getVehicle();
        if (!(vehicle instanceof PokemonEntity)) return;

        if (self instanceof ServerPlayerEntity player) {
            player.playSoundToPlayer(
                    PKM_DISMOUNT,
                    SOUND_CATEGORY,
                    1.0f,
                    1.0f
            );
        }
    }
}