package com._48panda.tech.block.machines;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class AugmentSlot extends SlotItemHandler {
    protected AugmentSlotProperties properties;
    public AugmentSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, AugmentSlotProperties properties) {
        super(itemHandler, index, xPosition, yPosition);
        this.properties = properties;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return properties.isValid(stack);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        return 1;
    }
}
