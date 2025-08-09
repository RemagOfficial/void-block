package com.remag.voidblock.block.entities;

import com.remag.voidblock.VoidBlock;
import com.remag.voidblock.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VoidBlock.MOD_ID);

    public static final RegistryObject<BlockEntityType<VoidBlockEntity>> VOID_BLOCK =
            BLOCK_ENTITIES.register("void_block",
                    () -> BlockEntityType.Builder.of(VoidBlockEntity::new, ModBlocks.VOID_BLOCK.get()).build(null));
}
