package com.ziroau.morecobblemonsoundevents

import com.ziroau.morecobblemonsoundevents.MoreCobblemonSoundEvents.logger
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object EventSounds {
    private data class CobbleSound(val id: Identifier, val sound: SoundEvent)
    private val sounds = mutableListOf<CobbleSound>()

    private fun registerSound(path: String): SoundEvent {
        val id = MoreCobblemonSoundEvents.id(path)
        val sound = SoundEvent.of(id)

        sounds += CobbleSound(id, sound)
        return sound
    }

    val BATTLE_PVN_WIN = registerSound("battle.pvn.win")
    val BATTLE_PVN_LOSS = registerSound("battle.pvn.loss")
    val BATTLE_PVN_FLED = registerSound("battle.pvn.fled")
    val BATTLE_PVP_WIN = registerSound("battle.pvp.win")
    val BATTLE_PVP_LOSS = registerSound("battle.pvp.loss")
    val BATTLE_PVP_FLED = registerSound("battle.pvp.fled")
    val BATTLE_PVW_WIN = registerSound("battle.pvw.win")
    val BATTLE_PVW_LOSS = registerSound("battle.pvw.loss")
    val BATTLE_PVW_FLED = registerSound("battle.pvw.fled")
    val BATTLE_FAINTED = registerSound("battle.fainted")
    val STARTER_CHOSEN = registerSound("pokemon.starter_chosen")
    val PKM_CAPTURED = registerSound("pokemon.captured")

    // via mixin
    @JvmField
    val BATTLE_INVITE_SENT = registerSound("player.battle_invite.sent")
    @JvmField
    val BATTLE_INVITE_RECEIVED = registerSound("player.battle_invite.received")
    @JvmField
    val BATTLE_ACCEPTED = registerSound("player.battle_accepted")
    @JvmField
    val TRADE_INVITE_SENT = registerSound("player.trade_invite.sent")
    @JvmField
    val TRADE_INVITE_RECEIVED = registerSound("player.trade_invite.received")
    @JvmField
    val TRADE_ACCEPTED = registerSound("player.trade_accepted")
    @JvmField
    val PKM_MOUNT = registerSound("pokemon.mount")
    @JvmField
    val PKM_DISMOUNT = registerSound("pokemon.dismount")

    fun register() {
        var registeredAmount = 0
        sounds.forEach { entry ->
            try {
                Registry.register(
                    Registries.SOUND_EVENT,
                    entry.id,
                    entry.sound
                )
                registeredAmount++
            } catch (e: Exception) {
                logger.error("Failed to register Cobblemon Sound Event: ${entry.id}", e)
            }
        }
        logger.info("Loaded '$registeredAmount' More Cobblemon Sound Events!")
    }
}