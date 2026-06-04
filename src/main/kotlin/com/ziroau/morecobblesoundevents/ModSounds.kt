package com.ziroau.morecobblesoundevents

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object ModSounds {
    private data class CobbleSound(val id: Identifier, val sound: SoundEvent)
    private val sounds = mutableListOf<CobbleSound>()

    private fun registerSound(path: String): SoundEvent {
        val id = MoreCobbleSoundEvents.id(path)
        val sound = SoundEvent.of(id)

        sounds += CobbleSound(id, sound)
        return sound
    }

    val BATTLE_WIN = registerSound("battle.win")
    val BATTLE_LOSS = registerSound("battle.loss")
    val BATTLE_FLED = registerSound("battle.fled")
    val MEGA_EVOLUTION = registerSound("pokemon.mega_evolution")
    val STARTER_CHOSEN = registerSound("pokemon.starter_chosen")
    val PKM_FAINTED = registerSound("pokemon.fainted")

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