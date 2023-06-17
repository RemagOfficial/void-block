package com.remag.voidblock.events;

import com.remag.voidblock.block.ModBlocks;
import com.remag.voidblock.item.ModItems;
import com.remag.voidblock.util.ClientConfig;
import com.remag.voidblock.util.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ModEvents {
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private double previousPosY;

    @SubscribeEvent
    public void punchAir(PlayerInteractEvent.RightClickEmpty event) {
        if(CommonConfig.VOID_BLOCK_ENABLE.get()){
            if(event.getPlayer().getMainHandItem() == ItemStack.EMPTY){
                executor.schedule(() -> {
                    event.getPlayer().addItem(new ItemStack(ModItems.DUST.get()));
                }, 500, TimeUnit.MILLISECONDS);
            }
        }
    }

    @SubscribeEvent
    public void placeBlockUnderPlayer(PlayerInteractEvent.RightClickEmpty event) {
        if(CommonConfig.VOID_BLOCK_ENABLE.get()){
            if(event.getPlayer().getMainHandItem().sameItem(new ItemStack(ModBlocks.VOID_BLOCK.get().asItem()))){
                BlockPos blockPos = event.getPlayer().getOnPos().below();
                event.getWorld().setBlock(blockPos, ModBlocks.VOID_BLOCK.get().defaultBlockState(), 3);
                event.getPlayer().getMainHandItem().shrink(1);
                if(!CommonConfig.REGULAR_FALLING.get()) {
                    CommonConfig.REGULAR_FALLING.set(Boolean.TRUE);
                }
            }
        }
    }

    @SubscribeEvent
    public void playerNoFall(LivingEvent.LivingUpdateEvent event){
        if(CommonConfig.VOID_BLOCK_ENABLE.get()){
            if(!CommonConfig.REGULAR_FALLING.get()){
                if(event.getEntity() instanceof Player){
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    if(entity.getY() < previousPosY){
                        entity.setPos(entity.getX(), previousPosY, entity.getZ());
                    }
                    previousPosY = entity.getY();
                }
            }
        }
    }
}
