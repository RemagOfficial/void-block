package com.remag.voidblock.block;

import com.remag.voidblock.block.entities.VoidBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class VoidBlockBlock extends BaseEntityBlock {

    public VoidBlockBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
                .strength(3f, 10f)
                .sound(SoundType.AMETHYST));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VoidBlockEntity(pos, state);
    }
}
