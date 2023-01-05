package com.remag.voidblock.events;

import com.remag.voidblock.block.ModBlocks;
import com.remag.voidblock.item.ModItems;
import com.remag.voidblock.util.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ModEvents {
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private double previousPosY;
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void punchAir(PlayerInteractEvent.RightClickEmpty event) {
        if(Config.VOID_BLOCK_ENABLED.get()){
            if(event.getPlayer().getMainHandItem() == ItemStack.EMPTY){
                executor.schedule(() -> {
                    event.getPlayer().addItem(new ItemStack(ModItems.DUST.get()));
                }, 500, TimeUnit.MILLISECONDS);
            }
        }
    }

    @SubscribeEvent
    public void placeBlockUnderPlayer(PlayerInteractEvent.RightClickEmpty event) {
        if(Config.VOID_BLOCK_ENABLED.get()){
            if(event.getPlayer().getMainHandItem().sameItem(new ItemStack(ModBlocks.VOID_BLOCK.get().asItem()))){
                BlockPos blockPos = event.getPlayer().getOnPos().below();
                event.getWorld().setBlock(blockPos, ModBlocks.VOID_BLOCK.get().defaultBlockState(), 3);
                event.getPlayer().getMainHandItem().shrink(1);
                if(!Config.REGULAR_FALLING.get()) {
                    Config.REGULAR_FALLING.set(Boolean.TRUE);
                }
            }
        }
    }

    @SubscribeEvent
    public void playerNoFall(LivingEvent.LivingUpdateEvent event){
        if(Config.VOID_BLOCK_ENABLED.get()){
            if(!Config.REGULAR_FALLING.get()){
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
