package com.ziroau.morecobblemonsoundevents

import com.ziroau.morecobblemonsoundevents.sounds.EventSounds
import com.ziroau.morecobblemonsoundevents.sounds.SoundEventHandler
import net.fabricmc.api.EnvType
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object MoreCobblemonSoundEvents : ModInitializer {
	const val MOD_ID = "morecobblemonsoundevents"
	val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		EventSounds.register()
		SoundEventHandler.register()
		if (FabricLoader.getInstance().environmentType == EnvType.CLIENT)
			ResourcePackLoader.register()

		logger.info("Finished Loading More Cobblemon Sound Events!")
	}
	fun id(path: String?): Identifier {
		return Identifier.of(MOD_ID, path)
	}
}