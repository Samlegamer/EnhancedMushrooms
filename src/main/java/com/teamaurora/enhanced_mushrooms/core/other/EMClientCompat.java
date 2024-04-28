package com.teamaurora.enhanced_mushrooms.core.other;

import com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

public class EMClientCompat {

    public static void registerRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(EMBlocks.MUSHROOM_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(EMBlocks.MUSHROOM_TRAPDOOR.get(), RenderType.cutout());
    }
}
