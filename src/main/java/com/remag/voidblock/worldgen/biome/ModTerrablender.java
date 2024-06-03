package com.remag.voidblock.worldgen.biome;

import com.remag.voidblock.VoidBlock;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(VoidBlock.MOD_ID, "overworld"), 5));
    }
}
