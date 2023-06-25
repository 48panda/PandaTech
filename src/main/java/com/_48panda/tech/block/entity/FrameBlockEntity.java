package com._48panda.tech.block.entity;

import com._48panda.tech.block.FrameBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class FrameBlockEntity extends BlockEntity implements IMinionBlockEntity {
    private BlockPos mainLocation;
    public FrameBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
        mainLocation = BlockPos.ZERO;
    }
    @Override
    public BlockPos getMainPos() {
        return mainLocation;
    }

    @Override
    public boolean hasMainAttached() {
        return getBlockState().getValue(FrameBlock.HAS_MULTIBLOCK);
    }

    @Override
    public void attachMain(BlockPos pos) {
        mainLocation = pos;
        BlockState state = getBlockState();
        if (!state.getValue(FrameBlock.HAS_MULTIBLOCK)) {
            assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            level.setBlockAndUpdate(getBlockPos(), state.setValue(FrameBlock.HAS_MULTIBLOCK, true));
        }
    }

    @Override
    public void detachMain() {
        BlockState state = getBlockState();
        if (state.getValue(FrameBlock.HAS_MULTIBLOCK)) {
            assert level != null; // Again, if this happens we have bigger problems but this shuts up the warnings so cool.
            level.setBlockAndUpdate(getBlockPos(), state.setValue(FrameBlock.HAS_MULTIBLOCK, false));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        List<Integer> list = new ArrayList<>();
        list.add(mainLocation.getX());
        list.add(mainLocation.getY());
        list.add(mainLocation.getZ());
        tag.putIntArray("mainLocation", list);       
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        int[] locations = tag.getIntArray("mainLocation");
        mainLocation = new BlockPos(locations[0], locations[1], locations[2]);
    }
}
