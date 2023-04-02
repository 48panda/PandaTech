package com._48panda.tech.block.cable;

import com._48panda.tech.PositionWithDirection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.Objects;

public class Connection extends PositionWithDirection {
    private ConnectionType type;
    private int distance;
    public Connection(BlockPos pos, Direction dir, ConnectionType type, int distance) {
        super(pos, dir);
        this.type = type;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection that)) return false;
        if (!super.equals(o)) return false;
        return type == that.type;
    }
    
    public Connection with(int distance) {
        return new Connection(pos, dir, type, distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "type=" + type +
                ", distance=" + distance +
                ", pos=" + pos +
                ", dir=" + dir +
                '}';
    }
}
