package com.ziroau.morecobblemonsoundevents.mixin;

import com.cobblemon.mod.common.battles.ChallengeManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.ziroau.morecobblemonsoundevents.ModSounds.BATTLE_ACCEPT;
import static com.ziroau.morecobblemonsoundevents.ModSounds.BATTLE_INVITE;
import static com.ziroau.morecobblemonsoundevents.SoundEventHandler.SOUND_CATEGORY;

@Mixin(value = ChallengeManager.class, remap = false)
public abstract class ChallengeManagerMixin {
    @Inject(method = "onSend", at = @At("TAIL"))
    private void moreCobblemonSoundEvents$playSoundOnBattleRequest(
            ChallengeManager.BattleChallenge request,
            CallbackInfo ci
    ) {
        ServerPlayerEntity sender = request.getSender();
        ServerPlayerEntity receiver = request.getReceiver();
        sender.playSoundToPlayer(
                BATTLE_INVITE,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
        receiver.playSoundToPlayer(
                BATTLE_INVITE,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
    }
    @Inject(method = "onAccept", at = @At("TAIL"))
    private void moreCobblemonSoundEvents$playSoundOnBattleResponse(
            ChallengeManager.BattleChallenge request,
            CallbackInfo ci
    ) {
        ServerPlayerEntity sender = request.getSender();
        ServerPlayerEntity receiver = request.getReceiver();
        sender.playSoundToPlayer(
                BATTLE_ACCEPT,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
        receiver.playSoundToPlayer(
                BATTLE_ACCEPT,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
    }
}
