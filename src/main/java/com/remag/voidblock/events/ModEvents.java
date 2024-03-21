package com.remag.voidblock.events;

import com.remag.voidblock.block.ModBlocks;
import com.remag.voidblock.item.ModItems;
import com.remag.voidblock.util.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
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

    /**
     * Event triggered when a player right clicks with an item. If the common config
     * 'VOID_BLOCK_ENABLE' is true, a void block will be placed below the player and the
     * item will be consumed. If 'REGULAR_FALLING' is false, it will be set to true.
     *
     * @param event The event containing player interaction information
     */
    @SubscribeEvent
    public void punchAir(PlayerInteractEvent.RightClickItem event) {
        // Check if the event is on the server and if the player is not the client
        if (!event.getSide().isClient() && event.getEntity() instanceof Player player) {
            // Check if the common config 'VOID_BLOCK_ENABLE' is true
            if (CommonConfig.VOID_BLOCK_ENABLE.get()) {
                // Schedule the item addition on the server's main thread using a task
                MinecraftServer server = player.getServer();
                if (server != null) {
                    server.execute(() -> {
                        // Get the delay in ticks before adding the item from the common config
                        int delayTicks = CommonConfig.ITEM_TICK_DELAY.get();
                        // Create a new item stack of the void dust item
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

    /**
     * Custom method to add an item to the player's inventory after a delay.
     * 
     * @param player The player to add the item to.
     * @param itemStack The item stack to add.
     * @param delayTicks The delay in ticks before adding the item.
     * @return True if the item was added successfully, false otherwise.
     */
    private boolean addItemToPlayerInventory(Player player, ItemStack itemStack, int delayTicks) {
        // If the delay is 0 or less, add the item immediately
        if (delayTicks <= 0) {
            // Add the item to the player's inventory
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
                // Create a copy of the item stack to prevent modification
                if (player.addItem(itemStack.copy())) {
                    // Item added successfully
                } else {
                    // Inventory full or another issue
                }
            }, delayTicks, TimeUnit.MILLISECONDS);
            // Return true to indicate that the item addition is scheduled
            return true;
        }
    }



    /**
     * Event that is triggered when a player right clicks with an item.
     * If the common config 'VOID_BLOCK_ENABLE' is true and the item clicked is the void block item,
     * a void block will be placed below the player and the item will be consumed.
     * If 'REGULAR_FALLING' is false, it will be set to true.
     *
     * @param event The event containing player interaction information
     */
    @SubscribeEvent
    public void placeBlockUnderPlayer(PlayerInteractEvent.RightClickItem event) {
        // Check if the side is the server side and if the player clicked with the right hand
        if (!event.getSide().isClient() && event.getEntity() instanceof Player player) {
            // Check if the common config 'VOID_BLOCK_ENABLE' is true
            if (CommonConfig.VOID_BLOCK_ENABLE.get()) {
                // Create a new ItemStack for the void block item
                ItemStack voidBlockItem = new ItemStack(ModBlocks.VOID_BLOCK.get().asItem());
                // Check if the item clicked is the void block item
                if (player.getMainHandItem().is(voidBlockItem.getItem())) {
                    // Get the position below the player
                    BlockPos posBelow = player.getOnPos().below();
                    // Set the block at the position below the player to the void block
                    event.getWorld().setBlock(posBelow, ModBlocks.VOID_BLOCK.get().defaultBlockState(), 3);
                    // Consume the void block item
                    player.getMainHandItem().shrink(1);
                    // If 'REGULAR_FALLING' is false, set it to true
                    if (!CommonConfig.REGULAR_FALLING.get()) {
                        CommonConfig.REGULAR_FALLING.set(true);
                    }
                }
            }
        }
    }

    /**
     * Event that is triggered when a living entity is updated (e.g. player).
     * If the common config 'VOID_BLOCK_ENABLE' is true and 'REGULAR_FALLING' is false, it checks if the entity is a player
     * and if it has fallen (y-position lower than previous position). If so, it resets the entity's position to the previous y-position.
     * 
     * @param event The living update event
     */
    @SubscribeEvent
    public void playerNoFall(LivingEvent.LivingUpdateEvent event){
        // Check if the common config and regular falling are enabled
        if(CommonConfig.VOID_BLOCK_ENABLE.get() && !CommonConfig.REGULAR_FALLING.get()){
            // Check if the entity is a player
            if(event.getEntity() instanceof Player){
                LivingEntity entity = (LivingEntity) event.getEntity();
                // Check if the entity has fallen
                if(entity.getY() < previousPosY){
                    // Reset the entity's position to the previous y-position
                    entity.setPos(entity.getX(), previousPosY, entity.getZ());
                }
                // Update the previous y-position
                previousPosY = entity.getY();
            }
        }
    }
}
