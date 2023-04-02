
package com._48panda.tech.block.cable;

import com._48panda.tech.block.cable.entity.EnergyCableBlockEntity;
import com._48panda.tech.block.cable.type.CableType;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;

import org.jetbrains.annotations.Nullable;

public class EnergyCableBlock extends AbstractCableBlock {

	public EnergyCableBlock() {
		super(CableType.ENERGY);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EnergyCableBlockEntity(pos, state);
	}
}