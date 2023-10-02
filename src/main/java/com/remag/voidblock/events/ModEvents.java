package com.remag.voidblock.events;

import com.remag.voidblock.block.ModBlocks;
import com.remag.voidblock.item.ModItems;
import com.remag.voidblock.util.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ModEvents {
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private double previousPosY;

    @SubscribeEvent
    public void punchAir(PlayerInteractEvent.RightClickItem event) {
        if (!event.getSide().isClient() && event.getEntity() instanceof Player player) {
            if (CommonConfig.VOID_BLOCK_ENABLE.get()) {
                // Schedule the item addition on the server's main thread using a task
                MinecraftServer server = player.getServer();
                if (server != null) {
                    server.execute(() -> {
                        int delayTicks = CommonConfig.ITEM_TICK_DELAY.get();
                        ItemStack itemStackToAdd = new ItemStack(ModItems.DUST.get());

                        // Use a custom method to safely add the item to the player's inventory
                        if (addItemToPlayerInventory(player, itemStackToAdd, delayTicks)) {
                            // Item added successfully
                        } else {
                            // Inventory full or another issue
                        }
                    });
                }
            }
        }
    }

    // Custom method to add an item to the player's inventory after a delay
    private boolean addItemToPlayerInventory(Player player, ItemStack itemStack, int delayTicks) {
        if (delayTicks <= 0) {
            // Add the item immediately
            if (player.addItem(itemStack)) {
                // Item added successfully
                return true;
            } else {
                // Inventory full or another issue
                return false;
            }
        } else {
            // Schedule the item addition after the specified delay
            executor.schedule(() -> {
                if (player.addItem(itemStack.copy())) {
                    // Item added successfully
                } else {
                    // Inventory full or another issue
                }
            }, delayTicks, TimeUnit.MILLISECONDS);
            return true;
        }
    }



    @SubscribeEvent
    public void placeBlockUnderPlayer(PlayerInteractEvent.RightClickItem event) {
        if (!event.getSide().isClient() && event.getEntity() instanceof Player player) {
            if (CommonConfig.VOID_BLOCK_ENABLE.get()) {
                if (event.getPlayer().getMainHandItem().sameItem(new ItemStack(ModBlocks.VOID_BLOCK.get().asItem()))) {
                    BlockPos blockPos = event.getPlayer().getOnPos().below();
                    event.getWorld().setBlock(blockPos, ModBlocks.VOID_BLOCK.get().defaultBlockState(), 3);
                    event.getPlayer().getMainHandItem().shrink(1);
                    if (!CommonConfig.REGULAR_FALLING.get()) {
                        CommonConfig.REGULAR_FALLING.set(Boolean.TRUE);
                    }
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
