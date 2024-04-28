package com.teamaurora.enhanced_mushrooms.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class EMItemTags {
    public static final TagKey<Item> MUSHROOM_STEMS = TagUtil.itemTag(EnhancedMushrooms.MOD_ID, "mushroom_stems");
}
