package com._48panda.tech.block.machines;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ViewingSlotItemHandler extends SlotItemHandler {

    public ViewingSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }
    /*@Override
        public ItemStack safeInsert(ItemStack items) {
        return items;
    }

        @Override
        public ItemStack safeInsert(ItemStack items, int i) {
        return items;

    }*/

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
    @Override
    public boolean mayPickup(Player playerIn) {
        return false;
    }
}
