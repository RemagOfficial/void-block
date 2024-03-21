package com.remag.voidblock.item;

import com.remag.voidblock.VoidBlock;
import com.remag.voidblock.util.Registration;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

/*
 * This class contains static references to all items in the VoidBlock
 * mod. Items are registered in the 'ITEMS' registry, and each item has
 * a reference in this class. The items are also given a tab in the creative
 * menu, which is the 'VOID_TAB'.
 */
public class ModItems
{
    /* ITEMS */

    /*
     * Dust item. This item appears in the creative menu under 'VoidBlock'.
     */
    public static final RegistryObject<Item> DUST =
            Registration.ITEMS.register("dust",
                    () -> new Item(new Item.Properties().tab(VoidBlock.VOID_TAB)));

    /*
     * Plant matter item. This item appears in the creative menu under 'VoidBlock'.
     */
    public static final RegistryObject<Item> PLANT_MATTER =
            Registration.ITEMS.register("plant_matter",
                    () -> new Item(new Item.Properties().tab(VoidBlock.VOID_TAB)));

    /*
     * This function is intentionally empty, it is here to override the
     * register function in Registration, which could cause issues with the
     * 'ITEMS' registry. It is not needed in this file, and can be safely
     * ignored.
     */
    public static void register() { }
}
