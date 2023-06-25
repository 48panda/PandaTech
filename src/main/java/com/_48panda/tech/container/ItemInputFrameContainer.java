package com._48panda.tech.container;

import com._48panda.tech.block.machines.InputSlotItemHandler;
import com._48panda.tech.init.PandaTechMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ItemInputFrameContainer extends AbstractContainerMenu {
    protected final ContainerLevelAccess containerAccess;
    protected final BlockPos worldPosition;
    protected final int size, outputSize;
    protected static final int slotSizePlus2 = 18, startX = 8, startY = 51, hotbarY = 109;
    
    public ItemInputFrameContainer(int id, Inventory playerInv) {
        this(id, playerInv,
                new ItemStackHandler(5), BlockPos.ZERO);
    }
    public ItemInputFrameContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos pos) {
        super(PandaTechMenus.INPUT_FRAME.get(), id);
        worldPosition = pos;
        containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
        this.size = 5;
        this.outputSize = 0;
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
        
        int xStart = 44;
        int yStart = 20;
        
        for (int i =0; i < 5 ; i++) {
            addSlot(new InputSlotItemHandler(slots, i, xStart + i * slotSizePlus2, yStart));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(
                (double)this.worldPosition.getX() + 0.5D,
                (double)this.worldPosition.getY() + 0.5D,
                (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
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
