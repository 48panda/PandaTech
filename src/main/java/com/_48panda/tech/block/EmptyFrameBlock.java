package com._48panda.tech.block;

import com._48panda.tech.block.entity.EmptyFrameBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptyFrameBlock extends FrameBlock {
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new EmptyFrameBlockEntity(pos, state);
    }
}
