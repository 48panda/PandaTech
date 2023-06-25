package com._48panda.tech.block.entity;

import com._48panda.tech.init.PandaTechBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class HighDensityGlassFrameBlockEntity extends FrameBlockEntity {
    public HighDensityGlassFrameBlockEntity(BlockPos pos, BlockState state) {
        super(PandaTechBlockEntities.HIGH_DENSITY_GLASS.get(), pos, state);
    }
}
