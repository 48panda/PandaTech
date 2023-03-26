package com._48panda.colorfulbiomes.block.machines;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public abstract class MachineContainer extends AbstractContainerMenu {
    protected final ContainerLevelAccess containerAccess;
    protected final BlockPos worldPosition;
    protected final int size, outputSize;
    public final ContainerData data;
    protected static final int slotSizePlus2 = 18, startX = 8, startY = 84, hotbarY = 142, inventoryY = 18;
    protected final MachineProperties properties;
    public MachineContainer(int id, Inventory playerInv, MachineProperties properties) {
        this(id, playerInv,
                new ItemStackHandler(properties.getNumSlots()),
                new ItemStackHandler(properties.getOutputSlots()),
                BlockPos.ZERO, properties,
                new SimpleContainerData(properties.getNumDataPieces()));
    }
    public MachineContainer(int id, Inventory playerInv, IItemHandler slots, IItemHandler outputSlots, BlockPos pos, MachineProperties properties, ContainerData data) {
        super(properties.getMenuType(), id);
        this.data = data;
        worldPosition = pos;
        containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
        this.size = properties.getNumSlots();
        this.outputSize = properties.getOutputSlots();
        this.properties = properties;
        for (int row = 0; row < 3; row ++) {
            for (int column = 0; column < 9; column ++) {
                addSlot(new Slot(playerInv, 9 + row * 9 + column,
                        startX + column * slotSizePlus2,
                        startY + row * slotSizePlus2));
            }
        }
        for (int column = 0; column < 9; column ++) {
            addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
        }
        createSlots(slots, outputSlots);
        addDataSlots(data);
    }
    public abstract void createSlots(IItemHandler slots, IItemHandler outputSlots);

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(
                (double)this.worldPosition.getX() + 0.5D,
                (double)this.worldPosition.getY() + 0.5D,
                (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack retStack = ItemStack.EMPTY;
        final Slot slot = getSlot(index);
        if (slot.hasItem()) {
            final ItemStack item = slot.getItem();
            retStack = item.copy();
            if (index >= 36) {
                if (!moveItemStackTo(item, 0, 36, true)) {
                    return ItemStack.EMPTY;
                }  
            } else if (!moveItemStackTo(item, 36, this.slots.size() - outputSize, false)) {
                return ItemStack.EMPTY;
            }
            if (item.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return retStack;
    }
    
}
