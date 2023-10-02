package com.remag.voidblock.util;

import net.minecraftforge.common.ForgeConfigSpec;


public class CommonConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> VOID_BLOCK_ENABLE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> REGULAR_FALLING;

    public static final ForgeConfigSpec.ConfigValue<Integer> ITEM_TICK_DELAY;

    static {
        BUILDER.push("Voidblock Config");

        VOID_BLOCK_ENABLE = BUILDER.comment("Should Voidblock be enabled")
                .define("Voidblock Enabled", true);

        REGULAR_FALLING = BUILDER.comment("Should the player fall normally")
                .define("Regular Falling", true);

        ITEM_TICK_DELAY = BUILDER.comment("Delay between items gained from right clicking the air in milliseconds")
                        .define("Item Delay", 100);



        BUILDER.pop();
        SPEC = BUILDER.build();

    }
}

