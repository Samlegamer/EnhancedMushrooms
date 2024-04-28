package com.teamaurora.enhanced_mushrooms.core.data.server;

import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.other.EMBlockFamilies;
import com.teamaurora.enhanced_mushrooms.core.other.tags.EMItemTags;
import com.teamaurora.enhanced_mushrooms.integration.boatload.EMBoatTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.*;

public class EMRecipeProvider extends RecipeProvider {
    public EMRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        generateRecipes(consumer, EMBlockFamilies.MUSHROOM_PLANKS_FAMILY);
        planksFromLogs(consumer, MUSHROOM_PLANKS.get(), EMItemTags.MUSHROOM_STEMS, 4);
        woodFromLogs(consumer, MUSHROOM_HYPHAE.get(), MUSHROOM_STEM.get());
        woodFromLogs(consumer, STRIPPED_MUSHROOM_HYPHAE.get(), STRIPPED_MUSHROOM_STEM.get());
        hangingSign(consumer, MUSHROOM_HANGING_SIGNS.getFirst().get(), STRIPPED_MUSHROOM_STEM.get());

        BoatloadRecipeProvider.boatRecipes(consumer, EMBoatTypes.MUSHROOM);
        WoodworksRecipeProvider.baseRecipes(consumer, MUSHROOM_PLANKS.get(), MUSHROOM_SLAB.get(), MUSHROOM_BOARDS.get(), MUSHROOM_BOOKSHELF.get(), CHISELED_MUSHROOM_BOOKSHELF.get(), MUSHROOM_LADDER.get(), MUSHROOM_BEEHIVE.get(), MUSHROOM_CHEST.get(), TRAPPED_MUSHROOM_CHEST.get(), EnhancedMushrooms.MOD_ID);
        WoodworksRecipeProvider.sawmillRecipes(consumer, EMBlockFamilies.MUSHROOM_PLANKS_FAMILY, EMItemTags.MUSHROOM_STEMS, MUSHROOM_BOARDS.get(), MUSHROOM_LADDER.get(), EnhancedMushrooms.MOD_ID);
    }
}
