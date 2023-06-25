package com._48panda.tech.block.entity;

import com._48panda.tech.init.PandaTechBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BasicMotorBlockEntity extends FrameBlockEntity {
    public BasicMotorBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.BASIC_MOTOR.get(), pos, state);
    }
}
