package com.ziroau.morecobblemonsoundevents

import com.ziroau.morecobblemonsoundevents.MoreCobblemonSoundEvents.MOD_ID
import com.ziroau.morecobblemonsoundevents.MoreCobblemonSoundEvents.id
import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.fabric.api.resource.ResourceManagerHelper.registerBuiltinResourcePack
import net.fabricmc.fabric.api.resource.ResourcePackActivationType.NORMAL
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.text.Text

object ResourcePackLoader {
    fun register() {
        val instance = FabricLoader.getInstance()
        if (instance.environmentType == CLIENT) {
            val container = instance.getModContainer(MOD_ID).orElse(null)
            registerBuiltinResourcePack(
                id("demo_pack"),
                container,
                Text.translatable("resourcePack.${MOD_ID}.demo_pack"),
                NORMAL
            )
        }
    }
}
