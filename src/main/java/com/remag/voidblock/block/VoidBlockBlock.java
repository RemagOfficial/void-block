package com.remag.voidblock.block;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class VoidBlockBlock extends Block {

    public VoidBlockBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.END_PORTAL)
                .strength(3f, 10f)
                .sound(SoundType.AMETHYST));
    }
}
