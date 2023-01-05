package com.remag.voidblock.util;

import net.minecraftforge.common.ForgeConfigSpec;


public class ClientConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> REGULAR_FALLING;

    static {
        BUILDER.push("Voidblock Config");

        REGULAR_FALLING = BUILDER.comment("Should the player fall normally")
                        .define("Regular Falling", true);

        BUILDER.pop();
        SPEC = BUILDER.build();

    }
}
