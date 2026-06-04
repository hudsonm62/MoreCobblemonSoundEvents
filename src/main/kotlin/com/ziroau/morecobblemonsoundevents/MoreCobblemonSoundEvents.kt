package com.ziroau.morecobblemonsoundevents

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object MoreCobblemonSoundEvents : ModInitializer {
	const val MOD_ID = "morecobblemonsoundevents"
	val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModSounds.register()
		SoundEventHandler.register()
		ResourcePackLoader.register()
		logger.info("Loaded More Cobblemon Sound Events!")
	}
	fun id(path: String?): Identifier {
		return Identifier.of(MOD_ID, path)
	}
}