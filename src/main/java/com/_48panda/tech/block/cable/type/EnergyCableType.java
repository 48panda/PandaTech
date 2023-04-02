package com._48panda.tech.block.cable.type;

import com._48panda.tech.block.cable.EnergyCableBlock;
import com._48panda.tech.block.cable.ItemCableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class EnergyCableType extends CableType<Void> {
    public static final EnergyCableType INSTANCE = new EnergyCableType();
    public static final int max = 10000;

    @Override
    public boolean isConnected(Level level, BlockPos pos, Direction dir) {
        if (level.getBlockState(pos.relative(dir)).getBlock() instanceof EnergyCableBlock) {
            return true;
        }
        ICapabilityProvider otherBlock = level.getBlockEntity(pos.relative(dir));
        if (otherBlock == null) {
            return false;
        }
        LazyOptional<IEnergyStorage> cap = otherBlock.getCapability(CapabilityEnergy.ENERGY, dir.getOpposite());
        return cap.isPresent();
    }
}
