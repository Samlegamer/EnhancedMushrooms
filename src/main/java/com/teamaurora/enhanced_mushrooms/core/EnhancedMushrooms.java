package com.teamaurora.enhanced_mushrooms.core;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamaurora.enhanced_mushrooms.core.data.client.EMBlockStateProvider;
import com.teamaurora.enhanced_mushrooms.core.data.client.EMItemModelProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.EMLootTableProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.EMRecipeProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.tags.EMBlockTagsProvider;
import com.teamaurora.enhanced_mushrooms.core.data.server.tags.EMItemTagsProvider;
import com.teamaurora.enhanced_mushrooms.core.other.EMClientCompat;
import com.teamaurora.enhanced_mushrooms.core.other.EMCompat;
import com.teamaurora.enhanced_mushrooms.core.registry.EMBlocks;
import com.teamaurora.enhanced_mushrooms.core.registry.EMItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Mod(EnhancedMushrooms.MOD_ID)
public class EnhancedMushrooms
{
    public static final String MOD_ID = "enhanced_mushrooms";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);
    public static final RegistryHelper VANILLA_HELPER = new RegistryHelper("minecraft");

    public EnhancedMushrooms() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRY_HELPER.register(bus);
        VANILLA_HELPER.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);
        bus.addListener(EventPriority.LOWEST, this::buildCreativeModeTabContents);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            EMBlocks.setupTabEditors();
            EMItems.setupTabEditors();
        });
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            EMCompat.registerCompat();
        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            EMClientCompat.registerRenderLayers();
        });
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean includeServer = event.includeServer();
        EMBlockTagsProvider blockTags = new EMBlockTagsProvider(output, provider, helper);
        generator.addProvider(includeServer, blockTags);
        generator.addProvider(includeServer, new EMItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(includeServer, new EMRecipeProvider(output));
        generator.addProvider(includeServer, new EMLootTableProvider(output));

        boolean includeClient = event.includeClient();
        generator.addProvider(includeClient, new EMBlockStateProvider(output, helper));
        generator.addProvider(includeClient, new EMItemModelProvider(output, helper));
    }

    @SubscribeEvent
    public void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
            ArrayList<ItemStack> mushroom_stems = new ArrayList<ItemStack>();
            for (Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : entries)
            {
                if (entry.getKey().is(Items.MUSHROOM_STEM)) {
                    //mushroom_stems.add(entry.getKey());
                    LOGGER.info(entry.getKey());
                }
            }
            mushroom_stems.forEach(entries::remove);
        }
    }
}
