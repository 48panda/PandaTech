package com._48panda.tech;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Objects;

public class PositionWithDirection implements INBTSerializable<CompoundTag> {
    protected BlockPos pos;
    protected Direction dir;
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("X", pos.getX());
        tag.putInt("Y", pos.getX());
        tag.putInt("Z", pos.getX());
        tag.putInt("Direction", dir.get3DDataValue());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        pos = new BlockPos(tag.getInt("X"), tag.getInt("Y"), tag.getInt("Z"));
        dir = Direction.from3DDataValue(tag.getInt("Direction"));
    }

    public PositionWithDirection(BlockPos pos, Direction dir) {
        this.pos = pos;
        this.dir = dir;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PositionWithDirection that = (PositionWithDirection) o;
        if (!pos.equals(that.pos)) {
            return false;
        }
        return dir == that.dir;
    }

    @Override
    public int hashCode() {
        int result = pos.hashCode();
        result = 31 * result + dir.hashCode();
        return result;
    }
}
