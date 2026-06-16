package com.ziroau.morecobblemonsoundevents.mixin;

import com.cobblemon.mod.common.api.net.NetworkPacket;
import com.cobblemon.mod.common.trade.TradeManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.ziroau.morecobblemonsoundevents.EventSounds.TRADE_INVITE_SENT;
import static com.ziroau.morecobblemonsoundevents.EventSounds.TRADE_INVITE_RECEIVED;
import static com.ziroau.morecobblemonsoundevents.EventSounds.TRADE_ACCEPTED;
import static com.ziroau.morecobblemonsoundevents.SoundEventHandler.SOUND_CATEGORY;

@Mixin(value = TradeManager.class, remap = false)
public abstract class TradeManagerMixin {
    @Inject(method = "notificationPacket", at = @At("HEAD"))
    private void moreCobbblemonSoundEvents$playTradeInviteSound(
            TradeManager.TradeRequest request,
            CallbackInfoReturnable<NetworkPacket<?>> cir
    ) {
        ServerPlayerEntity sender = request.getSender();
        ServerPlayerEntity receiver = request.getReceiver();
        sender.playSoundToPlayer(
                TRADE_INVITE_SENT,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
        receiver.playSoundToPlayer(
                TRADE_INVITE_RECEIVED,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
    }
    @Inject(method = "onAccept", at = @At("TAIL"))
    private void moreCobblemonSoundEvents$onTradeRequestAccepted(
            TradeManager.TradeRequest request,
            CallbackInfo ci
    ) {
        ServerPlayerEntity sender = request.getSender();
        ServerPlayerEntity receiver = request.getReceiver();
        sender.playSoundToPlayer(
                TRADE_ACCEPTED,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
        receiver.playSoundToPlayer(
                TRADE_ACCEPTED,
                SOUND_CATEGORY,
                1.0F,
                1.0F
        );
    }
}