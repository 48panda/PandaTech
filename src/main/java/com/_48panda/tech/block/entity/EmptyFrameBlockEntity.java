package com._48panda.tech.block.entity;

import com._48panda.tech.init.PandaTechBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class EmptyFrameBlockEntity extends FrameBlockEntity {
    public EmptyFrameBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.EMPTY_FRAME.get(), pos, state);
    }
}
