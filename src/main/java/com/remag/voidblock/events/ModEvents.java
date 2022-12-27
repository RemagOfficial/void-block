package com.remag.voidblock.events;

import com.remag.voidblock.block.ModBlocks;
import com.remag.voidblock.item.ModItems;
import com.remag.voidblock.util.Config;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ModEvents {
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);


    @SubscribeEvent
    public void punchAir(PlayerInteractEvent.RightClickEmpty event) {
        if(Config.VOID_BLOCK_ENABLED.get()){
            if(event.getPlayer().getMainHandItem() == ItemStack.EMPTY){
                executor.schedule(() -> {
                    event.getPlayer().getInventory().add(new ItemStack(ModItems.DUST.get()));
                }, 500, TimeUnit.MILLISECONDS);
            }
        }
    }

    public void voidBlockPlace(PlayerInteractEvent.RightClickEmpty event) {
       if(Config.VOID_BLOCK_ENABLED.get()) {
           if(event.getPlayer().getMainHandItem().equals(new ItemStack(ModBlocks.VOID_BLOCK.get().asItem()))){
               event.getPlayer().getInventory().add(new ItemStack(ModBlocks.VOID_BLOCK.get().asItem()));
           }
       }
    }
}
