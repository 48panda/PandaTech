package com._48panda.tech.block.entity;

import com._48panda.tech.init.PandaTechBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalPistonFrameBlockEntity extends FrameBlockEntity {
    public MechanicalPistonFrameBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.MECHANICAL_PISTON.get(), pos, state);
    }
}
