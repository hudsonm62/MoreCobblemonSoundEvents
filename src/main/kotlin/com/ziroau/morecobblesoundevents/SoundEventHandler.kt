package com.ziroau.morecobblesoundevents

import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent

object SoundEventHandler {
    val SOUND_CATEGORY = SoundCategory.MASTER
    fun register() {
        CobblemonEvents.BATTLE_VICTORY.subscribe { event ->
            val winningPlayers = event.winners
                .filterIsInstance<PlayerBattleActor>()
                .mapNotNull { it.entity }

            if(winningPlayers.isNotEmpty()){
                val winSound: SoundEvent = 
                    if (event.battle.isPvW)
                        ModSounds.BATTLE_PVW_WIN
                    else if (event.battle.isPvP)
                        ModSounds.BATTLE_PVP_WIN
                    else
                        ModSounds.BATTLE_PVN_WIN

                winningPlayers.forEach { player ->
                    player.playSoundToPlayer(
                        winSound,
                        SOUND_CATEGORY,
                        1.0f,
                        1.0f
                    )
                    MoreCobbleSoundEvents.logger.debug("MoreCobbleSoundEvents: Executed battle WIN sound event for player: ${player.name.string}")
                }                
            }

            val losingPlayers = event.losers
                .filterIsInstance<PlayerBattleActor>()
                .mapNotNull { it.entity }
                
            if(losingPlayers.isNotEmpty()){
                losingPlayers.forEach { player ->
                    val lossSound: SoundEvent =
                        if (event.battle.isPvW)
                            ModSounds.BATTLE_PVW_LOSS
                        else if (event.battle.isPvP)
                            ModSounds.BATTLE_PVP_LOSS
                        else
                            ModSounds.BATTLE_PVN_LOSS

                    player.playSoundToPlayer(
                        lossSound,
                        SOUND_CATEGORY,
                        1.0f,
                        1.0f
                    )
                    MoreCobbleSoundEvents.logger.debug("MoreCobbleSoundEvents: Executed battle LOSS sound event for player: ${player.name.string}")
                }                
            }
        }
        CobblemonEvents.BATTLE_FLED.subscribe { event ->
            val player = event.player.entity
            player?.playSoundToPlayer(
                ModSounds.BATTLE_PVN_FLED,
                SOUND_CATEGORY,
                1.0f,
                1.0f
            )
            MoreCobbleSoundEvents.logger.debug("MoreCobbleSoundEvents: Executed battle FLED sound event for player: ${player?.name?.string}")
        }

        CobblemonEvents.MEGA_EVOLUTION.subscribe { event ->
            val player = event.pokemon.effectedPokemon.getOwnerPlayer()
            player?.playSoundToPlayer(
                ModSounds.MEGA_EVOLUTION,
                SOUND_CATEGORY,
                1.0f,
                1.0f
            )
            MoreCobbleSoundEvents.logger.debug("MoreCobbleSoundEvents: Executed Mega Evolution sound event for player: ${player?.name?.string}")
        }

        CobblemonEvents.STARTER_CHOSEN.subscribe { event ->
            val player = event.player
            player.playSoundToPlayer(
                ModSounds.STARTER_CHOSEN,
                SOUND_CATEGORY,
                1.0f,
                1.0f
            )
            MoreCobbleSoundEvents.logger.debug("MoreCobbleSoundEvents: Executed Starter Chosen sound event for player: ${player.name.string}")
        }

        CobblemonEvents.POKEMON_FAINTED.subscribe { event ->
            val player = event.pokemon.getOwnerPlayer()
            player?.playSoundToPlayer(
                ModSounds.PKM_FAINTED,
                SOUND_CATEGORY,
                1.0f,
                1.0f
            )
            MoreCobbleSoundEvents.logger.debug("MoreCobbleSoundEvents: Executed Pokemon Fainted sound event for player: ${player?.name?.string}")
        }
    }
}