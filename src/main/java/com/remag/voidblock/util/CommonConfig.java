package com.remag.voidblock.util;

import net.minecraftforge.common.ForgeConfigSpec;


/**
 * Holds common configuration options for the Voidblock mod.
 */
public class CommonConfig
{
    /**
     * The builder for the configuration file.
     */
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    /**
     * The configuration specification.
     */
    public static final ForgeConfigSpec SPEC;

    /**
     * Determines if void blocks should be enabled.
     */
    public static final ForgeConfigSpec.ConfigValue<Boolean> VOID_BLOCK_ENABLE;

    /**
     * The delay between items gained from right clicking the air in milliseconds.
     */
    public static final ForgeConfigSpec.ConfigValue<Integer> ITEM_TICK_DELAY;


    static {
        // Push a new category to the config builder.
        BUILDER.push("Voidblock Config");

        // Define the "Voidblock Enabled" config value.
        VOID_BLOCK_ENABLE = BUILDER.comment("Should Voidblock be enabled")
                .define("Voidblock Enabled", true);

        // Define the "Item Delay" config value.
        ITEM_TICK_DELAY = BUILDER.comment("Delay between items gained from right clicking the air in milliseconds")
                        .define("Item Delay", 100);


        // Pop the category from the config builder.
        BUILDER.pop();

        // Build the configuration specification.
        SPEC = BUILDER.build();

    }
}

