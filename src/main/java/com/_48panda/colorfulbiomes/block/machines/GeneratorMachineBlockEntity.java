package com._48panda.colorfulbiomes.block.machines;

import com._48panda.colorfulbiomes.Utils;
import com._48panda.colorfulbiomes.block.EnergyCableBlock;
import com._48panda.colorfulbiomes.block.entity.EnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class GeneratorMachineBlockEntity extends MachineBlockEntity implements EnergyBlockEntity {
    public GeneratorMachineBlockEntity(BlockPos pos, BlockState state, MachineProperties properties) {
        super(pos, state, properties);
    }
    @Override
    public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        updateState(world, pos);
    }
    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        updateState(world, pos);
    }
    @Override
    public void tick() {
        super.tick();
        CompoundTag data = getTileData();
        if (data.getBoolean("hasNetworkManager")) {
            int[] NetworkManagerPos = data.getIntArray("networkManagerPos");
            BlockPos sendToPos = new BlockPos(NetworkManagerPos[0], NetworkManagerPos[1], NetworkManagerPos[2]);
            int amount = energyStorage.getEnergyStored();
            Utils.transfer(getLevel(), this, sendToPos, amount);
        }
    }
    private void updateState(Level world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        CompoundTag data = be.getTileData();
        data.putBoolean("hasNetworkManager", false);
        if (getMaxDirection(world, pos) > 0) {
            for (Direction dir: Direction.values()) {
                if (getValInDirection(world, pos, dir) > 0) {
                    BlockPos networkManager = getNetworkManagerPos(world, pos.relative(dir));
                    if (networkManager != null) {
                        data.putBoolean("hasNetworkManager", true);
                        int[] myIntArray = {networkManager.getX(),networkManager.getY(),networkManager.getZ()};
                        data.putIntArray("networkManagerPos", myIntArray);
                    }
                }
            }
        }
    }
    private Integer getValInDirection(Level world, BlockPos pos, Direction dir) {
        BlockState lookingAt = world.getBlockState(pos.relative(dir));
        ResourceLocation myTagId = new ResourceLocation("forge", "electrocution_energy_cable");
        ResourceLocation connect = new ResourceLocation("forge", "electrocution_network_manager_tag");
        if (!Utils.isConnected(world, pos.relative(dir), dir.getOpposite())) {
            return 0;
        }
        if (lookingAt.is(BlockTags.create(myTagId))) {
            return lookingAt.getValue(EnergyCableBlock.DISTANCE);
        } else if (lookingAt.is(BlockTags.create(connect))) {
            return 64;
        } else {
            return 0;
        }

    }
    private Integer getMaxDirection(Level world, BlockPos pos) {
        Integer a = getValInDirection(world, pos, Direction.UP);
        Integer b = getValInDirection(world, pos, Direction.DOWN);
        Integer c = getValInDirection(world, pos, Direction.NORTH);
        Integer d = getValInDirection(world, pos, Direction.SOUTH);
        Integer e = getValInDirection(world, pos, Direction.EAST);
        Integer f = getValInDirection(world, pos, Direction.WEST);
        return Math.max(Math.max(a, b), Math.max(Math.max(c, d), Math.max(e, f)));
    }
    private Direction getDirectionOfMax(Level world, BlockPos pos) {
        Integer maxVal = getMaxDirection(world, pos);
        if (getValInDirection(world, pos, Direction.UP) == maxVal) {
            return Direction.UP;
        }
        if (getValInDirection(world, pos, Direction.DOWN) == maxVal) {
            return Direction.DOWN;
        }
        if (getValInDirection(world, pos, Direction.NORTH) == maxVal) {
            return Direction.NORTH;
        }
        if (getValInDirection(world, pos, Direction.SOUTH) == maxVal) {
            return Direction.SOUTH;
        }
        if (getValInDirection(world, pos, Direction.EAST) == maxVal) {
            return Direction.EAST;
        }
        return Direction.WEST;
    }
    private BlockPos getNetworkManagerPos(Level world, BlockPos pos) {
        if (getMaxDirection(world, pos) < world.getBlockState(pos).getValue(EnergyCableBlock.DISTANCE)) {
            return null;
        }
        if (getMaxDirection(world, pos) == 64) {
            return pos.relative(getDirectionOfMax(world, pos));
        } else {
            return getNetworkManagerPos(world, pos.relative(getDirectionOfMax(world, pos)));
        }
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction direction) {
        if (cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(()->energyStorage).cast();
        }
        return super.getCapability(cap, direction);
    }
}
