package com.teamaurora.enhanced_mushrooms.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.LogBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.enhanced_mushrooms.common.block.MushroomStemBlock;
import com.teamaurora.enhanced_mushrooms.common.block.MushroomStemReplacerBlock;
import com.teamaurora.enhanced_mushrooms.core.EnhancedMushrooms;
import com.teamaurora.enhanced_mushrooms.core.other.EMConstants;
import com.teamaurora.enhanced_mushrooms.integration.farmers_delight.EMFDCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@Mod.EventBusSubscriber(modid = EnhancedMushrooms.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EMBlocks {
    public static final BlockSubRegistryHelper HELPER = EnhancedMushrooms.REGISTRY_HELPER.getBlockSubHelper();
    public static final BlockSubRegistryHelper VANILLA_HELPER = EnhancedMushrooms.VANILLA_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> MUSHROOM_STEM_REPLACER = VANILLA_HELPER.createBlockNoItem("mushroom_stem", ()->new MushroomStemReplacerBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));

    public static final RegistryObject<Block> STRIPPED_MUSHROOM_STEM = HELPER.createBlock("stripped_mushroom_stem", ()->new RotatedPillarBlock(EMProperties.MUSHROOM.log()));
    public static final RegistryObject<Block> STRIPPED_MUSHROOM_HYPHAE = HELPER.createBlock("stripped_mushroom_hyphae", ()->new RotatedPillarBlock(EMProperties.MUSHROOM.log()));
    public static final RegistryObject<Block> MUSHROOM_STEM = HELPER.createBlockNoItem("mushroom_stem", ()->new MushroomStemBlock(STRIPPED_MUSHROOM_STEM, EMProperties.MUSHROOM.log()));
    public static final RegistryObject<Block> MUSHROOM_HYPHAE = HELPER.createBlock("mushroom_hyphae", ()->new LogBlock(STRIPPED_MUSHROOM_HYPHAE, EMProperties.MUSHROOM.log()));
    public static final RegistryObject<Block> MUSHROOM_PLANKS = HELPER.createBlock("mushroom_planks", ()->new Block(EMProperties.MUSHROOM.planks()));
    public static final RegistryObject<Block> MUSHROOM_STAIRS = HELPER.createBlock("mushroom_stairs", ()->new StairBlock(()->MUSHROOM_PLANKS.get().defaultBlockState(), EMProperties.MUSHROOM.planks()));
    public static final RegistryObject<Block> MUSHROOM_SLAB = HELPER.createBlock("mushroom_slab", ()->new SlabBlock(EMProperties.MUSHROOM.planks()));
    public static final RegistryObject<Block> MUSHROOM_PRESSURE_PLATE = HELPER.createBlock("mushroom_pressure_plate", ()->new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, EMProperties.MUSHROOM.pressurePlate(), EMProperties.MUSHROOM_BLOCK_SET));
    public static final RegistryObject<Block> MUSHROOM_BUTTON = HELPER.createBlock("mushroom_button", ()->new ButtonBlock(EMProperties.MUSHROOM.button(), EMProperties.MUSHROOM_BLOCK_SET, 30, true));
    public static final RegistryObject<Block> MUSHROOM_FENCE = HELPER.createFuelBlock("mushroom_fence", ()->new FenceBlock(EMProperties.MUSHROOM.planks()), 300);
    public static final RegistryObject<Block> MUSHROOM_FENCE_GATE = HELPER.createFuelBlock("mushroom_fence_gate", ()->new FenceGateBlock(EMProperties.MUSHROOM.planks(), EMProperties.MUSHROOM_WOOD_TYPE), 300);
    public static final RegistryObject<Block> MUSHROOM_DOOR = HELPER.createBlock("mushroom_door", ()->new DoorBlock(EMProperties.MUSHROOM.door(), EMProperties.MUSHROOM_BLOCK_SET));
    public static final RegistryObject<Block> MUSHROOM_TRAPDOOR = HELPER.createBlock("mushroom_trapdoor", ()->new TrapDoorBlock(EMProperties.MUSHROOM.trapdoor(), EMProperties.MUSHROOM_BLOCK_SET));
    public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> MUSHROOM_SIGNS = HELPER.createSignBlock("mushroom", EMProperties.MUSHROOM_WOOD_TYPE, EMProperties.MUSHROOM.sign());
    public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> MUSHROOM_HANGING_SIGNS = HELPER.createHangingSignBlock("mushroom", EMProperties.MUSHROOM_WOOD_TYPE, EMProperties.MUSHROOM.hangingSign());

    public static final RegistryObject<Block> MUSHROOM_BOARDS = HELPER.createFuelBlock("mushroom_boards", () -> new RotatedPillarBlock(EMProperties.MUSHROOM.planks()), 300);
    public static final RegistryObject<Block> MUSHROOM_BOOKSHELF = HELPER.createFuelBlock("mushroom_bookshelf", ()->new Block(EMProperties.MUSHROOM.bookshelf()), 300);
    public static final RegistryObject<Block> CHISELED_MUSHROOM_BOOKSHELF = HELPER.createFuelBlock("chiseled_mushroom_bookshelf", ()->new ChiseledBookShelfBlock(EMProperties.MUSHROOM.chiseledBookshelf()), 300);
    public static final RegistryObject<Block> MUSHROOM_LADDER = HELPER.createFuelBlock("mushroom_ladder", ()->new LadderBlock(EMProperties.MUSHROOM.ladder()), 300);
    public static final RegistryObject<Block> MUSHROOM_BEEHIVE = HELPER.createBlock("mushroom_beehive", ()->new BlueprintBeehiveBlock(EMProperties.MUSHROOM.beehive()));
    public static final RegistryObject<BlueprintChestBlock> MUSHROOM_CHEST = HELPER.createChestBlock("mushroom", EMProperties.MUSHROOM.chest());
    public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_MUSHROOM_CHEST = HELPER.createTrappedChestBlock("mushroom", EMProperties.MUSHROOM.chest());
    public static final RegistryObject<Block> MUSHROOM_CABINET = HELPER.createFuelBlock("mushroom_cabinet", ItemSubRegistryHelper.areModsLoaded("farmersdelight") ? EMFDCompat.CABINET_SUPPLIER : () -> new Block(BlockBehaviour.Properties.copy(Blocks.BARREL)), 300);

    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(EnhancedMushrooms.MOD_ID)
                .tab(BUILDING_BLOCKS)
                .addItemsBefore(of(Blocks.BAMBOO_BLOCK), MUSHROOM_STEM, MUSHROOM_HYPHAE, STRIPPED_MUSHROOM_STEM, STRIPPED_MUSHROOM_HYPHAE, MUSHROOM_PLANKS)
                .addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), MUSHROOM_BOARDS)
                .addItemsBefore(of(Blocks.BAMBOO_BLOCK), MUSHROOM_STAIRS, MUSHROOM_SLAB, MUSHROOM_FENCE, MUSHROOM_FENCE_GATE, MUSHROOM_DOOR, MUSHROOM_TRAPDOOR, MUSHROOM_PRESSURE_PLATE, MUSHROOM_BUTTON)
                .tab(NATURAL_BLOCKS)
                .addItemsBefore(of(Blocks.CRIMSON_STEM), MUSHROOM_STEM)
                .tab(FUNCTIONAL_BLOCKS)
                .addItemsBefore(of(Blocks.BAMBOO_SIGN), MUSHROOM_SIGNS.getFirst(), MUSHROOM_HANGING_SIGNS.getFirst());

        if (ItemSubRegistryHelper.areModsLoaded("farmersdelight")) {
            CreativeModeTabContentsPopulator.mod(EnhancedMushrooms.MOD_ID)
                    .predicate(EMFDCompat::fdGroupPredicate)
                    .addItemsBefore(ofID(EMConstants.BAMBOO_CABINET), MUSHROOM_CABINET);
        }

        CreativeModeTabContentsPopulator.mod("woodworks_1")
                .tab(FUNCTIONAL_BLOCKS)
                .addItemsBefore(ofID(EMConstants.BAMBOO_LADDER), MUSHROOM_LADDER)
                .addItemsBefore(ofID(EMConstants.BAMBOO_BEEHIVE), MUSHROOM_BEEHIVE)
                .addItemsBefore(ofID(EMConstants.BAMBOO_BOOKSHELF), MUSHROOM_BOOKSHELF, CHISELED_MUSHROOM_BOOKSHELF)
                .addItemsBefore(ofID(EMConstants.BAMBOO_CLOSET), MUSHROOM_CHEST)
                .tab(REDSTONE_BLOCKS)
                .addItemsBefore(ofID(EMConstants.TRAPPED_BAMBOO_CLOSET), TRAPPED_MUSHROOM_CHEST);
    }

    public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
        return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location, ItemLike fallback, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) ? of(ForgeRegistries.ITEMS.getValue(location)) : of(fallback)).test(stack);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
        return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(ForgeRegistries.ITEMS.getValue(location)).test(stack));
    }

    public static class EMProperties {
        public static final BlockSetType MUSHROOM_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(EnhancedMushrooms.MOD_ID + ":mushroom"));
        public static final WoodType MUSHROOM_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(EnhancedMushrooms.MOD_ID + ":mushroom", MUSHROOM_BLOCK_SET));
        public static final PropertyUtil.WoodSetProperties MUSHROOM = PropertyUtil.WoodSetProperties.builder(MapColor.TERRACOTTA_WHITE).build();
    }
}
