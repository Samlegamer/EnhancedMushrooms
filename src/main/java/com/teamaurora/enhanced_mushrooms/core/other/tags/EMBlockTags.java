package com.teamaurora.enhanced_mushrooms.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class EMBlockTags {
    public static final TagKey<Block> MUSHROOM_STEMS = TagUtil.blockTag(EnhancedMushrooms.MOD_ID, "mushroom_stems");
}
