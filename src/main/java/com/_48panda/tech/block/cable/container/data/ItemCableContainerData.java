package com._48panda.tech.block.cable.container.data;

import com._48panda.tech.block.cable.entity.AbstractCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class ItemCableContainerData extends AbstractCableContainerData{
    public ItemCableContainerData(AbstractCableBlockEntity be, int size, Direction face) {
        super(be, size, face);
    }

    @Override
    public int get(int key) {
        BlockPos pos = blockEntity.getBlockPos();
        return switch (key) {
            case 0 -> face.get3DDataValue();
            case 1 -> blockEntity.getIsLocked(face)?1:0;
            case 2 -> pos.getX();
            case 3 -> pos.getY();
            case 4 -> pos.getZ();
            default -> throw new UnsupportedOperationException("Unsupported key '" + key + "'");
        };
    }

    @Override
    public void set(int key, int value) {
        switch (key) {
            case 0 -> face = Direction.from3DDataValue(value);
            case 1 -> blockEntity.setLocked(face, value == 1);
            default -> throw new UnsupportedOperationException("Unsupported key '" + key + "'");
        }
    }

}
