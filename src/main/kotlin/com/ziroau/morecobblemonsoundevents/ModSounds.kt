package com.ziroau.morecobblemonsoundevents

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object ModSounds {
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
    val STARTER_CHOSEN = registerSound("pokemon.starter_chosen")
    val BATTLE_FAINTED = registerSound("battle.fainted")
    val PKM_CAPTURED = registerSound("pokemon.captured")

    // via mixin
    @JvmField
    val BATTLE_INVITE = registerSound("player.battle_invite")
    @JvmField
    val BATTLE_ACCEPT = registerSound("player.battle_accept")
    @JvmField
    val TRADE_INVITE = registerSound("player.trade_invite")
    @JvmField
    val TRADE_ACCEPT = registerSound("player.trade_accept")
    @JvmField
    val PKM_MOUNT = registerSound("pokemon.mount")
    @JvmField
    val PKM_DISMOUNT = registerSound("pokemon.dismount")

    fun register() {
        sounds.forEach { entry ->
            Registry.register(
                Registries.SOUND_EVENT,
                entry.id,
                entry.sound
            )
        }
    }
}