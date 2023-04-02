package com._48panda.tech.block.copper_infused;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CopperLeverBlock extends LeverBlock {
    public CopperLeverBlock() {
        super(Properties.copy(Blocks.LEVER));
    }

    public int getSignal(BlockState p_54635_, BlockGetter p_54636_, BlockPos p_54637_, Direction p_54638_) {
        return p_54635_.getValue(POWERED) ? 63 : 0;
    }

    public int getDirectSignal(BlockState p_54670_, BlockGetter p_54671_, BlockPos p_54672_, Direction p_54673_) {
        return p_54670_.getValue(POWERED) && getConnectedDirection(p_54670_) == p_54673_ ? 63 : 0;
    }
}
