package com._48panda.tech.block.cable.container;

import com._48panda.tech.block.cable.container.data.AbstractCableContainerData;
import com._48panda.tech.block.machines.MachineProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCableContainer extends AbstractContainerMenu {
    protected final BlockPos worldPosition;
    public final ContainerData data;
    protected final ContainerLevelAccess containerAccess;
    protected static final int slotSizePlus2 = 18, startX = 8, startY = 84, hotbarY = 142, inventoryY = 18;
    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(
                (double)this.worldPosition.getX() + 0.5D,
                (double)this.worldPosition.getY() + 0.5D,
                (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    public AbstractCableContainer(MenuType<?> type, int id, Inventory playerInv, int size, int numDataPieces) {
        this(type, id, playerInv,
                new ItemStackHandler(size),
                BlockPos.ZERO,
                new SimpleContainerData(numDataPieces));
    }
    public AbstractCableContainer(MenuType<?> type, int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) {
        super(type, id);
        this.data = data;
        worldPosition = pos;
        containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
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
        createSlots(slots);
        addDataSlots(data);
    }
    abstract void createSlots(IItemHandler slots);

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
            } else if (!moveItemStackTo(item, 36, this.slots.size(), false)) {
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
