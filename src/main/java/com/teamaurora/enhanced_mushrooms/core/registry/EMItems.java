package com.teamaurora.enhanced_mushrooms.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.integration.boatload.EMBoatTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.modLoaded;
import static com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks.ofID;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EMItems {
    public static final ItemSubRegistryHelper HELPER = EnhancedMushrooms.REGISTRY_HELPER.getItemSubHelper();
    public static final ItemSubRegistryHelper VANILLA_HELPER = EnhancedMushrooms.VANILLA_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> MUSHROOM_STEM = VANILLA_HELPER.createItem("mushroom_stem", () -> new BlockItem(EMBlocks.MUSHROOM_STEM.get(), new Item.Properties()));

    public static final Pair<RegistryObject<Item>, RegistryObject<Item>> MUSHROOM_BOAT = HELPER.createBoatAndChestBoatItem("mushroom", EMBlocks.MUSHROOM_PLANKS);
    public static final RegistryObject<Item> MUSHROOM_FURNACE_BOAT = HELPER.createItem("mushroom_furnace_boat", ModList.get().isLoaded("boatload") ? EMBoatTypes.MUSHROOM_FURNACE_BOAT : () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_MUSHROOM_BOAT = HELPER.createItem("large_mushroom_boat", ModList.get().isLoaded("boatload") ? EMBoatTypes.LARGE_MUSHROOM_BOAT : () -> new Item(new Item.Properties()));

    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(EnhancedMushrooms.MOD_ID)
                .tab(TOOLS_AND_UTILITIES)
                .addItemsBefore(of(Items.BAMBOO_RAFT), MUSHROOM_BOAT.getFirst(), MUSHROOM_BOAT.getSecond())
                .addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), MUSHROOM_FURNACE_BOAT, LARGE_MUSHROOM_BOAT);
    }
}
