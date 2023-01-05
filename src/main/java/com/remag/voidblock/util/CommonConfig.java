package com.remag.voidblock.util;

import net.minecraftforge.common.ForgeConfigSpec;


public class CommonConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> VOID_BLOCK_ENABLE;

    static {
        BUILDER.push("Voidblock Config");

        VOID_BLOCK_ENABLE = BUILDER.comment("Should Voidblock be enabled")
                .define("Voidblock Enabled", true);

        BUILDER.pop();
        SPEC = BUILDER.build();

    }
}

