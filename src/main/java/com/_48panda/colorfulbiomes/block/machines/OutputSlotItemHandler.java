package com._48panda.colorfulbiomes.block.machines;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class OutputSlotItemHandler extends SlotItemHandler {

    public OutputSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
        public ItemStack safeInsert(ItemStack items) {
        return items;
    }

        @Override
        public ItemStack safeInsert(ItemStack items, int i) {
        return items;

    }
    
}
