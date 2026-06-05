package com.ziroau.morecobblemonsoundevents

import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.ziroau.morecobblemonsoundevents.MoreCobblemonSoundEvents.logger
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent

object SoundEventHandler {
    @JvmField
    val SOUND_CATEGORY = SoundCategory.MASTER // everything else
    @JvmField
    val PKMSOUND_CATEGORY = SoundCategory.NEUTRAL // for sounds originating from mons
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
                    logger.debug("Executed battle WIN sound event for player: ${player.name.string}")
                }
            }

            val losingPlayers = event.losers
                .filterIsInstance<PlayerBattleActor>()
                .mapNotNull { it.entity }

            val lossSound: SoundEvent =
                if (event.battle.isPvW)
                    ModSounds.BATTLE_PVW_LOSS
                else if (event.battle.isPvP)
                    ModSounds.BATTLE_PVP_LOSS
                else
                    ModSounds.BATTLE_PVN_LOSS
                
            if(losingPlayers.isNotEmpty()){
                losingPlayers.forEach { player ->
                    player.playSoundToPlayer(
                        lossSound,
                        SOUND_CATEGORY,
                        1.0f,
                        1.0f
                    )
                    logger.debug("Executed battle LOSS sound event for player: ${player.name.string}")
                }                
            }
        }
        CobblemonEvents.BATTLE_FLED.subscribe { event ->
            val fledSound: SoundEvent =
                if (event.battle.isPvW)
                    ModSounds.BATTLE_PVW_FLED
                else if (event.battle.isPvP)
                    ModSounds.BATTLE_PVP_FLED
                else
                    ModSounds.BATTLE_PVN_FLED

            val player = event.player.entity
            player?.playSoundToPlayer(
                fledSound,
                SOUND_CATEGORY,
                1.0f,
                1.0f
            )
            logger.debug("Executed battle FLED sound event for player: ${player?.name?.string}")
        }

        CobblemonEvents.STARTER_CHOSEN.subscribe { event ->
            val player = event.player
            player.playSoundToPlayer(
                ModSounds.STARTER_CHOSEN,
                SOUND_CATEGORY,
                1.0f,
                1.0f
            )
            logger.debug("Executed Starter Chosen sound event for player: ${player.name.string}")
        }

        CobblemonEvents.BATTLE_FAINTED.subscribe { event ->
            val pkmEntity = event.killed.effectedPokemon.entity
            if(pkmEntity != null){
                // play in world if entity still valid
                val thisWorld = pkmEntity.world
                thisWorld.playSoundFromEntity(
                    null,
                    pkmEntity,
                    ModSounds.BATTLE_FAINTED,
                    PKMSOUND_CATEGORY,
                    1.0f,
                    1.0f
                )
                logger.debug("Executed Pokemon Fainted sound event at world position for entity: ${pkmEntity.name.string}")
            } else {
                // otherwise fallback to playing sound directly to everyone involved
                event.battle.players.forEach { player ->
                    player.playSoundToPlayer(
                        ModSounds.BATTLE_FAINTED,
                        PKMSOUND_CATEGORY,
                        1.0f,
                        1.0f
                    )
                    logger.debug("Executed Pokemon Fainted sound event for player: ${player.name?.string}")
                }
            }
        }
        CobblemonEvents.POKEMON_CAPTURED.subscribe { event ->
            val player = event.player
            player.playSoundToPlayer(
                ModSounds.PKM_CAPTURED,
                PKMSOUND_CATEGORY,
                1.0f,
                1.0f
            )
            logger.debug("Executed Pokemon Captured sound event for player: ${player.name?.string}")
        }
    }
}