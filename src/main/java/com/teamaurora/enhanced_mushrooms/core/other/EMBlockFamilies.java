package com.teamaurora.enhanced_mushrooms.core.other;

import net.minecraft.data.BlockFamily;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.*;

public class EMBlockFamilies {
    public static final BlockFamily MUSHROOM_PLANKS_FAMILY = new BlockFamily.Builder(MUSHROOM_PLANKS.get())
            .button(MUSHROOM_BUTTON.get())
            .fence(MUSHROOM_FENCE.get())
            .fenceGate(MUSHROOM_FENCE_GATE.get())
            .pressurePlate(MUSHROOM_PRESSURE_PLATE.get())
            .sign(MUSHROOM_SIGNS.getFirst().get(), MUSHROOM_SIGNS.getSecond().get())
            .slab(MUSHROOM_SLAB.get())
            .stairs(MUSHROOM_STAIRS.get())
            .door(MUSHROOM_DOOR.get())
            .trapdoor(MUSHROOM_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();
}
