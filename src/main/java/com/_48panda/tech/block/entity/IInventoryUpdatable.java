package com._48panda.tech.block.entity;

import net.minecraft.world.item.ItemStack;

public interface IInventoryUpdatable {
    void setItem(int slot, ItemStack setTo, boolean isOutput);
}
