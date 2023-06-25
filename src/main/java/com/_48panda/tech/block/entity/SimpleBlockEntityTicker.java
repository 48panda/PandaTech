package com._48panda.tech.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SimpleBlockEntityTicker<T extends BlockEntity> implements BlockEntityTicker<T> {

    @Override
    public void tick(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull T entity) {
        if (entity instanceof ITickableBlockEntity be) {
            be.tick();
        }
    }
}
