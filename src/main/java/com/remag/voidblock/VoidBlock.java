package com.remag.voidblock;

import com.remag.voidblock.block.ModBlocks;
import com.remag.voidblock.events.ModEvents;
import com.remag.voidblock.item.ModItems;
import com.remag.voidblock.util.CommonConfig;
import com.remag.voidblock.util.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VoidBlock.MOD_ID)
public class VoidBlock
{
    public static final String MOD_ID = "voidblock";

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructor for the mod.
     *
     * Registers mod events, registers the mod blocks and items, and sets up the config.
     */
    public VoidBlock()
    {
        // Register the mod events
        MinecraftForge.EVENT_BUS.register(new ModEvents());

        // Register the mod blocks, items, and config
        Registration.register();
        ModBlocks.register();
        ModItems.register();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Setup method for mod loading. Called in the preinit phase.
     *
     * @param event The FMLCommonSetupEvent
     */
    private void setup(final FMLCommonSetupEvent event)
    {}

    /**
     * Enqueue InterModCommunication (IMC) method for mod loading.
     * Called in the constructing phase.
     *
     * @param event The InterModEnqueueEvent
     */
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    /**
     * Process InterModCommunication (IMC) method for mod loading.
     * Called in the constructing phase.
     *
     * @param event The InterModProcessEvent
     */
    private void processIMC(final InterModProcessEvent event)
    {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    /**
     * Event handler for when the server starts.
     *
     * @param event The ServerStartingEvent
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    /**
     * Event subscriber for the Registry Events.
     */
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    }
}
