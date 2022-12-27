package com.remag.voidblock.item;

import com.remag.voidblock.VoidBlock;
import com.remag.voidblock.util.Registration;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{

    /* ITEMS */

    public static final RegistryObject<Item> DUST =
            Registration.ITEMS.register("dust",
                    () -> new Item(new Item.Properties().tab(VoidBlock.VOID_TAB)));

    public static final RegistryObject<Item> PLANT_MATTER =
            Registration.ITEMS.register("plant_matter",
                    () -> new Item(new Item.Properties().tab(VoidBlock.VOID_TAB)));

    public static void register() { }
}
