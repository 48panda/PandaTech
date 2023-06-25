package com._48panda.tech.block.entity;

import net.minecraft.core.BlockPos;

public interface IMinionBlockEntity {
    BlockPos getMainPos();
    boolean hasMainAttached();
    void attachMain(BlockPos pos);
    void detachMain();
}
