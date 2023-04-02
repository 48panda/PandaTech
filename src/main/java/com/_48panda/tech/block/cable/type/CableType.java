package com._48panda.tech.block.cable.type;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public abstract class CableType<T> {
    public static final CableType<Item> ITEM = ItemCableType.INSTANCE;
    public static final CableType<Void> ENERGY = EnergyCableType.INSTANCE;
    
    public abstract boolean isConnected(Level level, BlockPos pos, Direction dir);
}
