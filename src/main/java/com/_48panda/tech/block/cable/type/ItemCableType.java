package com._48panda.tech.block.cable.type;

import com._48panda.tech.block.cable.ItemCableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ItemCableType extends CableType<Item> {
    public static final ItemCableType INSTANCE = new ItemCableType();
    public static final int max = 1;
    
    @Override
    public boolean isConnected(Level level, BlockPos pos, Direction dir) {
        if (level.getBlockState(pos.relative(dir)).getBlock() instanceof ItemCableBlock) {
            return true;
        }
        ICapabilityProvider otherBlock = level.getBlockEntity(pos.relative(dir));
        if (otherBlock == null) {
            return false;
        }
        LazyOptional<IItemHandler> cap = otherBlock.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, dir.getOpposite());
        return cap.isPresent();
    }
}
