package com._48panda.tech.block.copper_infused;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.swing.*;

public class CopperButton extends ButtonBlock {
    public CopperButton() {
        super(true, BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON));
    }
    @Override
    protected SoundEvent getSound(boolean p_58286_) {
        return p_58286_ ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }
    @Override
    public int getSignal(BlockState state, BlockGetter $0, BlockPos $1, Direction $2) {
        return state.getValue(POWERED) ? 63 : 0;
    }
    @Override
    public int getDirectSignal(BlockState state, BlockGetter blockGetter, BlockPos pos, Direction dir) {
        return state.getValue(POWERED) && getConnectedDirection(state) == dir ? 63 : 0;
    }
}