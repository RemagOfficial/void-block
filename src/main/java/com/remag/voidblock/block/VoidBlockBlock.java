package com.remag.voidblock.block;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class VoidBlockBlock extends Block {

    public VoidBlockBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
                .strength(3f, 10f)
                .sound(SoundType.AMETHYST));
    }
}
