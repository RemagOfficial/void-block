package com.remag.voidblock.tab;

import com.remag.voidblock.VoidBlock;
import com.remag.voidblock.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VoidBlock.MOD_ID);

    public static final List<Supplier<? extends ItemLike>> VOID_BLOCK_TABS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> VOID_TAB = TABS.register("void_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.void_tab"))
                    .icon(ModBlocks.VOID_BLOCK.get().asItem()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            VOID_BLOCK_TABS.forEach(itemLike -> output.accept(new ItemStack(itemLike.get()))))
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        VOID_BLOCK_TABS.add(itemLike);
        return itemLike;
    }
}
