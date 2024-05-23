package com.remag.voidblock.block;

import com.remag.voidblock.VoidBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VoidBlock.MOD_ID);


    public static final RegistryObject<Block> VOID_BLOCK = BLOCKS.register("void_block",
            VoidBlockBlock::new);


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
