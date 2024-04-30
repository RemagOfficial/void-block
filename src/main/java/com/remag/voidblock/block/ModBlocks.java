package com.remag.voidblock.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;
import com.remag.voidblock.util.Registration;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final RegistryObject<Block> VOID_BLOCK = register("void_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_PORTAL)
                    .strength(3f, 10f).sound(SoundType.AMETHYST)));


    public static void register() { }

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = Registration.BLOCKS.register(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(toReturn.get(),
                new Item.Properties()));
        return toReturn;
    }
}
