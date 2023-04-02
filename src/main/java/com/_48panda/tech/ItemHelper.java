package com._48panda.tech;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemHelper extends ItemHandlerHelper {
    public static ItemStack simulateExtractItem(IItemHandler handler, int slot, int amount) {
        ItemStack out = handler.getStackInSlot(slot).copy();
        out.setCount(Math.max(Math.min(amount, out.getCount()), 0));
        return out;
    }
}
