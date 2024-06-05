package com.remag.voidblock.item;

import com.remag.voidblock.VoidBlock;
import com.remag.voidblock.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.remag.voidblock.tab.ModCreativeModeTab.addToTab;

/*
 * This class contains static references to all items in the VoidBlock
 * mod. Items are registered in the 'ITEMS' registry, and each item has
 * a reference in this class. The items are also given a tab in the creative
 * menu, which is the 'VOID_TAB'.
 */
public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, VoidBlock.MOD_ID);

    // items

    public static final RegistryObject<Item> DUST = addToTab(ITEMS.register("dust",
                    () -> new Item(new Item.Properties())));

    public static final RegistryObject<Item> PLANT_MATTER = addToTab(ITEMS.register("plant_matter",
                    () -> new Item(new Item.Properties())));

    public static final RegistryObject<Item> GRAVITY_BOOTS = addToTab(ITEMS.register("gravity_boots",
                    () -> new ArmorItem(ModArmorMaterials.VOID, ArmorItem.Type.BOOTS, new Item.Properties())));

    // block items

    public static final RegistryObject<Item> VOID_BLOCK = addToTab(ITEMS.register("void_block",
            () -> new BlockItem(ModBlocks.VOID_BLOCK.get(),
                    new Item.Properties()
            )
    ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
