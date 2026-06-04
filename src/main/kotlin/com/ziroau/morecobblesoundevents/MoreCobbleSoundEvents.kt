package com.ziroau.morecobblesoundevents

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object MoreCobbleSoundEvents : ModInitializer {
	const val MOD_ID = "morecobblesoundevents"
	val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		ModSounds.register()
		SoundEventHandler.register()
		logger.info("Loaded More Cobble Sound Events!")
	}
	fun id(path: String?): Identifier {
		return Identifier.of(MOD_ID, path)
	}
}