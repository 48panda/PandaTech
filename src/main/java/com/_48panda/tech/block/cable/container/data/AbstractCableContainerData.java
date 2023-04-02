package com._48panda.tech.block.cable.container.data;

import com._48panda.tech.block.cable.entity.AbstractCableBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.SimpleContainerData;

public abstract class AbstractCableContainerData extends SimpleContainerData {
    public final AbstractCableBlockEntity blockEntity;
    protected Direction face;
    public AbstractCableContainerData(AbstractCableBlockEntity be, int size, Direction face) {
        super(size);
        blockEntity = be;
        this.face = face;
    }

    @Override
    public abstract int get(int p_40213_);

    @Override
    public abstract void set(int p_40215_, int p_40216_);
}
